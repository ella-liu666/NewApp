package com.haixiajiemei.member.Module.Setting.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Coupon implements Serializable {

    @SerializedName("accountCouponMapID")
    private int accountCouponMapID;

    @SerializedName("denomination")
    private float denomination;

    @SerializedName("name")
    private String name;

    @SerializedName("couponCategoryName")
    private String couponCategoryName;

    @SerializedName("storeName")
    private String storeName;

    @SerializedName("dueTime")
    private String dueTime;


    public int getAccountCouponMapID() {
        return accountCouponMapID;
    }

    public float getDenomination() {
        return denomination;
    }

    public String getName() {
        return name;
    }

    public String getcouponCategoryName() {
        return couponCategoryName;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getDueTime() {
        return dueTime;
    }
}
