package com.haixiajiemei.app.Module.Home.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.net.URL;

public class ItemsList implements Serializable {

    @SerializedName("itemsID")
    private int itemsID;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private float price;

    @SerializedName("delType")
    private int delType;

//    @SerializedName("image")
//    private URL image;

    @SerializedName("image")
    private String image;



    public int getItemsID() {
        return itemsID;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getDelType() {
        return delType;
    }

//    public URL getImage() {
//        return image;
//    }

    public String getImage() {
        return image;
    }
}
