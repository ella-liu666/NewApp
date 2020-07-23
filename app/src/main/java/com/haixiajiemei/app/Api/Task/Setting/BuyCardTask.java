package com.haixiajiemei.app.Api.Task.Setting;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.SettingRtf;
import com.haixiajiemei.app.Api.Task.DataTask;

import androidx.annotation.NonNull;

public class BuyCardTask extends DataTask<String> {

    private SettingRtf api;

    private int cardID;
    private int cardCategoryID;
    private int type;
    private String cardName;
//    private float cardPrice;
    private String cardPrice;
    private float upgradeCardPrice;
    private int cardCurrentAmount;

    public BuyCardTask(@NonNull Context context, int cardID, int cardCategoryID, int type, String cardName, String cardPrice, float upgradeCardPrice, int cardCurrentAmount) {
        api = new SettingRtf(context);

        this.cardID = cardID;
        this.cardCategoryID = cardCategoryID;
        this.type = type;
        this.cardName = cardName;
        this.cardPrice = cardPrice;
        this.upgradeCardPrice = upgradeCardPrice;
        this.cardCurrentAmount = cardCurrentAmount;
    }

    @Override
    protected String load() throws Exception {
        return api.getBuyCard(cardID, cardCategoryID, type, cardName, cardPrice,upgradeCardPrice,cardCurrentAmount);
    }

    @Override
    protected String parseData(String s) throws Exception {
        return s;
    }
}
