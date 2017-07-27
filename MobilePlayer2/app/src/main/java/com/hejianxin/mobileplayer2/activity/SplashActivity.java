package com.hejianxin.mobileplayer2.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.hejianxin.mobileplayer2.R;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //延时执行，在主线程执行
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //延时两秒后执行
                //在主线程执行
                startMainActivity();
                Log.e(TAG,"当前线程名称=="+Thread.currentThread().getName());
            }


        },2000);
    }
    private boolean isStartMain = false;

    //跳转到主页面，并且把当前页面关闭掉
    private void startMainActivity() {
        if(!isStartMain){
            isStartMain = true;
            //意图对象
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            //关闭当前页面
            finish();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG,"onTouchEvent==Action"+event.getAction());
        startMainActivity();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();

    }
}
