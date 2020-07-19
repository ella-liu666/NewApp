package com.haixiajiemei.member.Api.Rtf;

import android.content.Context;

import com.haixiajiemei.member.Api.Basic.Params;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

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

    public String getProfile() throws Exception {
        return this.execute(this.api.Profile());
    }

    public String getProfileName(@NonNull String name) throws Exception {
        Params params = new Params();
        params.putRequired("name", name);

        return this.execute(this.api.ProfileName(params.getMap()));
    }

    public String getProfilePwd(@NonNull String nowPassword,@NonNull String newPassword) throws Exception {
        Params params = new Params();
        params.putRequired("nowPassword", nowPassword);
        params.putRequired("newPassword", newPassword);

        return this.execute(this.api.ProfilePwd(params.getMap()));
    }

    public String getsign_out() throws Exception {
        return this.execute(this.api.sign_out());
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

        @GET("Wallet/Member/Profile")
        Call<String> Profile();

        @POST("Wallet/Member/ProfilePwd")
        Call<String> ProfilePwd(@Body Map<String, Object> params);

        @POST("Wallet/Member/ProfileName")
        Call<String> ProfileName(@Body Map<String, Object> params);

        @GET("Wallet/Member/LogOut")
        Call<String> sign_out();
    }

}
