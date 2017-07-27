package com.example.youku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private ImageView icon_home;
    private ImageView icon_menu;
    private RelativeLayout level1;
    private RelativeLayout level2;
    private RelativeLayout level3;

    //显示状态
    private boolean isShowLevel3 = true;
    private boolean isShowLevel2 = true;
    private boolean isShowLevel1 = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        icon_home = (ImageView) findViewById(R.id.icon_home);
        icon_menu = (ImageView) findViewById(R.id.icon_menu);
        level1 = (RelativeLayout) findViewById(R.id.leve1);
        level2 = (RelativeLayout) findViewById(R.id.leve2);
        level3 = (RelativeLayout) findViewById(R.id.leve3);
        //设置点击事件
        MyOnClickListener myOnClickListener = new MyOnClickListener();
        icon_home.setOnClickListener(myOnClickListener);
        icon_menu.setOnClickListener(myOnClickListener);
    }
    class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.icon_home: // home
                    //如果三级菜单和二级菜单显示，则隐藏
                    if(isShowLevel2){
                        isShowLevel2 = false;
                        Tools.hideView(level2);
                        if(isShowLevel3){
                            isShowLevel3 = false;
                            Tools.hideView(level3,200);
                        }
                    }else{
                        isShowLevel2 = true;
                        Tools.showView(level2);
                    }
                    //如果都是隐藏的，则显示二级菜单

                    break;
                case R.id.icon_menu:  //菜单
                    if(isShowLevel3){
                        isShowLevel3 = false;
                        Tools.hideView(level3);
                    }else{
                        Tools.showView(level3);
                        isShowLevel3 = true;
                    }
                    break;
            }
        }
    }
}
