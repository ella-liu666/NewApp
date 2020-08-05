package com.haixiajiemei.app.Module.Home.Contract;

public interface CheckoutECContract {

    interface ViewAction {

        void CheckoutECSuccess(String s);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }

    interface PresenterAction {

        void doCheckoutEC();

    }
}
