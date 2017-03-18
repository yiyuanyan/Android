package com.example.hejianxin.news.utils;

import android.content.Context;

import com.example.hejianxin.news.R;
import com.example.hejianxin.news.bean.NewsBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by hejianxin on 2017/3/9.
 */

public class NewsUtils {
    public static String newsPath_url = "http://test.gogo-talk.com/";
    //封装数据
    public static ArrayList<NewsBean> getAllNewsForNetWork(Context context) throws MalformedURLException {
        ArrayList<NewsBean> arrayList = new ArrayList<>();
        try {

            //1.请求解析服务器
            URL url =new URL(newsPath_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10*1000);
            int code = connection.getResponseCode();
            if(code == 200){
                InputStream in = connection.getInputStream();
                //转换为字符串
                String result = StreamUtils.streamToString(in);
                //2解析获取的新闻数据到一个List集合中
                JSONObject root_json = new JSONObject(result);
                JSONArray jsonArray = root_json.getJSONArray("news");
                //便利JSONARRAY
                for(int i=0;i < jsonArray.length();i++){
                    //获取一条新闻的JSON
                    JSONObject news_json = jsonArray.getJSONObject(i);
                    NewsBean bean = new NewsBean();
                    bean.id = news_json.getInt("id");
                    bean.comment = news_json.getInt("comment");
                    bean.type = news_json.getInt("type");
                    bean.time = news_json.getString("time");
                    bean.des = news_json.getString("des");
                    bean.title = news_json.getString("title");
                    bean.news_url = news_json.getString("news_url");
                    bean.icon_url = news_json.getString("icon_url");
                    arrayList.add(bean);

                }
            }


            //3将数据缓存到数据库
        }catch(Exception e){

        }

        return arrayList;
    }
}
