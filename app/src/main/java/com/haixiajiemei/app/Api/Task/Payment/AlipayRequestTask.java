package com.haixiajiemei.app.Api.Task.Payment;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.PaymentRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Setting.Model.AlipayRequest;
import com.haixiajiemei.app.Parser.ClassParser;

public class AlipayRequestTask extends DataTask<AlipayRequest> {
    private PaymentRtf api;

    private final Context context;
    private final float rechargeTotal;

    public AlipayRequestTask(Context context, float rechargeTotal) {
        api = new PaymentRtf(context);

        this.context = context;
        this.rechargeTotal = rechargeTotal;
    }

    @Override
    protected String load() throws Exception {
        return api.getAlipayRequest(rechargeTotal);
    }

    @Override
    protected AlipayRequest parseData(String s) throws Exception {
        AlipayRequest response = ClassParser.toData(s, AlipayRequest.class);
        return response;
    }
}
