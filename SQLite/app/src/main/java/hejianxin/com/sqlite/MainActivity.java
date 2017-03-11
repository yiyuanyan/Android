package hejianxin.com.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import hejianxin.com.sqlite.hejianxin.com.sqlite.bean.InfoBean;
import hejianxin.com.sqlite.hejianxin.com.sqlite.dao.InfoDao;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(mContext);
        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();

        findViewById(R.id.bt_add).setOnClickListener(this);
        findViewById(R.id.bt_del).setOnClickListener(this);
        findViewById(R.id.bt_update).setOnClickListener(this);
        findViewById(R.id.bt_query).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        InfoDao infoDao = new InfoDao(mContext);
        switch(v.getId()){
            case R.id.bt_add:
                InfoBean bean = new InfoBean();
                bean.name = "张三";
                bean.phone = "13800138000";
                boolean result = infoDao.add(bean);
                if(result){
                    Toast.makeText(mContext, "添加成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "添加失败", Toast.LENGTH_SHORT).show();
                }
                InfoBean bean2 = new InfoBean();
                bean2.name = "李四";
                bean2.phone = "13811138111";
                boolean result2 = infoDao.add(bean2);
                if(result2){
                    Toast.makeText(mContext, "添加成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "添加失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_del:
                int del = infoDao.del("张三");
                Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();

                break;
            case R.id.bt_update:
                InfoBean bean3 = new InfoBean();
                bean3.name = "李四";
                bean3.phone = "13911139111";
                int update = infoDao.update(bean3);
                Toast.makeText(mContext, "修改成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_query:
                infoDao.query("李四");
                infoDao.query("张三");
                break;
        }
    }
}
