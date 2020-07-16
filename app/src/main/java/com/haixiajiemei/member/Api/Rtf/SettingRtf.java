package com.haixiajiemei.member.Api.Rtf;

import android.content.Context;

import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.http.GET;

public class SettingRtf extends BaseRtf<SettingRtf.Service>{

    protected SettingRtf(@Nullable Context context) {
        super(context);
    }

    @Override
    protected Class<Service> getType() {
        return SettingRtf.Service.class;
    }

    public interface Service {

        @GET("Wallet/TradeRecord")
        Call<String> TradeRecord();
    }

}
