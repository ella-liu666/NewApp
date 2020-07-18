package com.haixiajiemei.member.Module.Setting.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Recharge implements Serializable {

    @SerializedName("pointTradeTime")
    private String pointTradeTime;

    @SerializedName("point")//餘額
    private float point;

    @SerializedName("pointCategory")//錢包類別
    private String pointCategory;

    @SerializedName("pointChargeSource")//支付方式
    private String pointChargeSource;

    @SerializedName("chargeAmount")//金錢
    private float chargeAmount;
    

    public String getPointTradeTime() {
        return pointTradeTime;
    }

    public float getPoint() {
        return point;
    }

    public String getPointCategory() {
        return pointCategory;
    }

    public String getPointChargeSource() {
        return pointChargeSource;
    }

    public float getChargeAmount() {
        return chargeAmount;
    }
}
