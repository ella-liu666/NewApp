package com.haixiajiemei.app.Module.Setting.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PayRequest implements Serializable {

    @SerializedName("orderNo")
    private String orderNo;

    @SerializedName("responseBody")
    private String responseBody;


    public String getOrderNo() {
        return orderNo;
    }

    public String getBody() {
        return responseBody;
    }
}
