package com.binweather.android.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 彬少 on 2017/11/30.
 */

public class News {
    public String reason;
    public Result result;
    public class Result {
        public String stat;
        @SerializedName("data")
        public List<Datas> dataList;
    }
    public String error_code;
}
