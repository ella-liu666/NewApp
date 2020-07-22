package com.haixiajiemei.app.Api.Task.Setting;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.SettingRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Setting.Model.Coupon;

import java.util.List;

import androidx.annotation.NonNull;

import static com.haixiajiemei.app.Util.FunTools.JSONArrayToCoupon;

public class CouponUsedTask  extends DataTask<List<Coupon>> {
    private SettingRtf api;

    public CouponUsedTask(@NonNull Context context) {
        api = new SettingRtf(context);
    }
    @Override
    protected String load() throws Exception {
        return api.getCouponUsed();
    }

    @Override
    protected List<Coupon> parseData(String s) throws Exception {
        List<Coupon> response = JSONArrayToCoupon(s);
        return response;
    }
}
