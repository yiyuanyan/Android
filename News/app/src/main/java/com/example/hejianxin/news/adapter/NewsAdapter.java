package com.example.hejianxin.news.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hejianxin.news.R;
import com.example.hejianxin.news.bean.NewsBean;

import java.util.ArrayList;

/**
 * Created by hejianxin on 2017/3/9.
 */

public class NewsAdapter extends BaseAdapter {
    private ArrayList<NewsBean> list;
    private Context context;
    public NewsAdapter(Context context, ArrayList<NewsBean> list){
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        //返回数组的个数
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    //返回item的ID
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if(convertView != null){
            view = convertView;
        }else{

            //将一个xml布局文件转换为view对象
            //context:上下文
            //resource:要转换成view对象layout的id int类型
            //root:将layout用root包一层作为getView的返回值
            view = View.inflate(context, R.layout.item_news_layout, null);
        }
        //获取布局文件中的控件
        ImageView item_img_icon = (ImageView) view.findViewById(R.id.item_img_icon);
        TextView item_tv_des = (TextView) view.findViewById(R.id.item_tv_des);
        TextView item_tv_title = (TextView) view.findViewById(R.id.item_tv_title);
        //获取list这个bean对象下标为position的数据
        NewsBean newsBean = list.get(position);
        item_img_icon.setImageDrawable(newsBean.icon);
        item_tv_title.setText(newsBean.title);
        item_tv_des.setText(newsBean.des);
        return view;
    }
}
