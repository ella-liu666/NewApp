package com.haixiajiemei.app.Module.Order.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class StoreList implements Serializable {

    @SerializedName("dbid")
    private int dbid;

    @SerializedName("dbName")
    private String dbName;

    @SerializedName("storeInfo")
    private List<StoreInfo> storeInfo;



    public int getDbid() {
        return dbid;
    }

    public String getDbName() {
        return dbName;
    }

    public List<StoreInfo> getStoreInfo() {
        return storeInfo;
    }
}
