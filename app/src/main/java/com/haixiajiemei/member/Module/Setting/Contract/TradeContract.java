package com.haixiajiemei.member.Module.Setting.Contract;

import com.haixiajiemei.member.Module.Setting.Model.Expenses;

import java.util.List;

public interface TradeContract {

    interface ViewAction {
        void TradeSuccess(List<Expenses> rechargeList);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doTrade();
    }
}