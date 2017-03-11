package hejianxin.com.listview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        //获取listView布局对象
        ListView listView = (ListView) findViewById(R.id.lv_simple);
        //创建一个adapter对象
        MyListAdapter myListAdapter = new MyListAdapter();
        //将adapter设置给listView
        listView.setAdapter(myListAdapter);
    }
    //为listView提供数据，要继承BaseAdapter类，并实现方法
    class MyListAdapter extends BaseAdapter {

        //有多少个cell
        @Override
        public int getCount() {
            return 20;
        }
        //根据postion获取listview上条目对应的Bean数据,该方法不影响数据的展示
        @Override
        public Object getItem(int position) {
            return null;
        }
        //获取每一行的ID
        @Override
        public long getItemId(int position) {
            return 0;
        }
        //屏幕以外的cell不执行
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //创建一个空的view
            TextView view = null;
            //如果复用的convertView不为空则复用，否则创建新TextView
            if(convertView != null) {
                view = (TextView)convertView;
            }else{
                view = new TextView(mContext);
            }
            view.setText("postion:"+position);
            view.setTextSize(40);
            return view;
        }

    }
}
