package com.example.quickindex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hejianxin on 2017/7/11.
 */

public class IndexView extends View {
    private int itemWidth;
    private int itemHeight;

    private Paint paint;

    private String[] words = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};



    public IndexView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        itemWidth = getMeasuredWidth();
        itemHeight = getMeasuredHeight()/words.length;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i = 0; i <words.length;i++){
            String word = words[i];
            Rect rect = new Rect();
            paint.getTextBounds(word,0,1,rect);
            int wordWidth = rect.width();
            int wordHeight = rect.height();
            float wordX = itemWidth/2 - wordWidth/2;
            float wordY = itemHeight/2 + wordHeight/2 + i*itemHeight;
            canvas.drawText(word,wordX,wordY,paint);
        }
    }

    /**
     * 自定义接口
     * 字母下标索引变化的监听者
     */
    public interface OnIndexChangeListener{
        //当字母下标位置发生变化的时候回调
        void onIndexChange(String word);
    }

    //供外界实例化调用
    private OnIndexChangeListener onIndexChangeListener;
    //set方法

    /**
     *
     * @param onIndexChangeListener
     */
    public void setOnIndexChangeListener(OnIndexChangeListener onIndexChangeListener) {
        this.onIndexChangeListener = onIndexChangeListener;
    }

}
