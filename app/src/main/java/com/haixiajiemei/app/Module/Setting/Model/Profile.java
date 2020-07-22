package com.haixiajiemei.app.Module.Setting.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Profile implements Serializable {

    @SerializedName("userName")//不可更改
    private String userName;

    @SerializedName("name")
    private String name;

    @SerializedName("telephone")//不可更改
    private String telephone;


    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getTelephone() {
        return telephone;
    }
}
