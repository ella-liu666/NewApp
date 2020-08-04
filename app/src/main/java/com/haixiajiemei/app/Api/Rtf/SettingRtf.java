package com.haixiajiemei.app.Api.Rtf;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.Params;

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

    public String getTrade() throws Exception {
        return this.execute(this.api.Trade());
    }

    public String getCouponUnused() throws Exception {
        return this.execute(this.api.CouponUnused());
    }

    public String getCouponUsed() throws Exception {
        return this.execute(this.api.CouponUsed());
    }

    public String getCouponExpired() throws Exception {
        return this.execute(this.api.CouponExpired());
    }

    public String QRcodeCoupon(@NonNull int accountCouponMapID, @NonNull float denomination,
            @NonNull String name, @NonNull String storeName, @NonNull String dueTime) throws Exception {
        Params params = new Params();
        params.putRequired("accountCouponMapID", accountCouponMapID);
        params.putRequired("denomination", denomination);
        params.putRequired("name", name);
        params.putRequired("storeName", storeName);
        params.putRequired("dueTime", dueTime);

        return this.execute(this.api.QRcodeCoupon(params.getMap()));
    }

    public String getVIPDetail(@NonNull int cardID) throws Exception {
        Params params = new Params();
        params.putRequired("cardID", cardID);

        return this.execute(this.api.VIPDetail(params.getMap()));
    }

    public String getCardDetail(@NonNull int cardID) throws Exception {
        Params params = new Params();
        params.putRequired("cardID", cardID);

        return this.execute(this.api.CardDetail(params.getMap()));
    }

    public String getBuyCard(@NonNull int cardID,@NonNull int cardCategoryID,@NonNull int type,
            @NonNull String cardName,@NonNull String cardPrice,@NonNull float upgradeCardPrice,@NonNull int cardCurrentAmount) throws Exception {
        Params params = new Params();
        params.putRequired("cardID", cardID);
        params.putRequired("cardCategoryID", cardCategoryID);
        params.putRequired("type", type);
        params.putRequired("cardName", cardName);
        params.putRequired("cardPrice", cardPrice);
        params.putRequired("upgradeCardPrice", upgradeCardPrice);
        params.putRequired("cardCurrentAmount", cardCurrentAmount);

        return this.execute(this.api.BuyCard(params.getMap()));
    }

    public String getStoredValue(@NonNull String orderNo,@NonNull float value,@NonNull String sourceID) throws Exception {
        Params params = new Params();
        params.putRequired("orderNo", orderNo);
        params.putRequired("value", value);
        params.putRequired("sourceID", sourceID);

        return this.execute(this.api.StoredValue(params.getMap()));
    }

    public interface Service {
        @GET("Wallet/GetRechargeList")
        Call<String> Recharge();

        @GET("Wallet/GetTradeList")
        Call<String> Trade();

        @GET("Wallet/GetHome")
        Call<String> MemberInfo();

        @GET("Wallet/GetQRcodePoint")
        Call<String> QRcodePoint();

        @POST("Wallet/GetQRcodeCoupon")
        Call<String> QRcodeCoupon(@Body Map<String, Object> params);

        @POST("Wallet/BuyCard")
        Call<String> BuyCard(@Body Map<String, Object> params);

        @GET("Wallet/GetPoint")
        Call<String> Point();

        @GET("Wallet/GetCardListVIP")
        Call<String> VIPCard();

        @GET("Wallet/GetCardListMonth")
        Call<String> MonthCard();

        @GET("Wallet/BuyCardListVIP")
        Call<String> VIPCardBuy();

        @GET("Wallet/BuyCardListMonth")
        Call<String> MonthCardBuy();

        @POST("Wallet/GetCardDetailVIP")
        Call<String> VIPDetail(@Body Map<String, Object> params);

        @POST("Wallet/GetCardDetailMonth")
        Call<String> CardDetail(@Body Map<String, Object> params);

        @GET("Wallet/GetCouponListUnused")
        Call<String> CouponUnused();

        @GET("Wallet/GetCouponListUsed")
        Call<String> CouponUsed();

        @GET("Wallet/GetCouponListExpired")
        Call<String> CouponExpired();

        @GET("Member/GetProfile")
        Call<String> Profile();

        @POST("Member/UpdatePwd")
        Call<String> ProfilePwd(@Body Map<String, Object> params);

        @POST("Member/UpdateName")
        Call<String> ProfileName(@Body Map<String, Object> params);



        @POST("Wallet/RechargePoint")
        Call<String> StoredValue(@Body Map<String, Object> params);
    }

}
