package com.binweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 彬少 on 2017/12/4.
 */

public class Constellation {
    public int error_code;
    public String reason;
    @SerializedName("result")
    public result result_content;
    public class result{
        public String name;
        public String all;
        public String color;
        public String health;
        public String love;
        public String money;
        public String number;
        public String OFriend;
        public String summary;
        public String work;
    }
}
