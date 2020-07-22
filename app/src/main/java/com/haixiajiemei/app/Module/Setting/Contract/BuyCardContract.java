package com.haixiajiemei.app.Module.Setting.Contract;

public interface BuyCardContract {

    interface ViewAction {
        void BuyCardSuccess();

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doBuyCard();
    }
}
