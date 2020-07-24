package com.haixiajiemei.app.Module.Order.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ShoppingCart implements Serializable {

    @SerializedName("mealID")
    private String mealID;

    @SerializedName("mealName")
    private String mealName;

    @SerializedName("amount")
    private int amount;

    @SerializedName("price")
    private float price;

    @SerializedName("feeding")
    private List<Feeding> feeding;



    public String getMealID() {
        return mealID;
    }

    public String getMealName() {
        return mealName;
    }

    public int getAmount() {
        return amount;
    }

    public float getPrice() {
        return price;
    }

    public List<Feeding> getFeeding() {
        return feeding;
    }
}
