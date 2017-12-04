package com.binweather.android.db;

/**
 * Created by 彬少 on 2017/12/4.
 */

public class Concontent {
    /*{
    "error_code": 0,
    "reason": "成功",
    "result": {
        "name": "狮子座",
        "datetime": "2017年12月04日",
        "date": "2017-12-04",
        "all": "56%",
        "color": "紫罗兰色",
        "health": "80%",
        "love": "75%",
        "money": "52%",
        "number": "9",
        "OFriend": "水瓶座",
        "summary": "今日狮子座整体运势总体来说不太好，一整天下来都没有做什么有意义的事情，感到有些空虚，或许应该主动去找一些事情来做，将日子过得更有味道，也让自己感到充实。今日爱情运势一般般，遇到不开心的事情不要自己憋着，要说出来共同解决。今日工作运势不好，可能有同事嫉妒你的才华，找机会陷害你。今日财运不太好，遇到借钱的事情要多留个心眼。今日健康运势总体来说还不错，空闲的时候多出去走动走动。",
        "work": "68%"
    }
}
    * */
    private String error_code;
    private String reason;
    private String result;
    public void setError_code(String error_code){
        this.error_code=error_code;
    }
    public void setReason(String reason){
        this.reason=reason;
    }
    public void setResult(String result){
        this.result=result;
    }
    public String getError_code(){
        return error_code;
    }
    public String getReason(){
        return reason;
    }
    public String getResult(){
        return result;
    }
}
