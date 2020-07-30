package com.haixiajiemei.app.Module.Order.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SuccessMessage implements Serializable {

    @SerializedName("message")
    private String message;


    public String getMessage() {
        return message;
    }
}
