package com.haixiajiemei.member.Api.Basic;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ErrorBody implements Serializable {
    @SerializedName("statusCode")
    private String statusCode;

    @SerializedName("error")
    private String error;

    @SerializedName("message")
    private String message;

    public String getStatusCode() {
        return statusCode;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
