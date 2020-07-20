package com.haixiajiemei.member.Module.Setting.Contract;

import com.haixiajiemei.member.Module.Setting.Model.Coupon;

import java.util.List;

public interface CouponUnusedContract {

    interface ViewAction {
        void CouponUnusedSuccess(List<Coupon> rechargeList);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doCouponUnused();
    }
}
