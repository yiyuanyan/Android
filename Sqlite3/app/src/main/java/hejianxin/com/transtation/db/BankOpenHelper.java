package hejianxin.com.transtation.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hejianxin on 2017/3/8.
 */

public class BankOpenHelper extends SQLiteOpenHelper {
    public BankOpenHelper(Context context) {
        super(context, "bank.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table account (_id integer primary key autoincrement,name varchar(20),money varchar(20))");
        db.execSQL("insert into account('name','money') values('张三','2000')");
        db.execSQL("insert into account('name','money') values('李四','5000')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
