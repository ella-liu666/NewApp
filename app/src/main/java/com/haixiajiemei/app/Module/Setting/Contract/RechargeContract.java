package com.haixiajiemei.app.Module.Setting.Contract;

import com.haixiajiemei.app.Module.Setting.Model.Recharge;

import java.util.List;

public interface RechargeContract {

    interface ViewAction {
        void RechargeSuccess(List<Recharge> rechargeList);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doRecharge();
    }
}
