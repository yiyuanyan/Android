package com.example.hejianxin.news;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hejianxin.news.bean.NewsBean;
import com.example.hejianxin.news.utils.NewsUtils;
import com.example.hejianxin.news.adapter.NewsAdapter;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private Context mContext;
    private Handler handler = new Handler() {
        @Override
        public void publish(LogRecord record) {

        }

        @Override
        public void flush() {

        }

        @Override
        public void close() throws SecurityException {

        }

        public void handleMessage(android.os.Message msg){

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        ListView lv_news = (ListView) findViewById(R.id.lv_news);

        new Thread(new Runnable() {
            @Override
            public void run() {

                ArrayList<NewsBean> allNews = null;
                try {
                    allNews = NewsUtils.getAllNewsForNetWork(mContext);
                    Message msg = Message.obtain();
                    msg.obj = allNews;
                    handler.sendMessage(msg);
                    //创建adapter设置给ListView
                    NewsAdapter newsAdapter = new NewsAdapter(mContext, allNews);
                    lv_news.setAdapter(newsAdapter);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        //实现item的点击事件，要implements AdapterView.OnItemClickListener
        //并且实现onItemClick()方法
        lv_news.setOnItemClickListener(this);
    }
    //点击时调用这个方法
    //parent:就是listView
    //view:就是cell上的view对象
    //position:cell的ID
    //id:就是cell的ID
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //获取item的bean
        NewsBean object = (NewsBean) parent.getItemAtPosition(position);
        String url = object.news_url;
        //跳转浏览器
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
