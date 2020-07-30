package com.haixiajiemei.app.Module.Setting.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WxResponseBody implements Serializable {

    @SerializedName("return_code")
    private String return_code;

    @SerializedName("return_msg")
    private String return_msg;

    @SerializedName("appid")
    private String appid;

    @SerializedName("mch_id")
    private String mch_id;

    @SerializedName("nonce_str")
    private String nonce_str;

    @SerializedName("sign")
    private String sign;

    @SerializedName("result_code")
    private String result_code;

    @SerializedName("prepay_id")
    private String prepay_id;

    @SerializedName("trade_type")
    private String trade_type;





    public String getReturn_code() {
        return return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public String getAppid() {
        return appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public String getTrade_type() {
        return trade_type;
    }
}
