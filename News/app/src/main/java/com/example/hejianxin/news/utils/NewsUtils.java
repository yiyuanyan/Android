package com.example.hejianxin.news.utils;

import android.content.Context;

import com.example.hejianxin.news.R;
import com.example.hejianxin.news.bean.NewsBean;

import java.util.ArrayList;

/**
 * Created by hejianxin on 2017/3/9.
 */

public class NewsUtils {
    //封装数据
    public static ArrayList<NewsBean> getAllNews(Context context) {
        ArrayList<NewsBean> arrayList = new ArrayList<>();
        for(int i=0; i<20; i++) {
            NewsBean newsBean = new NewsBean();
            newsBean.title = "0000000000";
            newsBean.des = "0000000000000,0000000000000000000000000,0000000000000000000000000,000000000000";
            newsBean.news_url = "http://news.sina.com.cn";
            //通过context对象将一个资源ID转换为drawable对象
            newsBean.icon = context.getResources().getDrawable(R.drawable.timg);
            arrayList.add(newsBean);


            NewsBean newsBean1 = new NewsBean();
            newsBean1.title = "1111111";
            newsBean1.des = "11111111111111,1111111111110000000000000,0000000000000000000000000,0000000000000000000000000,000000000000";
            newsBean1.news_url = "http://news.sina.com.cn";
            //通过context对象将一个资源ID转换为drawable对象
            newsBean1.icon = context.getResources().getDrawable(R.drawable.timg);
            arrayList.add(newsBean1);


            NewsBean newsBean2 = new NewsBean();
            newsBean2.title = "22222222";
            newsBean2.des = "2222222222222,22222222222222220000000000000,0000000000000000000000000,000000000000";
            newsBean2.news_url = "http://news.sina.com.cn";
            //通过context对象将一个资源ID转换为drawable对象
            newsBean2.icon = context.getResources().getDrawable(R.drawable.timg);
            arrayList.add(newsBean2);

            NewsBean newsBean3 = new NewsBean();
            newsBean3.title = "333333333";
            newsBean3.des = "33333333333,33333333330000000000000,000000000000";
            newsBean3.news_url = "http://news.sina.com.cn";
            //通过context对象将一个资源ID转换为drawable对象
            newsBean3.icon = context.getResources().getDrawable(R.drawable.timg);
            arrayList.add(newsBean3);
        }

        return arrayList;
    }
}
