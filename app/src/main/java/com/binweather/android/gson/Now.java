package com.binweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 彬少 on 2017/11/23.
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;
    @SerializedName("cond")
    public More more;
    public class More{
        @SerializedName("txt")
        public String info;
    }
}
