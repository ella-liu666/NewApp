package com.haixiajiemei.member.Module.Setting.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

public class CardDetail implements Serializable {

    @SerializedName("cardName")
    private String cardName;

    @SerializedName("cardPrice")//原價
    private float cardPrice;

    @SerializedName("cardImg")
    private URL img;

    @SerializedName("cardMsg")//放置卡片上方
    private String cardMsg;

    @SerializedName("cardInfo")//福利特權
    private String cardInfo;

    @SerializedName("cardBev")//優惠福利
    private List<CardBev> cardBev;

    @SerializedName("buyStatus")//卡片狀態
    private CardStatus buyStatus;



    public String getCardName() {
        return cardName;
    }

    public float getCardPrice() {
        return cardPrice;
    }

    public URL getImg() {
        return img;
    }

    public String getCardMsg() {
        return cardMsg;
    }

    public String getCardInfo() {
        return cardInfo;
    }

    public List<CardBev> getCardBev() {
        return cardBev;
    }

    public CardStatus getBuyStatus() {
        return buyStatus;
    }
}
