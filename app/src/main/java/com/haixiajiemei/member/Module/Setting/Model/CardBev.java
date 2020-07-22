package com.haixiajiemei.member.Module.Setting.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CardBev implements Serializable {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }
}
