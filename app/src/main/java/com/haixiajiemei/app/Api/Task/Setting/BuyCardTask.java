package com.haixiajiemei.app.Api.Task.Setting;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.SettingRtf;
import com.haixiajiemei.app.Api.Task.DataTask;

import androidx.annotation.NonNull;

public class BuyCardTask extends DataTask<String> {

    private SettingRtf api;

    private int accountCouponMapID;
    private float denomination;
    private String name;
    private String storeName;
    private String dueTime;

    public BuyCardTask(@NonNull Context context,int accountCouponMapID, float denomination, String name, String storeName, String dueTime) {
        api = new SettingRtf(context);

        this.accountCouponMapID = accountCouponMapID;
        this.denomination = denomination;
        this.name = name;
        this.storeName = storeName;
        this.dueTime = dueTime;
    }

    @Override
    protected String load() throws Exception {
//        return api.getBuyCard(accountCouponMapID,denomination,name,storeName,dueTime);
        return api.QRcodeCoupon(accountCouponMapID,denomination,name,storeName,dueTime);
    }

    @Override
    protected String parseData(String s) throws Exception {
        return s;
    }
}
