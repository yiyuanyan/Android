package com.hejianxin.webcode;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;

import java.net.URL;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_url;
    private ImageView img_pic;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        //获取控件
        et_url = (EditText) findViewById(R.id.et_url);
        Button bt_looksource = (Button) findViewById(R.id.bt_looksource);
        img_pic = (ImageView) findViewById(R.id.img_pic);
        //设置点击事件
        bt_looksource.setOnClickListener(this);
    }

    //在主线程创建一个handler对象
    private Handler handler = new Handler() {
        //重写handler的handlermessage方法,用来接收子线程发来的消息

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //接收子线程发送的数据，并处理
            Bitmap map = (Bitmap) msg.obj;
            //显示在textView
            //img_pic.setText(result);
            img_pic.setImageBitmap(map);
        }
    };

    @Override
    public void onClick(View v) {
        try {
            //onClick方法获取用户输入的URL地址
            final String url_str = et_url.getText().toString().trim();
            if (TextUtils.isEmpty(url_str)) {
                Toast.makeText(mContext, "URL不能为空", Toast.LENGTH_SHORT).show();
                return;
            }

            //创建子线程
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //创建一个url对象
                        URL url = new URL(url_str);
                        //获取一个urlConnection对象
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        //设置请求的参数,请求方式，超时时间毫秒
                        connection.setRequestMethod("GET");
                        connection.setConnectTimeout(1000 * 10);
                        //判断服务器返回的状态码
                        int code = connection.getResponseCode();
                        System.out.println("状态码:" + code);
                        if (code == 200) {
                            //获取流数据并解析成字符串
                            InputStream inputStream = connection.getInputStream();
                            //String result = StreamUtils.streamToString(inputStream);
                            //将流转换为Bitmap图片
                            Bitmap map = BitmapFactory.decodeStream(inputStream);
                            //子线程中创建一个Message对象，为了携带子线程中获取的数据给主线程
                            //Message msg = new Message();
                            //复用Message对象，如果内存没有销毁就复用。
                            Message msg = Message.obtain();
                            //将返回的数据封装到msg中
                            msg.obj = map;
                            //使用handler对象将message发送给主线程
                            handler.sendMessage(msg);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
