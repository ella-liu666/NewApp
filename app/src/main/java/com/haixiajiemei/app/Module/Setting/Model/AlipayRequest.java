package com.haixiajiemei.app.Module.Setting.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AlipayRequest implements Serializable {

    @SerializedName("orderNo")
    private String orderNo;

    @SerializedName("aliBody")
    private String aliBody;


    public String getOrderNo() {
        return orderNo;
    }

    public String getAliBody() {
        return aliBody;
    }
}
