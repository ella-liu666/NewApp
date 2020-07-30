package com.haixiajiemei.app.Api.Task.Payment;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.PaymentRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Setting.Model.PayRequest;
import com.haixiajiemei.app.Module.Setting.Model.WxPayRequest;
import com.haixiajiemei.app.Parser.ClassParser;

public class WxPayRequestTask extends DataTask<WxPayRequest> {
    private PaymentRtf api;

    private final Context context;
    private final float rechargeTotal;

    public WxPayRequestTask(Context context, float rechargeTotal) {
        api = new PaymentRtf(context);

        this.context = context;
        this.rechargeTotal = rechargeTotal;
    }

    @Override
    protected String load() throws Exception {
        return api.getWxPayRequest(rechargeTotal);
    }

    @Override
    protected WxPayRequest parseData(String s) throws Exception {
        WxPayRequest response = ClassParser.toData(s, WxPayRequest.class);
        return response;
    }
}
