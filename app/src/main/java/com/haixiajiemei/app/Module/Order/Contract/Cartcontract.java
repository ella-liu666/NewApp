package com.haixiajiemei.app.Module.Order.Contract;

public interface Cartcontract {

    interface ViewAction {

        void CartSuccess(String s);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }

    interface PresenterAction {

        void doCart();

    }
}
