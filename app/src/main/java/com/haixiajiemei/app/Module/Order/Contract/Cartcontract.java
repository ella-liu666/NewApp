package com.haixiajiemei.app.Module.Order.Contract;

public interface Cartcontract {

    interface ViewAction {

        void CartSuccess();

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }

    interface PresenterAction {

        void doCart();

    }
}
