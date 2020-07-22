package com.haixiajiemei.app.Module.Setting.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CardStatus implements Serializable {

    @SerializedName("status")
    private boolean status;

    @SerializedName("msg")
    private String msg;

    @SerializedName("type")
    private int type;



    public boolean isStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public int getType() {
        return type;
    }
}
