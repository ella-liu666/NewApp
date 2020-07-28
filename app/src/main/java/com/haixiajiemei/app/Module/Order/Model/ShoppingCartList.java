package com.haixiajiemei.app.Module.Order.Model;

import java.util.List;

public class ShoppingCartList {

    private String cName;

    private String address;

    public List<StoreFeed> storeFeeds;



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

}
