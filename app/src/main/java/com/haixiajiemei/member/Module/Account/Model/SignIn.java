package com.haixiajiemei.member.Module.Account.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SignIn implements Serializable {

    @SerializedName("access_token")
    private String access_token;

    @SerializedName("refresh_token")
    private String refresh_token;



    public String getAccess_token() {
        return access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }
}
