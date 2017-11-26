package com.binweather.android.gson;

/**
 * Created by 彬少 on 2017/11/23.
 */

public class AQI {
    public AQICity city;
    public class AQICity{
        public String aqi;
        public String pm25;
    }
}
