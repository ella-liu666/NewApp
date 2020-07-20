package com.haixiajiemei.member.Module.Setting.Contract;

import com.haixiajiemei.member.Module.Setting.Model.Coupon;

import java.util.List;

public interface CouponExpiredContract {

    interface ViewAction {
        void CouponExpiredSuccess(List<Coupon> rechargeList);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doCouponExpired();
    }
}
