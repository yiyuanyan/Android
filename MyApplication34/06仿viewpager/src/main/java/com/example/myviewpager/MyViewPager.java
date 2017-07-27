package com.example.myviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**创建一个自定义类，实现自定义属性
 * Created by hejianxin on 2017/7/10.
 */

public class MyViewPager extends ViewGroup {
    /**
     * 手势识别器
     * 1、定义手势识别器
     * 2、实例化-重新定义方法
     * 3、在onTouchEvent()把事件传递给手势识别器
     *
     *
     */
    private GestureDetector detector;
    private int currentIndex;
    private MyScroller scroller;

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        //实例化手势识别器
        initView(context);
    }

    private void initView(final Context context) {
        //实例化手势识别器
        detector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
                Toast.makeText(context, "长按", Toast.LENGTH_SHORT).show();
            }

            /**
             *
             * @param e1
             * @param e2
             * @param distanceX X轴滑动的距离
             * @param distanceY Y轴滑动的距离
             * @return
             */
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                //滑动
                scrollBy((int)distanceX,0);
                return true;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Toast.makeText(context, "双击", Toast.LENGTH_SHORT).show();
                return super.onDoubleTap(e);
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //遍历子试图，给每个子试图制定坐标位置
        for(int i=0; i<getChildCount();i++){
            View childView = getChildAt(i);
            childView.layout(i * getWidth(),0,(i+1)*getWidth(),getHeight());
        }
    }
    private float startX;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        //把事件传递给手势识别器
        detector.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //1.记录坐标
                startX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                //新坐标
                float endX = event.getX();
                //计算偏移量

                int tempIndex = currentIndex;
                if((startX-endX)>getWidth()/2){
                    tempIndex ++;
                }else if((endX-startX)>getWidth()/2){
                    tempIndex --;
                }
                scrollToPager(tempIndex);
                break;
        }
        return true;
    }

    /**
     * 屏蔽非法值,根据位置移动到指定页面
     * @param tempIndex
     */
    private void scrollToPager(int tempIndex){
        if(tempIndex < 0){
            tempIndex = 0;
        }
        if(tempIndex > getChildCount()-1){
            tempIndex = getChildCount()-1;
        }
        //当前页面下标位置
        currentIndex = tempIndex;
        //滚动到指定下标的当前页面
        int distanceX = currentIndex*getWidth() - getScrollX();
        //scrollTo(,getScrollY());
        scroller.startScroll(getScrollX(),getScaleY(),distanceX,0);
        invalidate();
    }

    @Override
    public void computeScroll() {
       // super.computeScroll();
       if(scroller.cuputeScrollOffset()){
           float currX = scroller.getCurrX();
           scrollTo((int) currX,0);
           invalidate();
       }
    }
}
