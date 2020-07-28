package com.haixiajiemei.app.Module.Setting.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StoredValue implements Serializable {

    @SerializedName("status")
    private boolean status;

    @SerializedName("statusMsg")
    private String statusMsg;


    public boolean isStatus() {
        return status;
    }

    public String getStatusMsg() {
        return statusMsg;
    }
}
