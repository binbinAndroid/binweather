package com.binweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 彬少 on 2017/11/23.
 */

public class AQI {
    public AQICity city;
    public class AQICity{
        public String aqi;
        public String pm25;
        @SerializedName("qlty")
        public String quality;
    }
}
