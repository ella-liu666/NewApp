package com.haixiajiemei.member.Module.Setting.Contract;

public interface CouponItemCallback {

    void onCouponItemClicked(int accountCouponMapID, float denomination,String name,String storeName,String dueTime);
}
