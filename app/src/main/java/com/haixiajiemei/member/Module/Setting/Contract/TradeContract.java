package com.haixiajiemei.member.Module.Setting.Contract;

import com.haixiajiemei.member.Module.Setting.Model.Recharge;

import java.util.List;

public interface TradeContract {

    interface ViewAction {
        void TradeSuccess(List<Recharge> rechargeList);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doTrade();
    }
}
