package com.binweather.android.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.binweather.android.R;
import com.binweather.android.gson.Forecast;
import com.binweather.android.gson.Weather;
import com.binweather.android.service.AutoUpdateService;
import com.binweather.android.util.HttpUtil;
import com.binweather.android.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity implements View.OnClickListener {
    public DrawerLayout drawerLayout;
    private Button navButton;
   // public SwipeRefreshLayout swipeRefresh;
    public String mWeatherId;
    private ScrollView weatherLayout;
    private TextView titleCity;
    private TextView titleUpdateTime;
    private TextView degreeText;
    private TextView weatherInfoText;
    private LinearLayout forecastLayout;
    private TextView aqiText;
    private TextView pm25Text;
    private TextView comfortText;
    private TextView carWashText;
    private TextView sportText;
    private ImageView weatherPicture;
    private TextView qualityText;
    private TextView dressText;
    private TextView pillText;
    private TextView travelText;
    private TextView uvText;
    private TextView airText;
    private LinearLayout newsModel;
    private LinearLayout weatherModel;
    private LinearLayout conModel;
    private ImageView model_weather_image;
    private TextView model_weather_text;
    private LinearLayout model_Con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        if (Build.VERSION.SDK_INT>=21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        weatherLayout = (ScrollView) findViewById(R.id.weather_layout);
        titleCity = (TextView) findViewById(R.id.title_city);
        titleUpdateTime = (TextView) findViewById(R.id.title_update_time);
        degreeText = (TextView) findViewById(R.id.degree_text);
        weatherInfoText = (TextView) findViewById(R.id.weather_info_text);
        forecastLayout = (LinearLayout) findViewById(R.id.forecast_layout);
        aqiText = (TextView) findViewById(R.id.api_text);
        pm25Text = (TextView) findViewById(R.id.pm25_text);
        comfortText = (TextView) findViewById(R.id.weather_sofa);
        carWashText = (TextView) findViewById(R.id.weather_car);
        pillText=(TextView)findViewById(R.id.weather_pill);
        airText=(TextView)findViewById(R.id.weather_pollute);
        travelText=(TextView)findViewById(R.id.weather_coconut);
        uvText=(TextView)findViewById(R.id.weather_shine);
        dressText=(TextView)findViewById(R.id.weather_dress);
        qualityText=(TextView)findViewById(R.id.api_quality);
        sportText = (TextView) findViewById(R.id.weather_sport);
        drawerLayout=(DrawerLayout)findViewById(R.id.draw_layout);
        newsModel=(LinearLayout)findViewById(R.id.model_news);
        navButton=(Button)findViewById(R.id.nav_button);
       // swipeRefresh=(SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
      //  swipeRefresh.setColorSchemeColors(R.color.colorPrimary);
        weatherPicture=(ImageView)findViewById(R.id.weather_picture);
        weatherModel=(LinearLayout)findViewById(R.id.weather_model);
        conModel=(LinearLayout)findViewById(R.id.model_Con);
        model_weather_image=(ImageView)findViewById(R.id.model_weather_image);
        model_weather_text=(TextView)findViewById(R.id.model_weather_text);
        model_Con=(LinearLayout)findViewById(R.id.model_Con);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = prefs.getString("weather", null);
        if (weatherString != null) {
            Weather weather = Utility.handlerWeatherResponse(weatherString);
            mWeatherId=weather.basic.weatherId;
            showWeatherInfo(weather);
        } else {
            mWeatherId=getIntent().getStringExtra("weather_id");
            weatherLayout.setVisibility(View.INVISIBLE);
            requestWeather(mWeatherId);
        }
        /*swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                requestWeather(mWeatherId);
            }
        });*/
        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
               // weatherModel.setVisibility(View.INVISIBLE);
            }
        });
        newsModel.setOnClickListener(this);
        conModel.setOnClickListener(this);
       // drawerLayout.setDrawerListener(new MyDrawerListener());
        model_weather_image.setBackground(getResources().getDrawable(R.drawable.weather_100_blue));
        model_weather_text.setTextColor(getResources().getColorStateList(R.color.color_blue));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.model_news:
                Intent intent=new Intent(this,NewsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.model_Con:
                Intent Con_intent=new Intent(this,ConstellationActivity.class);
                Con_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(Con_intent);
        }
    }

    public void requestWeather(final String weatherId) {
        String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=3f1184e2786046879e80a386d5de5ae3";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        //swipeRefresh.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = Utility.handlerWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && "ok".equals(weather.status)) {
                            SharedPreferences.Editor editor = PreferenceManager
                                    .getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString("weather", responseText);
                            editor.apply();
                            showWeatherInfo(weather);
                        } else {
                            Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                        //swipeRefresh.setRefreshing(false);
                    }
                });
            }
        });
    }

    private void showWeatherInfo(Weather weather) {
        String cityName = weather.basic.cityName;
        String updateTime = weather.basic.update.updateTime.split(" ")[1];
        String degree = weather.now.temperature + "℃";
        final String weatherInfo = weather.now.more.info;
        sendMessage(weatherInfo);
        titleCity.setText(cityName);
        titleUpdateTime.setText(updateTime);
        degreeText.setText(degree);
        weatherInfoText.setText(weatherInfo);
        forecastLayout.removeAllViews();
        for (Forecast forecast : weather.forecastList) {
            View view = LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false);
            TextView dataText = (TextView)view.findViewById(R.id.date_text);
            TextView infoText = (TextView)view.findViewById(R.id.info_text);
            TextView maxText = (TextView)view.findViewById(R.id.max_text);
            TextView minText = (TextView)view.findViewById(R.id.min_text);
            dataText.setText(forecast.date);
            infoText.setText(forecast.more.info);
            maxText.setText(forecast.temperature.max+"℃");
            minText.setText(forecast.temperature.min+"℃");
            forecastLayout.addView(view);
        }
        if (weather.aqi != null) {
            aqiText.setText(weather.aqi.city.aqi);
            pm25Text.setText(weather.aqi.city.pm25);
            qualityText.setText(weather.aqi.city.quality);
        }

        comfortText.setText(weather.suggestion.comfort.info);
        carWashText.setText(weather.suggestion.carWash.info);
        sportText.setText(weather.suggestion.sport.info);
        pillText.setText(weather.suggestion.cold.info);
        dressText.setText(weather.suggestion.dress.info);
        travelText.setText(weather.suggestion.travel.info);
        airText.setText(weather.suggestion.air.info);
        uvText.setText(weather.suggestion.ultraviolet.info);
        weatherLayout.setVisibility(View.VISIBLE);
        Intent intent=new Intent(this,AutoUpdateService.class);
        intent.putExtra("weather_cityName",cityName);
        intent.putExtra("weather_degree",degree);
        intent.putExtra("weather_weatherInfo",weatherInfo);
        startService(intent);


    }
    private void sendMessage(String mWeatherId){
        switch (mWeatherId){
            case "晴":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=100;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "多云":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=101;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "少云":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=102;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "晴间多云":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=103;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "阴":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=104;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "有风":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=200;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "平静":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=201;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "微风":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=202;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "和风":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=203;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "清风":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=204;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "强风/劲风":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=205;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "疾风":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=206;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "大风":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=207;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "烈风":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=208;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "风暴":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=209;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "狂暴风":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=210;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "飓风":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=211;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "龙卷风":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=212;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "热带风暴":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=213;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "阵雨":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=300;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "强阵雨":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=301;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "雷阵雨":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=302;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "强雨阵雨":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=303;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "雷阵雨伴有冰雹":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=304;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "小雨":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=305;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "中雨":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=306;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "大雨":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=307;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "极端降雨":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=308;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "毛毛细雨/细雨":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=309;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "暴雨":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=310;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "大暴雨":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=311;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "特大暴雨":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=312;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "冻雨":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=313;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "小雪":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=400;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "中雪":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=401;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "大雪":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=402;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "暴雪":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=403;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "雨夹雪":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=404;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "雨雪天气":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=405;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "阵雨夹雪":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=406;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "阵雪":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=407;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "薄雪":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=500;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "雾":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=501;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "霾":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=502;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "扬沙":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=503;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "浮尘":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=504;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "沙尘暴":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=507;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "强沙风暴":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=508;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "热":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=900;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "冷":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=901;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case "未知":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=999;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            default:
                break;
        }
    }
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    weatherPicture.setBackgroundResource(R.drawable.weather_100);
                    break;
                case 101:
                    weatherPicture.setBackgroundResource(R.drawable.weather_101);
                    break;
                case 102:
                    weatherPicture.setBackgroundResource(R.drawable.weather_102);
                    break;
                case 103:
                    weatherPicture.setBackgroundResource(R.drawable.weather_103);
                    break;
                case 104:
                    weatherPicture.setBackgroundResource(R.drawable.weather_104);
                    break;
                case 200:
                    weatherPicture.setBackgroundResource(R.drawable.weather_200);
                    break;
                case 201:
                    weatherPicture.setBackgroundResource(R.drawable.weather_201);
                    break;
                case 202:
                    weatherPicture.setBackgroundResource(R.drawable.weather_202);
                    break;
                case 203:
                    weatherPicture.setBackgroundResource(R.drawable.weather_203);
                    break;
                case 204:
                    weatherPicture.setBackgroundResource(R.drawable.weather_204);
                    break;
                case 205:
                    weatherPicture.setBackgroundResource(R.drawable.weather_205);
                    break;
                case 206:
                    weatherPicture.setBackgroundResource(R.drawable.weather_206);
                    break;
                case 207:
                    weatherPicture.setBackgroundResource(R.drawable.weather_207);
                    break;
                case 208:
                    weatherPicture.setBackgroundResource(R.drawable.weather_208);
                    break;
                case 209:
                    weatherPicture.setBackgroundResource(R.drawable.weather_209);
                    break;
                case 210:
                    weatherPicture.setBackgroundResource(R.drawable.weather_210);
                    break;
                case 211:
                    weatherPicture.setBackgroundResource(R.drawable.weather_211);
                    break;
                case 212:
                    weatherPicture.setBackgroundResource(R.drawable.weather_212);
                    break;
                case 213:
                    weatherPicture.setBackgroundResource(R.drawable.weather_213);
                    break;
                case 300:
                    weatherPicture.setBackgroundResource(R.drawable.weather_300);
                    break;
                case 301:
                    weatherPicture.setBackgroundResource(R.drawable.weather_301);
                    break;
                case 302:
                    weatherPicture.setBackgroundResource(R.drawable.weather_302);
                    break;
                case 303:
                    weatherPicture.setBackgroundResource(R.drawable.weather_303);
                    break;
                case 304:
                    weatherPicture.setBackgroundResource(R.drawable.weather_304);
                    break;
                case 305:
                    weatherPicture.setBackgroundResource(R.drawable.weather_305);
                    break;
                case 306:
                    weatherPicture.setBackgroundResource(R.drawable.weather_306);
                    break;
                case 307:
                    weatherPicture.setBackgroundResource(R.drawable.weather_307);
                    break;
                case 308:
                    weatherPicture.setBackgroundResource(R.drawable.weather_308);
                    break;
                case 309:
                    weatherPicture.setBackgroundResource(R.drawable.weather_309);
                    break;
                case 310:
                    weatherPicture.setBackgroundResource(R.drawable.weather_310);
                    break;
                case 311:
                    weatherPicture.setBackgroundResource(R.drawable.weather_311);
                    break;
                case 312:
                    weatherPicture.setBackgroundResource(R.drawable.weather_312);
                    break;
                case 313:
                    weatherPicture.setBackgroundResource(R.drawable.weather_313);
                    break;
                case 400:
                    weatherPicture.setBackgroundResource(R.drawable.weather_400);
                    break;
                case 401:
                    weatherPicture.setBackgroundResource(R.drawable.weather_401);
                    break;
                case 402:
                    weatherPicture.setBackgroundResource(R.drawable.weather_402);
                    break;
                case 403:
                    weatherPicture.setBackgroundResource(R.drawable.weather_403);
                    break;
                case 404:
                    weatherPicture.setBackgroundResource(R.drawable.weather_404);
                    break;
                case 405:
                    weatherPicture.setBackgroundResource(R.drawable.weather_405);
                    break;
                case 406:
                    weatherPicture.setBackgroundResource(R.drawable.weather_406);
                    break;
                case 407:
                    weatherPicture.setBackgroundResource(R.drawable.weather_407);
                    break;
                case 500:
                    weatherPicture.setBackgroundResource(R.drawable.weather_500);
                    break;
                case 501:
                    weatherPicture.setBackgroundResource(R.drawable.weather_501);
                    break;
                case 502:
                    weatherPicture.setBackgroundResource(R.drawable.weather_502);
                    break;
                case 503:
                    weatherPicture.setBackgroundResource(R.drawable.weather_503);
                    break;
                case 504:
                    weatherPicture.setBackgroundResource(R.drawable.weather_504);
                    break;
                case 507:
                    weatherPicture.setBackgroundResource(R.drawable.weather_507);
                    break;
                case 508:
                    weatherPicture.setBackgroundResource(R.drawable.weather_508);
                    break;
                case 900:
                    weatherPicture.setBackgroundResource(R.drawable.weather_900);
                    break;
                case 901:
                    weatherPicture.setBackgroundResource(R.drawable.weather_901);
                    break;
                case 999:
                    weatherPicture.setBackgroundResource(R.drawable.weather_999);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onRestart() {
        super.onRestart();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = prefs.getString("weather", null);
        if (weatherString != null) {
            Weather weather = Utility.handlerWeatherResponse(weatherString);
            mWeatherId=weather.basic.weatherId;
            showWeatherInfo(weather);
        } else {
            mWeatherId=getIntent().getStringExtra("weather_id");
            weatherLayout.setVisibility(View.INVISIBLE);
            requestWeather(mWeatherId);
        }
    }
   private class MyDrawerListener implements DrawerLayout.DrawerListener{
        @Override
        public void onDrawerClosed(View drawerView) {
            weatherModel.setVisibility(View.VISIBLE);
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            weatherModel.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    }


}
