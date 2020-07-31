package com.haixiajiemei.app.Module.Order.Contract;

public interface AddressItemCallback {

    void onAddressItemClicked(String Address,String PhoneName,int deliveryID);

    void onDelAddressItemClicked(int deliveryID);
}
