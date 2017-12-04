package com.binweather.android.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v7.app.NotificationCompat;

import com.binweather.android.R;
import com.binweather.android.activity.WeatherActivity;
import com.binweather.android.gson.Weather;
import com.binweather.android.util.HttpUtil;
import com.binweather.android.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AutoUpdateService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updateWeather();
        String degree=intent.getStringExtra("weather_degree");
        String weather_info=intent.getStringExtra("weather_weatherInfo");
        String city_name=intent.getStringExtra("weather_cityName");
        AlarmManager manager=(AlarmManager)getSystemService(ALARM_SERVICE);
        int anHour=30*60*1000;
        long triggerAtTime= SystemClock.elapsedRealtime()+anHour;
        Intent autoIntent=new Intent(this,AutoUpdateService.class);
        PendingIntent pendingIntent=PendingIntent.getService(this,0,autoIntent,0);
        manager.cancel(pendingIntent);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pendingIntent);
        Intent serviceIntent =new Intent(this,WeatherActivity.class);
        PendingIntent pi=PendingIntent.getActivity(this,0,serviceIntent,0);
        if (degree!=null){
        Notification notification=new NotificationCompat.Builder(this)
                .setContentTitle(city_name)
                .setContentText(degree+" "+weather_info)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1,notification);
        }
        return super.onStartCommand(intent, flags, startId);
    }
    private void updateWeather(){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString =preferences.getString("weather",null);
        if (weatherString!=null){
            Weather weather= Utility.handlerWeatherResponse(weatherString);
            String weatherId=weather.basic.weatherId;
            String weatherUrl="http://guolin.tech/api/weather?cityid="+weatherId+"&key=3f1184e2786046879e80a386d5de5ae3";
            HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseText=response.body().string();
                    Weather weather=Utility.handlerWeatherResponse(responseText);
                    if (weather!=null&&"ok".equals(weather.status)){
                        SharedPreferences.Editor editor=PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                        editor.putString("weather",responseText);
                        editor.apply();
                    }
                }
            });
        }
    }

}
