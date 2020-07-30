package com.haixiajiemei.app.Module.Order.Contract;

public interface AddressItemCallback {

    void onAddressItemClicked(String Address,String PhoneName);

    void onDelAddressItemClicked(int deliveryID);
}
