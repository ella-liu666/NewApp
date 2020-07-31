package com.haixiajiemei.app.Module.Setting.Contract;

public interface BuyCardContract {

    interface ViewAction {
        void BuyCardSuccess();

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }


    interface PresenterAction {

        void doBuyCard();
    }
}
