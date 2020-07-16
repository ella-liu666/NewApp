package com.haixiajiemei.member.Api.Basic;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ErrorBody implements Serializable {
    @SerializedName("ErrorCode")
    private String statusCode;

    @SerializedName("Status")
    private String error;

    @SerializedName("Message")
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
