package com.haixiajiemei.app.Module.Setting.Contract;

public interface CouponItemCallback {

    void onCouponItemClicked(int accountCouponMapID, float denomination,String name,String storeName,String dueTime);
}
