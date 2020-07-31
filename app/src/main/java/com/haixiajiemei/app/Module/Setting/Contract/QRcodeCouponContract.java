package com.haixiajiemei.app.Module.Setting.Contract;

import android.content.Context;

public interface QRcodeCouponContract {

    interface ViewAction {
        void QRcodeCouponSuccess(String s);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }


    interface PresenterAction {

        void doQRcodeCoupon(Context context, int accountCouponMapID, float denomination, String name
                ,String storeName,String dueTime);
    }
}
