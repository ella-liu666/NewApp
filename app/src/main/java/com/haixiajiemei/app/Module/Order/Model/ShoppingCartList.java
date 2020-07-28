package com.haixiajiemei.app.Module.Order.Model;

import java.util.List;

public class ShoppingCartList {

    private String cName;

    private String address;

    private String storeAccount;//dbName

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
}
