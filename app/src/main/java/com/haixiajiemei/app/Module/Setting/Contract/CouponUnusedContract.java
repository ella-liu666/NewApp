package com.haixiajiemei.app.Module.Setting.Contract;

import com.haixiajiemei.app.Module.Setting.Model.Coupon;

import java.util.List;

public interface CouponUnusedContract {

    interface ViewAction {
        void CouponUnusedSuccess(List<Coupon> rechargeList);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }


    interface PresenterAction {

        void doCouponUnused();
    }
}
