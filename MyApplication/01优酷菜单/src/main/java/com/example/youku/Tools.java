package com.example.youku;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by hejianxin on 2017/7/6.
 * 显示隐藏制定空间
 */

class Tools {
    public static void hideView(ViewGroup view){
        hideView(view,0);


    }
    public static void showView(ViewGroup view){
        RotateAnimation ra = new RotateAnimation(180,360,view.getWidth()/2,view.getHeight());
        ra.setDuration(500);
        ra.setFillAfter(true);
        view.startAnimation(ra);
        //获取ViewGroup所有的子视图，并将所有的子视图设置为不可点击
        for(int i = 0; i<view.getChildCount();i++){
            View children = view.getChildAt(i);
            children.setEnabled(true);
        }

    }

    public static void hideView(ViewGroup view, int startOffset) {
        RotateAnimation ra = new RotateAnimation(0,180,view.getWidth()/2,view.getHeight());
        //动画执行事件，毫秒
        ra.setDuration(500);
        //动画播放完成后停止
        ra.setFillAfter(true);
        //延时执行
        ra.setStartOffset(startOffset);
        //动画启动
        view.startAnimation(ra);
        //循环ViewGroup的所有子视图，并设置所有子视图可以点击交互
        for(int i = 0; i<view.getChildCount();i++){
            View children = view.getChildAt(i);
            children.setEnabled(false);

        }
    }
}
