package com.haixiajiemei.member.Api.Task.Setting;

import android.content.Context;

import com.haixiajiemei.member.Api.Rtf.SettingRtf;
import com.haixiajiemei.member.Api.Task.DataTask;
import com.haixiajiemei.member.Module.Setting.Model.Coupon;

import java.util.List;

import androidx.annotation.NonNull;

import static com.haixiajiemei.member.Util.FunTools.JSONArrayToCoupon;

public class CouponUnusedTask extends DataTask<List<Coupon>> {
    private SettingRtf api;

    public CouponUnusedTask(@NonNull Context context) {
        api = new SettingRtf(context);
    }

    @Override
    protected String load() throws Exception {
        return api.getCouponUnused();
    }

    @Override
    protected List<Coupon> parseData(String s) throws Exception {
        List<Coupon> response = JSONArrayToCoupon(s);
        return response;
    }
}
