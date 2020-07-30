package com.haixiajiemei.app.Api.Task.Payment;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.PaymentRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Setting.Model.PayRequest;
import com.haixiajiemei.app.Parser.ClassParser;

public class WxPayRequestTask extends DataTask<PayRequest> {
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
        return api.getAlipayRequest(rechargeTotal);
    }

    @Override
    protected PayRequest parseData(String s) throws Exception {
        PayRequest response = ClassParser.toData(s, PayRequest.class);
        return response;
    }
}
