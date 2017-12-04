package com.binweather.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.binweather.android.NewsContentAdapter;
import com.binweather.android.R;
import com.binweather.android.db.NewsContent;
import com.binweather.android.gson.Datas;
import com.binweather.android.gson.News;
import com.binweather.android.util.HttpUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.binweather.android.R.id.text;

/**
 * Created by 彬少 on 2017/11/30.
 */

public class NewsActivity extends Activity implements View.OnClickListener {
    private ListView listView;
    private TextView news_top;
    private TextView news_shehui;
    private TextView news_guonei;
    private TextView news_guoji;
    private TextView news_yule;
    private TextView news_tiyu;
    private TextView news_junshi;
    private TextView news_keji;
    private TextView news_caijing;
    private TextView news_shishang;
    private LinearLayout news_lin;
    private ImageView model_news_image;
    private TextView model_news_text;
    private LinearLayout model_weather;
    private LinearLayout model_Con;
    private List<NewsContent> newsContentsList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        listView = (ListView) findViewById(R.id.newsListView);
        news_top = (TextView) findViewById(R.id.news_top);
        news_caijing = (TextView) findViewById(R.id.news_caijing);
        news_guoji = (TextView) findViewById(R.id.news_guoji);
        news_guonei = (TextView) findViewById(R.id.news_guonei);
        news_keji = (TextView) findViewById(R.id.news_keji);
        news_junshi = (TextView) findViewById(R.id.news_junshi);
        news_shehui = (TextView) findViewById(R.id.news_shehui);
        news_shishang = (TextView) findViewById(R.id.news_shishang);
        news_tiyu = (TextView) findViewById(R.id.news_tiyu);
        news_yule = (TextView) findViewById(R.id.news_yule);
        news_lin=(LinearLayout)findViewById(R.id.news_model_lin);
        model_news_image=(ImageView)findViewById(R.id.model_news_image);
        model_news_text=(TextView)findViewById(R.id.model_news_text);
        model_weather=(LinearLayout)findViewById(R.id.model_weather);
        model_Con=(LinearLayout)findViewById(R.id.model_Con);
        news_top.setOnClickListener(this);
        news_caijing.setOnClickListener(this);
        news_guonei.setOnClickListener(this);
        news_guoji.setOnClickListener(this);
        news_tiyu.setOnClickListener(this);
        news_shishang.setOnClickListener(this);
        news_yule.setOnClickListener(this);
        news_keji.setOnClickListener(this);
        news_shehui.setOnClickListener(this);
        news_junshi.setOnClickListener(this);
        model_weather.setOnClickListener(this);
        model_Con.setOnClickListener(this);
        news_top.setTextColor(getResources().getColorStateList(R.color.color_white));
        news_lin.setBackgroundColor(getResources().getColor(R.color.color_news_gray));
        model_news_image.setBackground(getResources().getDrawable(R.drawable.weather_103_blue));
        model_news_text.setTextColor(getResources().getColorStateList(R.color.color_blue));
        requestNews("top");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.news_top:
                requestNews("top");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case R.id.news_guonei:
                requestNews("guonei");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 2;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case R.id.news_guoji:
                requestNews("guoji");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 3;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case R.id.news_junshi:
                requestNews("junshi");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 4;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case R.id.news_shishang:
                requestNews("shishang");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 5;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case R.id.news_keji:
                requestNews("keji");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 6;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case R.id.news_shehui:
                requestNews("shehui");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 7;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case R.id.news_yule:
                requestNews("yule");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 8;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case R.id.news_tiyu:
                requestNews("tiyu");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 9;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case R.id.news_caijing:
                requestNews("caijing");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 10;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case R.id.model_weather:
                Intent intent=new Intent(this,WeatherActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.model_Con:
                Intent Con_intent=new Intent(this,ConstellationActivity.class);
                Con_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(Con_intent);
            default:
                break;
        }
    }

    private void requestNews(String type) {
        String newsUrl = "http://v.juhe.cn/toutiao/index?type=" + type + "&key=1ccb1f1259db99a84f7ac7de36649f03";
        HttpUtil.sendOkHttpRequest(newsUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(NewsActivity.this, "获取新闻失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        News news = gson.fromJson(responseText, News.class);
                        listView.setAdapter(null);
                        if (news.error_code.equals("10012")) {
                            Toast.makeText(NewsActivity.this, "获取新闻失败", Toast.LENGTH_SHORT).show();
                        } else {
                            showNewsInfo(news);
                        }
                    }
                });
            }
        });
    }

    private void showNewsInfo(News news) {
        NewsContentAdapter newsContentAdapter = new NewsContentAdapter(NewsActivity.this, R.layout.news_item, newsContentsList);
        newsContentsList.clear();
        for (Datas datas : news.result.dataList) {
            String title = datas.title;
            String author = datas.author_name;
            String thumbnail_pic_s = datas.thumbnail_pic_s;
            String url = datas.url;
            if (title != null & author != null) {
                NewsContent newsContent = new NewsContent(title, author, thumbnail_pic_s, url);
                newsContentsList.add(newsContent);
            }
        }
        listView.setAdapter(newsContentAdapter);
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    news_top.setTextColor(getResources().getColorStateList(R.color.color_white));
                    news_guonei.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_guoji.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_shehui.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_yule.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_tiyu.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_junshi.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_caijing.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_shishang.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_keji.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    break;
                case 2:
                    news_top.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_guonei.setTextColor(getResources().getColorStateList(R.color.color_white));
                    news_guoji.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_shehui.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_yule.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_tiyu.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_junshi.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_caijing.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_shishang.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_keji.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    break;
                case 3:
                    news_top.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_guonei.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_guoji.setTextColor(getResources().getColorStateList(R.color.color_white));
                    news_shehui.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_yule.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_tiyu.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_junshi.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_caijing.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_shishang.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_keji.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    break;
                case 4:
                    news_top.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_guonei.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_guoji.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_shehui.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_yule.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_tiyu.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_junshi.setTextColor(getResources().getColorStateList(R.color.color_white));
                    news_caijing.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_shishang.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_keji.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    break;
                case 5:
                    news_top.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_guonei.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_guoji.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_shehui.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_yule.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_tiyu.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_junshi.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_caijing.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_shishang.setTextColor(getResources().getColorStateList(R.color.color_white));
                    news_keji.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    break;
                case 6:
                    news_top.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_guonei.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_guoji.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_shehui.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_yule.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_tiyu.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_junshi.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_caijing.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_shishang.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_keji.setTextColor(getResources().getColorStateList(R.color.color_white));
                    break;
                case 7:
                    news_top.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_guonei.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_guoji.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_shehui.setTextColor(getResources().getColorStateList(R.color.color_white));
                    news_yule.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_tiyu.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_junshi.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_caijing.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_shishang.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_keji.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    break;
                case 8:
                    news_top.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_guonei.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_guoji.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_shehui.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_yule.setTextColor(getResources().getColorStateList(R.color.color_white));
                    news_tiyu.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_junshi.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_caijing.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_shishang.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_keji.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    break;
                case 9:
                    news_top.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_guonei.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_guoji.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_shehui.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_yule.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_tiyu.setTextColor(getResources().getColorStateList(R.color.color_white));
                    news_junshi.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_caijing.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_shishang.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_keji.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    break;
                case 10:
                    news_top.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_guonei.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_guoji.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_shehui.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_yule.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_tiyu.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_junshi.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_caijing.setTextColor(getResources().getColorStateList(R.color.color_white));
                    news_shishang.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    news_keji.setTextColor(getResources().getColorStateList(R.color.color_news_gray));
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
