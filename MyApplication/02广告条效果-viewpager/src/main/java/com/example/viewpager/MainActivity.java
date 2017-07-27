package com.example.viewpager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewpager;
    private TextView tv_title;
    private LinearLayout ll_point_group;
    //数据集合
    private ArrayList<ImageView> imageViews;
    private final int[] imageIds = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,};
    private final String[] imageDescriptions = {"尚硅谷拔河争霸赛！","凝聚你我，放飞梦想！","抱歉，没座位了！","7月就业名单全部曝光！","平均起薪11345元"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        tv_title = (TextView) findViewById(R.id.tv_title);
        ll_point_group = (LinearLayout) findViewById(R.id.ll_point_group);
        //ViewPager的使用
        //1.在布局文件中定义ViewPager
        //2.在代码中实例化ViewPager
        //3.准备数据
        imageViews = new ArrayList<>();
        for(int i=0; i<imageIds.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);
            imageViews.add(imageView);
        }
        //4.设置适配器(PagerAdapter)-item布局，绑定数据
    }
}
