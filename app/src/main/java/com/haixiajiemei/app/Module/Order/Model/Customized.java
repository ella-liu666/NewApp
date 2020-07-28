package com.haixiajiemei.app.Module.Order.Model;

import com.google.gson.annotations.SerializedName;
import com.haixiajiemei.app.Api.Task.Order.Blend;

import java.io.Serializable;
import java.util.List;

public class Customized implements Serializable {

    @SerializedName("feedingCategoryID")
    private int feedingCategoryID;

    @SerializedName("feedingCategoryName")
    private String feedingCategoryName;

    @SerializedName("feedingStatusName")
    private String feedingStatusName;

    @SerializedName("blend")
    private List<Blend> blend;



    public int getFeedingCategoryID() {
        return feedingCategoryID;
    }

    public String getFeedingCategoryName() {
        return feedingCategoryName;
    }

    public String getFeedingStatusName() {
        return feedingStatusName;
    }

    public List<Blend> getBlend() {
        return blend;
    }
}
