package com.example.hejianxin.news.utils;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by yiyua on 2017/3/11.
 */

public class StreamUtils {
    public static String streamToString(InputStream in){
        String result = "";
        try {
            //创建一个字节数组
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            //创建一个字节对象
            byte[] buffer = new byte[1024];
            int length = 0;
            while((length = in.read(buffer)) != -1){
                out.write(buffer,0,length);
                out.flush();
            }
            //将字节流转换为字符串
            result = out.toString();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
