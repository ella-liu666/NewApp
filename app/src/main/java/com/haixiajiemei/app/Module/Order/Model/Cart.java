package com.haixiajiemei.app.Module.Order.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable {

    @SerializedName("storeAccount")
    private String storeAccount;

    @SerializedName("total")
    private float total;

    @SerializedName("delType")
    private String delType;

    @SerializedName("cart")
    private List<ShoppingCart> cart;



    public String getStoreAccount() {
        return storeAccount;
    }

    public float getTotal() {
        return total;
    }

    public String getDelType() {
        return delType;
    }

    public List<ShoppingCart> getCart() {
        return cart;
    }
}
