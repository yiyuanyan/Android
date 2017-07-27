package com.example.viewpager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //获取MainActivity类的名称
    private static final String TAG = MainActivity.class.getSimpleName();
    private ViewPager viewpager;
    private TextView tv_title;
    private LinearLayout ll_point_group;
    //数据集合
    private ArrayList<ImageView> imageViews;
    private final int[] imageIds = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,};
    //上一次高亮显示的位置
    private int prePosition = 0;
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
            //添加选择器
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_selector);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(8,8);
            if(i == 0){
                //显示红色
                point.setEnabled(true);
            }else{
                //显示灰色
                point.setEnabled(false);
                params.leftMargin = 8;
            }


            point.setLayoutParams(params);
            ll_point_group.addView(point);
        }
        //4.设置适配器(PagerAdapter)-item布局，绑定数据
        viewpager.setAdapter(new MyPagerAdapter());
        //设置监听viewpager页面的改变
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());
        tv_title.setText(imageDescriptions[prePosition]);
    }


    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{
        /**
         * 当页面滚动了的时候回调这个方法
         * @param position  当前页面的位置
         * @param positionOffset  滑动页面的百分比
         * @param positionOffsetPixels 在屏幕上滑动的像素
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /**
         * 当某个页面被选中的时候回调
         * @param position 被选中页面的位置
         */
        @Override
        public void onPageSelected(int position) {
            //设置对应页面的文本信息
            tv_title.setText(imageDescriptions[position]);
            //坝上一个高亮的设置默认灰色
            ll_point_group.getChildAt(prePosition).setEnabled(false);
            //当前的设置为高亮
            ll_point_group.getChildAt(position).setEnabled(true);
            prePosition = position;
        }

        /**
         * 当页面滚动状态变化的时候回调
         * 静止->滑动->静止
         * @param state
         */
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }




    class MyPagerAdapter extends PagerAdapter{

        /*
        获取图片的总数
         */
        @Override
        public int getCount() {
            return imageViews.size();
            //return Integer.MAX_VALUE;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageView =  imageViews.get(position);
            container.addView(imageView); //添加到ViewPager中
            Log.e(TAG, "instantiateItem: " + position + ",------imageView=="+imageView);
            return imageView;
        }

        /**
         * 比较view和object是否是同一个实力
         * @param view 页面
         * @param object instantiateItem返回的结果
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
//            if (view == object){
//                return true;
//            }else{
//                return false;
//            }
            return view == object;
        }


        /**
         * 释放资源
         * @param container viewpager
         * @param position  释放的位置
         * @param object  要释放的页面
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            Log.e(TAG, "destroyItem: " + position + ",----object==" + object);
            container.removeView((View)object);

        }
    }
}
