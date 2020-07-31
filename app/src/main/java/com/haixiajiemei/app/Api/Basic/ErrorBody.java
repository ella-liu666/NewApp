package com.haixiajiemei.app.Api.Basic;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ErrorBody implements Serializable {
    @SerializedName("ErrorCode")
    private String statusCode;

    @SerializedName("CardStatus")
    private String error;

    @SerializedName("Message")
    private String message;

    @SerializedName("access_token")
    private String access_token;

    @SerializedName("loginStatus")
    private boolean loginStatus;


    public String getStatusCode() {
        return statusCode;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getAccess_token() {
        if (access_token == null) {
            access_token="";
        }
        return access_token;
    }

    public boolean getLoginStatus() {
        return loginStatus;
    }
}
