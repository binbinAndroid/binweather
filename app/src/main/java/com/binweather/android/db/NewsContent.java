package com.binweather.android.db;

import android.graphics.Bitmap;

/**
 * Created by 彬少 on 2017/11/30.
 */

public class NewsContent {
    private String title;
    private String author_name;
    private String thumbnail_pic_s;
    private String url;
    public String getTitle(){
        return title;
    }
    public String getThumbnail_pic_s(){
        return thumbnail_pic_s;
    }
    public String getUrl(){
        return url;
    }
    public String getAuthor_name(){
        return author_name;
    }
   public NewsContent(String title,String author_name,String thumbnail_pic_s,String url){
       this.title=title;
       this.author_name=author_name;
       this.thumbnail_pic_s=thumbnail_pic_s;
       this.url=url;
   }
}
