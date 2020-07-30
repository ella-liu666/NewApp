package com.haixiajiemei.app.Module.Setting.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WxPayRequest implements Serializable {

    @SerializedName("orderNo")
    private String orderNo;

    @SerializedName("responseBody")
    private WxResponseBody responseBody;

    @SerializedName("timeStamp")
    private String timeStamp;


    public String getOrderNo() {
        return orderNo;
    }

    public WxResponseBody getResponseBody() {
        return responseBody;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
