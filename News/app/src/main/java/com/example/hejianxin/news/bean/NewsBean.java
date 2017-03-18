package com.example.hejianxin.news.bean;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

/**
 * Created by hejianxin on 2017/3/9.
 */

public class NewsBean extends Drawable {
    public String title;
    public String des;
    public String news_url;
    public int id;
    public int comment;
    public int type;
    public String time;
    public String icon_url;

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
