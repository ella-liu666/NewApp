package com.haixiajiemei.app.Module.Order.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.net.URL;

public class StoreInfo implements Serializable {

    @SerializedName("cName")
    private String cName;

    @SerializedName("image")
    private URL image;



    public String getcName() {
        return cName;
    }

    public URL getImage() {
        return image;
    }
}
