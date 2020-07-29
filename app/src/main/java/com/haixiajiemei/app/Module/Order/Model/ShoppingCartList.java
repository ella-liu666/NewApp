package com.haixiajiemei.app.Module.Order.Model;

import java.util.List;

public class ShoppingCartList {

    private String cName;

    private String address;

    private String storeAccount;//dbName

    private int dbid;

    private float Total;

    public static final String delType = "3";

    public List<ShoppingCart> cart;



    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStoreAccount() {
        return storeAccount;
    }

    public void setStoreAccount(String storeAccount) {
        this.storeAccount = storeAccount;
    }

    public int getDbid() {
        return dbid;
    }

    public void setDbid(int dbid) {
        this.dbid = dbid;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float total) {
        Total = total;
    }
}
