package com.binweather.android.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.binweather.android.R;
import com.binweather.android.gson.Constellation;
import com.binweather.android.gson.News;
import com.binweather.android.util.HttpUtil;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ConstellationActivity extends AppCompatActivity implements View.OnClickListener {
    private String name = "白羊座";
    private String type = "today";
    private TextView con_text;
    private ImageView con_image;
    private TextView con_all;
    private TextView con_work;
    private TextView con_money;
    private TextView con_love;
    private TextView con_health;
    private TextView con_summary;
    private TextView con_number;
    private LinearLayout con_Aries;
    private LinearLayout con_Taurus;
    private LinearLayout con_Gemini;
    private LinearLayout con_Cancer;
    private LinearLayout con_Leo;
    private LinearLayout con_Virgo;
    private LinearLayout con_Libra;
    private LinearLayout con_Scorpio;
    private LinearLayout con_Sag;
    private LinearLayout con_Capricorn;
    private LinearLayout con_Aquarius;
    private LinearLayout con_Pisces;
    private TextView con_today;
    private TextView con_tomorrow;
    private TextView con_next;
    private TextView con_week;
    private TextView con_month;
    private TextView con_year;
    private ImageView model_con_image;
    private TextView model_con_text;
    private LinearLayout model_weather;
    private LinearLayout model_news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constellation);
        con_all = (TextView) findViewById(R.id.con_all);
        con_work = (TextView) findViewById(R.id.con_work);
        con_money = (TextView) findViewById(R.id.con_money);
        con_love = (TextView) findViewById(R.id.con_love);
        con_health = (TextView) findViewById(R.id.con_health);
        con_summary = (TextView) findViewById(R.id.con_summary);
        con_number = (TextView) findViewById(R.id.con_number);
        con_Aries = (LinearLayout) findViewById(R.id.con_Aries);
        con_Taurus = (LinearLayout) findViewById(R.id.con_Taurus);
        con_Gemini = (LinearLayout) findViewById(R.id.con_Gemini);
        con_Cancer = (LinearLayout) findViewById(R.id.con_Cancer);
        con_Leo = (LinearLayout) findViewById(R.id.con_Leo);
        con_Virgo = (LinearLayout) findViewById(R.id.con_Virgo);
        con_Libra = (LinearLayout) findViewById(R.id.con_Libra);
        con_Scorpio = (LinearLayout) findViewById(R.id.con_Scorpio);
        con_Sag = (LinearLayout) findViewById(R.id.con_Sag);
        con_Capricorn = (LinearLayout) findViewById(R.id.con_Cap);
        con_Aquarius = (LinearLayout) findViewById(R.id.con_Aqu);
        con_Pisces = (LinearLayout) findViewById(R.id.con_Pisces);
        con_today = (TextView) findViewById(R.id.con_today);
        con_tomorrow = (TextView) findViewById(R.id.con_tomorrow);
        con_week = (TextView) findViewById(R.id.con_week);
        con_next = (TextView) findViewById(R.id.con_next);
        con_month = (TextView) findViewById(R.id.con_month);
        con_year = (TextView) findViewById(R.id.con_year);
        con_text = (TextView) findViewById(R.id.con_text);
        con_image = (ImageView) findViewById(R.id.con_image);
        model_con_image=(ImageView)findViewById(R.id.model_con_image);
        model_con_text=(TextView)findViewById(R.id.model_con_text);
        model_news=(LinearLayout)findViewById(R.id.model_news);
        model_weather=(LinearLayout)findViewById(R.id.model_weather);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        con_Aries.setOnClickListener(this);
        con_Taurus.setOnClickListener(this);
        con_Gemini.setOnClickListener(this);
        con_Cancer.setOnClickListener(this);
        con_Leo.setOnClickListener(this);
        con_Virgo.setOnClickListener(this);
        con_Libra.setOnClickListener(this);
        con_Scorpio.setOnClickListener(this);
        con_Sag.setOnClickListener(this);
        con_Capricorn.setOnClickListener(this);
        con_Aquarius.setOnClickListener(this);
        con_Pisces.setOnClickListener(this);
        con_today.setOnClickListener(this);
        con_tomorrow.setOnClickListener(this);
        con_week.setOnClickListener(this);
        con_next.setOnClickListener(this);
        con_month.setOnClickListener(this);
        con_year.setOnClickListener(this);
        model_news.setOnClickListener(this);
        model_weather.setOnClickListener(this);
        requestCon("白羊座", "today");
        con_today.setTextColor(getResources().getColorStateList(R.color.colorPrimary));
        model_con_text.setTextColor(getResources().getColorStateList(R.color.color_blue));
        model_con_image.setBackground(getResources().getDrawable(R.drawable.weather_101_blue));
    }

    private void requestCon(String name, String type) {

        String conUrl = "http://apis.haoservice.com/lifeservice/constellation/GetAll?key=b963de2244dd4123aafdf59fb2815204&consName=" + name + "&type=" + type + "&paybyvas=false";
        HttpUtil.sendOkHttpRequest(conUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Constellation constellation = gson.fromJson(responseText, Constellation.class);
                        if (constellation.error_code!=0){
                            Toast.makeText(ConstellationActivity.this, "获取星座运势失败", Toast.LENGTH_SHORT).show();
                        }else {
                        showConInfo(constellation);}
                    }
                });
            }
        });
    }

    private void showConInfo(Constellation constellation) {
        String all = constellation.result_content.all;
        String work = constellation.result_content.work;
        String money = constellation.result_content.money;
        String love = constellation.result_content.love;
        String health = constellation.result_content.health;
        String number = constellation.result_content.number;
        String summary = constellation.result_content.summary;
        con_all.setText(all);
        con_work.setText(work);
        con_money.setText(money);
        con_love.setText(love);
        con_health.setText(health);
        con_number.setText(number);
        con_summary.setText(summary);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.con_Aries:
                name = "白羊座";

                con_text.setText("白羊座");
                con_image.setBackground(getResources().getDrawable(R.drawable.con_1));
                requestCon(name, type);

                break;
            case R.id.con_Taurus:
                name = "金牛座";
                con_text.setText("金牛座");
                con_image.setBackground(getResources().getDrawable(R.drawable.con_2));
                requestCon(name, type);
                break;
            case R.id.con_Gemini:
                name = "双子座";
                con_text.setText("双子座");
                con_image.setBackground(getResources().getDrawable(R.drawable.con_3));
                requestCon(name, type);
                break;
            case R.id.con_Cancer:
                name = "巨蟹座";
                con_text.setText("巨蟹座");
                con_image.setBackground(getResources().getDrawable(R.drawable.con_4));
                requestCon(name, type);
                break;
            case R.id.con_Leo:
                name = "狮子座";
                con_text.setText("狮子座");
                con_image.setBackground(getResources().getDrawable(R.drawable.con_5));
                requestCon(name, type);
                break;
            case R.id.con_Virgo:
                name = "处女座";
                con_text.setText("处女座");
                con_image.setBackground(getResources().getDrawable(R.drawable.con_6));
                requestCon(name, type);
                break;
            case R.id.con_Libra:
                name = "天秤座";
                con_text.setText("天秤座");
                con_image.setBackground(getResources().getDrawable(R.drawable.con_7));
                requestCon(name, type);
                break;
            case R.id.con_Scorpio:
                name = "天蝎座";
                con_text.setText("天蝎座");
                con_image.setBackground(getResources().getDrawable(R.drawable.con_8));
                requestCon(name, type);
                break;
            case R.id.con_Sag:
                name = "射手座";
                con_text.setText("射手座");
                con_image.setBackground(getResources().getDrawable(R.drawable.con_9));
                requestCon(name, type);
                break;
            case R.id.con_Cap:
                name = "摩羯座";
                con_text.setText("摩羯座");
                con_image.setBackground(getResources().getDrawable(R.drawable.con_10));
                requestCon(name, type);
                break;
            case R.id.con_Aqu:
                name = "水瓶座";
                con_text.setText("水瓶座");
                con_image.setBackground(getResources().getDrawable(R.drawable.con_11));
                requestCon(name, type);
                break;
            case R.id.con_Pisces:
                name = "双鱼座";
                con_text.setText("双鱼座");
                con_image.setBackground(getResources().getDrawable(R.drawable.con_12));
                requestCon(name, type);
                break;
            case R.id.con_today:
                type = "today";
                con_today.setTextColor(getResources().getColorStateList(R.color.colorPrimary));
                con_tomorrow.setTextColor(getResources().getColorStateList(R.color.color_white));
                requestCon(name, type);
                break;
            case R.id.con_tomorrow:
                type = "tomorrow";
                con_tomorrow.setTextColor(getResources().getColorStateList(R.color.colorPrimary));
                con_today.setTextColor(getResources().getColorStateList(R.color.color_white));
                requestCon(name, type);
                break;
            case R.id.model_news:
                Intent intent=new Intent(this, NewsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.model_weather:
                Intent intent_weather=new Intent(this,WeatherActivity.class);
                intent_weather.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent_weather);
                break;
        /*    case R.id.con_week:
                type="week";
                requestCon(name,type);
                break;
            case R.id.con_next:
                type="nextweek";
                requestCon(name,type);
                break;
            case R.id.con_month:
                type="month";
                requestCon(name,type);
                break;
            case R.id.con_year:
                type="year";
                requestCon(name,type);
                break;*/
        default:
            break;
        }
    }
}
