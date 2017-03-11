package hejianxin.com.sqlite.hejianxin.com.sqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;

import hejianxin.com.sqlite.MySQLiteOpenHelper;
import hejianxin.com.sqlite.hejianxin.com.sqlite.bean.InfoBean;

/**
 * Created by hejianxin on 2017/3/8.
 */

public class InfoDao {
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    public InfoDao(Context context){
        mySQLiteOpenHelper = new MySQLiteOpenHelper(context);
    }
    public boolean add(InfoBean bean){
        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();
        //db.execSQL("insert into info(name,phone) values(?,?);",new Object[]{bean.name,bean.phone});
        //用map封装对象，存放值
        ContentValues values = new ContentValues();
        values.put("name",bean.name);
        values.put("phone",bean.phone);
        //参数:
        //table:表明
        //nullColumnHack:可以为空
        //values:添加的数据
        //返回添加行的ID,-1代表添加错误
        long result = db.insert("info",null,values);
        db.close();
        if(result != -1){
            return true;
        }else{
            return false;
        }

    }
    public int del(String name){
        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();
        //db.execSQL("delete from info where name=?",new Object[]{name});
        //参数：
        //tbale:表名
        //whereClause:删除条件
        //whereArgs:条件的占位符
        //返回受影响的行数
        int reslut = db.delete("info","name=?",new String[]{name});
        db.close();
        return reslut;
    }
    public int update(InfoBean bean){
        SQLiteDatabase db = mySQLiteOpenHelper.getWritableDatabase();
        //db.execSQL("update info set phone=? where name=?",new Object[]{bean.phone,bean.name});
        ContentValues values = new ContentValues();
        values.put("phone",bean.phone);
        //table:表名
        //values:ContentValues类型的值
        //whereClause:更新的条件
        //whereArgs:占位符
        //返回受影响的行数
        int result = db.update("info",values,"name=?",new String[]{bean.name});
        db.close();
        return result;
    }
    public void query(String name){
        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();
        //Cursor cursor = db.rawQuery("select _id,name,phone from info where name=?",new String[]{name});
        //System.out.println(cursor.getCount());
        //table:表名
        //columns:查询的列名，为空则查询所有列
        //selection:查询条件
        //selectionArgs:查询条件占位符
        //groupBy:按照什么字段分组查询
        //having:分组的条件
        //orderBy:排序条件
        Cursor cursor = db.query("info",new String[]{"_id","name","phone"},"name=?",new String[]{name},null,null,"_id desc");
        if(cursor != null && cursor.getCount() > 0){
            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String nameString = cursor.getString(1);
                String phoneString = cursor.getString(2);
                System.out.println("id:"+id+";name:"+nameString+";phone:"+phoneString);

            }
            cursor.close();
        }
        db.close();
    }
}
