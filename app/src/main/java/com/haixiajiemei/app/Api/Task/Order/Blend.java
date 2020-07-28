package com.haixiajiemei.app.Api.Task.Order;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Blend implements Serializable {

    @SerializedName("feedingID")
    private int feedingID;

    @SerializedName("name")
    private String name;

    @SerializedName("newName")//顯示
    private String newName;

    @SerializedName("price")
    private float price;



    public int getFeedingID() {
        return feedingID;
    }

    public String getName() {
        return name;
    }

    public String getNewName() {
        return newName;
    }

    public float getPrice() {
        return price;
    }
}
