package hejianxin.com.sqlite3;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import hejianxin.com.transtation.db.BankOpenHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //点击按钮执行方法
    public void transtation(View v){
        BankOpenHelper bankOpenHelper = new BankOpenHelper(this);
        SQLiteDatabase db = bankOpenHelper.getReadableDatabase();
        db.execSQL("update account set money=money-200 where name=?",new Object[]{"李四"});
        db.execSQL("update account set money=money+200 where name=?",new Object[]{"张三"});
    }
}
