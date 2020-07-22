package com.haixiajiemei.app.Module.Setting.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Expenses implements Serializable {

    @SerializedName("pointTradeID")//交易序號
    private int pointTradeID;

    @SerializedName("pointTradeTime")//交易時間
    private String pointTradeTime;

    @SerializedName("orderNo")//訂單編號
    private String orderNo;

    @SerializedName("tradeFrom")//交易來源
    private String tradeFrom;

    @SerializedName("tradeName")//消費
    private String tradeName;

    @SerializedName("pointCategory")//錢包類別
    private String pointCategory;

    @SerializedName("point")//點數
    private float point;



    public int getPointTradeID() {
        return pointTradeID;
    }

    public String getPointTradeTime() {
        return pointTradeTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public String getTradeFrom() {
        return tradeFrom;
    }

    public String getTradeName() {
        return tradeName;
    }

    public String getPointCategory() {
        return pointCategory;
    }

    public float getPoint() {
        return point;
    }
}
