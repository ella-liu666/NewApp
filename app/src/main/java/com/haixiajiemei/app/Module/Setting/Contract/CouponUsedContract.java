package com.haixiajiemei.app.Module.Setting.Contract;

import com.haixiajiemei.app.Module.Setting.Model.Coupon;

import java.util.List;

public interface CouponUsedContract {

    interface ViewAction {
        void CouponUsedSuccess(List<Coupon> rechargeList);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }


    interface PresenterAction {

        void doCouponUsed();
    }
}
