package com.haixiajiemei.app.Module.Setting.Contract;

import com.haixiajiemei.app.Module.Setting.Model.Expenses;

import java.util.List;

public interface TradeContract {

    interface ViewAction {
        void TradeSuccess(List<Expenses> rechargeList);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }


    interface PresenterAction {

        void doTrade();
    }
}
