package com.haixiajiemei.member.Module.Setting.Contract;

import android.content.Context;

public interface QRcodeCouponContract {

    interface ViewAction {
        void QRcodeCouponSuccess(String s);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doQRcodeCoupon(Context context, int accountCouponMapID, float denomination, String name
                ,String storeName,String dueTime);
    }
}
