package com.haixiajiemei.member.Api.Rtf;

import android.content.Context;

import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.http.GET;

public class SettingRtf extends BaseRtf<SettingRtf.Service>{

    public SettingRtf(@Nullable Context context) {
        super(context);
    }

    @Override
    protected Class<Service> getType() {
        return SettingRtf.Service.class;
    }

    public String getRecharge() throws Exception {
        return this.execute(this.api.Recharge());
    }

    public String getMemberInfo() throws Exception {
        return this.execute(this.api.MemberInfo());
    }

    public String getQRcodePoint() throws Exception {
        return this.execute(this.api.QRcodePoint());
    }

    public String getPoint() throws Exception {
        return this.execute(this.api.Point());
    }

    public String getVIPCard() throws Exception {
        return this.execute(this.api.VIPCard());
    }

    public String getMonthCard() throws Exception {
        return this.execute(this.api.MonthCard());
    }

    public String getVIPCardBuy() throws Exception {
        return this.execute(this.api.VIPCardBuy());
    }

    public String getMonthCardBuy() throws Exception {
        return this.execute(this.api.MonthCardBuy());
    }

    public interface Service {
        @GET("Wallet/Recharge")
        Call<String> Recharge();

        @GET("Wallet/MemberInfo")
        Call<String> MemberInfo();

        @GET("Wallet/QRcodePoint")
        Call<String> QRcodePoint();

        @GET("Wallet/Point")
        Call<String> Point();

        @GET("Wallet/VIPCard")
        Call<String> VIPCard();

        @GET("Wallet/MonthCard")
        Call<String> MonthCard();

        @GET("Wallet/VIPCardBuy")
        Call<String> VIPCardBuy();

        @GET("Wallet/MonthCardBuy")
        Call<String> MonthCardBuy();
    }

}