package com.haixiajiemei.app.Module.Setting.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

public class CardDetail implements Serializable {

    @SerializedName("cardCategoryID")
    private int cardCategoryID;

    @SerializedName("cardID")
    private int cardID;

    @SerializedName("cardName")
    private String cardName;

    @SerializedName("cardPrice")//原價
    private float cardPrice;

    @SerializedName("upgradeCardPrice")//升級價
    private float upgradeCardPrice;

    @SerializedName("cardImg")
    private URL cardImg;

    @SerializedName("cardMsg")//放置卡片上方
    private String cardMsg;

    @SerializedName("cardInfo")//福利特權
    private String cardInfo;

    @SerializedName("cardCurrentAmount")
    private float cardCurrentAmount;

    @SerializedName("cardBev")//優惠福利
    private List<CardBev> cardBev;

    @SerializedName("cardCoupon")//優惠福利
    private List<CardCoupon> cardCoupon;

    @SerializedName("buyStatus")//卡片狀態
    private CardStatus buyStatus;



    public int getCardCategoryID() {
        return cardCategoryID;
    }

    public int getCardID() {
        return cardID;
    }

    public String getCardName() {
        return cardName;
    }

    public float getCardPrice() {
        return cardPrice;
    }

    public float getUpgradeCardPrice() {
        return upgradeCardPrice;
    }

    public URL getCardImg() {
        return cardImg;
    }

    public String getCardMsg() {
        return cardMsg;
    }

    public String getCardInfo() {
        return cardInfo;
    }

    public float getCardCurrentAmount() {
        return cardCurrentAmount;
    }

    public List<CardBev> getCardBev() {
        return cardBev;
    }

    public CardStatus getBuyStatus() {
        return buyStatus;
    }
}
