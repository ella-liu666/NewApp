package com.haixiajiemei.member.Api.Task.Setting;

import android.content.Context;

import com.haixiajiemei.member.Api.Rtf.SettingRtf;
import com.haixiajiemei.member.Api.Task.DataTask;

public class QRcodeCouponTask extends DataTask<String> {
    private SettingRtf api;

    private Context context;
    private int accountCouponMapID;
    private float denomination;
    private String name;
    private String storeName;
    private String dueTime;

    public QRcodeCouponTask(int accountCouponMapID, float denomination, String name, String storeName, String dueTime) {
        api = new SettingRtf(context);

        this.accountCouponMapID = accountCouponMapID;
        this.denomination = denomination;
        this.name = name;
        this.storeName = storeName;
        this.dueTime = dueTime;
    }

    @Override
    protected String load() throws Exception {
        return api.QRcodeCoupon(accountCouponMapID,denomination,name,storeName,dueTime);
    }

    @Override
    protected String parseData(String s) throws Exception {
        return s;
    }
}
