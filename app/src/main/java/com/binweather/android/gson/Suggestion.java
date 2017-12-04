package com.binweather.android.gson;

import com.google.gson.annotations.SerializedName;

import java.security.PublicKey;

/**
 * Created by 彬少 on 2017/11/23.
 */

public class Suggestion {
    @SerializedName("comf")
    public Comfort comfort;
    @SerializedName("cw")
    public CarWash carWash;
    @SerializedName("drsg")
    public Dress dress;
    @SerializedName("flu")
    public Cold cold;
    @SerializedName("trav")
    public Travel travel;
    @SerializedName("uv")
    public Ultraviolet ultraviolet;
    public Air air;
    public Sport sport;
    public class Comfort{
        @SerializedName("brf")
        public String info;
    }
    public class CarWash{
        @SerializedName("brf")
        public String info;
    }
    public class Sport{
        @SerializedName("brf")
        public String info;
    }
    public class Dress{
        @SerializedName("brf")
        public String info;
    }
    public class Cold{
        @SerializedName("brf")
        public String info;
    }
    public class Travel{
        @SerializedName("brf")
        public String info;
    }
    public class Ultraviolet{
        @SerializedName("brf")
        public String info;
    }
    public class Air{
        @SerializedName("brf")
        public String info;
    }
}
