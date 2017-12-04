package com.binweather.android;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.binweather.android.activity.WebActivity;
import com.binweather.android.db.NewsContent;
import com.bumptech.glide.Glide;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by 彬少 on 2017/11/30.
 */

public class NewsContentAdapter extends ArrayAdapter<NewsContent>{
    private View view;
    private int resoureId;
    public NewsContentAdapter(Context context, int textViewResoureId, List<NewsContent>objects){
        super(context,textViewResoureId,objects);
        resoureId=textViewResoureId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final NewsContent newsContent=getItem(position);
        view = LayoutInflater.from(getContext()).inflate(resoureId,parent,false);
        TextView titleText=(TextView)view.findViewById(R.id.news_title);
        TextView authorText=(TextView)view.findViewById(R.id.news_author);
        ImageView imageView=(ImageView)view.findViewById(R.id.news_image);
        titleText.setText(newsContent.getTitle());
        authorText.setText(newsContent.getAuthor_name());
        if (newsContent.getThumbnail_pic_s()!=null) {
            Glide.with(view.getContext()).load(newsContent.getThumbnail_pic_s()).into(imageView);
        }else {
            imageView.setVisibility(View.GONE);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(view.getContext(), WebActivity.class);
                intent.putExtra("URL",newsContent.getUrl());
                intent.putExtra("TITLE",newsContent.getTitle());
                view.getContext().startActivity(intent);
            }
        });

        return view;
    }
}
