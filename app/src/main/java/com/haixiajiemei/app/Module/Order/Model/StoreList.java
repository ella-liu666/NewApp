package com.haixiajiemei.app.Module.Order.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StoreList implements Serializable {

    @SerializedName("dbid")
    private int dbid;

    @SerializedName("dbName")
    private String dbName;

    @SerializedName("storeInfo")
    private StoreInfo storeInfo;



    public int getDbid() {
        return dbid;
    }

    public String getDbName() {
        return dbName;
    }

    public StoreInfo getStoreInfo() {
        return storeInfo;
    }
}
