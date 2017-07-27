package com.hejianxin.mobileplayer2.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hejianxin.mobileplayer2.R;

/**
 * Created by hejianxin on 2017/7/27.
 */

class MainActivity extends Activity {
    private FrameLayout fl_main_content;
    private RadioGroup rg_bottom_tag;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        //实例化布局
        fl_main_content = (FrameLayout) findViewById(R.id.fl_main_content);
        //实例化布局
        rg_bottom_tag = (RadioGroup) findViewById(R.id.rg_bottom_tag);
        //通过布局找到元素，默认选中样式
        rg_bottom_tag.check(R.id.rb_video);
    }
}
