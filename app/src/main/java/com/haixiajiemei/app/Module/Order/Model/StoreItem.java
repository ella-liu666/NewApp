package com.haixiajiemei.app.Module.Order.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.net.URL;

public class StoreItem implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("detail")
    private String detail;

    @SerializedName("image")
    private URL image;

    @SerializedName("price")
    private float price;

    @SerializedName("dbName")
    private String dbName;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }

    public URL getImage() {
        return image;
    }

    public float getPrice() {
        return price;
    }

    public String getDbName() {
        return dbName;
    }
}
