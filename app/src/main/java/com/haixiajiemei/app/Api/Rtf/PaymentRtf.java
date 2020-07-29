package com.haixiajiemei.app.Api.Rtf;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.haixiajiemei.app.Api.Basic.Params;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class PaymentRtf extends BaseRtf<PaymentRtf.Service> {

    public PaymentRtf(@Nullable Context context) {
        super(context);
    }

    @Override
    protected Class<Service> getType() {
        return PaymentRtf.Service.class;
    }

    public String getAlipayRequest(@NonNull float rechargeTotal) throws Exception {
        Params params = new Params();
        params.putRequired("rechargeTotal", rechargeTotal);

        return this.execute(this.api.AlipayRequest(params.getMap()));
    }

    public interface Service {
        @POST("Payment/Payment/AliPayRequest")
        Call<String> AlipayRequest(@Body Map<String, Object> params);
    }
}
