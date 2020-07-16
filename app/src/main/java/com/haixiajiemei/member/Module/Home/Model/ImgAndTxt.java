package com.haixiajiemei.member.Module.Home.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.net.URL;


public class ImgAndTxt implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("image")
    private URL img;

    @SerializedName("name")
    private String txt;


    public int getId() {
        return id;
    }

    public URL getImg() {
        return img;
    }

    public String getTxt() {
        return txt;
    }

}
