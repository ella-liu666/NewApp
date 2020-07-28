package com.haixiajiemei.app.Module.Order.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

public class StoreFeed implements Serializable {

    @SerializedName("dbName")
    private String dbName;

    @SerializedName("image")
    private URL image;

    @SerializedName("mealsID")
    private int mealsID;

    @SerializedName("name")
    private String name;

    @SerializedName("detail")
    private String detail;

    @SerializedName("price")
    private float price;

    @SerializedName("feeding")
    private List<Customized> feeding;



    public String getDbName() {
        return dbName;
    }

    public URL getImage() {
        return image;
    }

    public int getMealsID() {
        return mealsID;
    }

    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }

    public float getPrice() {
        return price;
    }

    public List<Customized> getFeeding() {
        return feeding;
    }
}
