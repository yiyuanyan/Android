package com.example.myviewpager;

import android.os.SystemClock;

/**
 * Created by hejianxin on 2017/7/11.
 */

public class MyScroller {
    //起始坐标
    private float startX;
    private float startY;
    //移动的距离
    private int distanceX;
    private int distanceY;
    //开始的时间
    private long startTime;
    private long totalTime = 500;
    /**
     * 是否移动完成。
     *
     */
    private boolean isFinish;
    private float currX;

    public float getCurrX() {
        return currX;
    }

    public void startScroll(float startX, float startY, int distanceX, int distanceY) {
        this.startX = startX;
        this.startY = startY;
        this.distanceX = distanceX;
        this.distanceY = distanceY;
        this.startTime = SystemClock.uptimeMillis();  //系统开机时间
        this.isFinish = false;  //是否移动完成
    }

    /**
     *
     * @return
     */
    public  boolean cuputeScrollOffset(){
        if(isFinish){
            return false;
        }
        long endTime = SystemClock.uptimeMillis();
        long passTime = endTime - startTime;
        if(passTime < totalTime){
            //计算平均速度
            //float voleCity = distanceX/totalTime;
            float distanceSamllX = passTime * (distanceX/totalTime);

            currX = startX + distanceSamllX;
        }else{
            isFinish = true;
            currX = startX + distanceX;
        }
        return true;
    }
}
