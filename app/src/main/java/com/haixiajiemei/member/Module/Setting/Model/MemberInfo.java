package com.haixiajiemei.member.Module.Setting.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MemberInfo implements Serializable {

    @SerializedName("accountType")
    private String accountType;

    @SerializedName("name")
    private String name;

    @SerializedName("pointTotal")
    private float pointTotal;

    @SerializedName("experience")
    private int experience;

    @SerializedName("couponNum")
    private int couponNum;

    @SerializedName("cardNum")
    private int cardNum;



    public String getAccountType() {
        return accountType;
    }

    public String getName() {
        return name;
    }

    public float getPointTotal() {
        return pointTotal;
    }

    public int getExperience() {
        return experience;
    }

    public int getCouponNum() {
        return couponNum;
    }

    public int getCardNum() {
        return cardNum;
    }
}
