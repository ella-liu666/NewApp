package com.haixiajiemei.app.Module.Setting.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WxResponseBody implements Serializable {

    @SerializedName("appid")
    private String appid;

    @SerializedName("partnerid")
    private String partnerid;

    @SerializedName("prepayid")
    private String prepayid;

    @SerializedName("package")
    private String mpackage;

    @SerializedName("noncestr")
    private String noncestr;

    @SerializedName("timestamp")
    private String timestamp;

    @SerializedName("sign")
    private String sign;


    public String getAppid() {
        return appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public String getMpackage() {
        return mpackage;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getSign() {
        return sign;
    }
}