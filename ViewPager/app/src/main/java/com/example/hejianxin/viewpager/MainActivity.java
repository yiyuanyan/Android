package com.example.hejianxin.viewpager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private int[] imageResIds; //图片数组
    private ArrayList<ImageView> imageViewList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //View初始化布局
        initViews();

        //Model
        initData();
        //Controller
        initAdapter();
    }
    private void initViews(){
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        imageResIds = new int[]{R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e};
        //初始化要展示的5个 imageView
        //创建一个 List 集合用于存放创建出来的 imageView
        imageViewList = new ArrayList<ImageView>();
        ImageView imageView;
        for (int i = 0; i<imageResIds.length; i++){
            imageView = new ImageView(this);
            //给 imageView 设置图片
            imageView.setBackgroundResource(imageResIds[i]);
            //将创建的 imageView 加入 List 集合中
            imageViewList.add(imageView);
        }
    }

    private void initData(){
        //初始化数据
    }
    private void initAdapter(){
        //设置适配器
        viewPager.setAdapter(new MyAdapter());
    }
    //自定义适配器类，继承 PagerAdapter 并实现必要方法
    class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return imageViewList.size();
        }

        /**
         * 3：指定复用条件，滑动到新条目重新调用检查当前view 是否可以服用
         * @param view
         * @param object
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            //当 view 等于 object 中封装的 view 时进行复用
            return view == object;
        }
        //返回要显示的内容
        /**1：创建条目，必须重写这个方法
         * container:容器 viewpage
         * position:当前要显示条目的位置
         * */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //把 view 对象添加到 container 中
            ImageView imageView = imageViewList.get(position);
            //把获取的 imageView 添加到 container 中
            container.addView(imageView);
            //把 imageView 返回给适配器
            return imageView;
        }

        /**
         * 2：销毁条目
         * @param container
         * @param position
         * @param object
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //销毁用于复用
            container.removeView((View) object);
        }
    }
}
