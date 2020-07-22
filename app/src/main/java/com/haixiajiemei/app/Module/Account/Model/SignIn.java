package com.haixiajiemei.app.Module.Account.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SignIn implements Serializable {

    @SerializedName("access_token")
    private String access_token;

    @SerializedName("loginStatus")
    private boolean loginStatus;



    public String getAccess_token() {
        return access_token;
    }

    public boolean getLoginStatus() {
        return loginStatus;
    }
}
