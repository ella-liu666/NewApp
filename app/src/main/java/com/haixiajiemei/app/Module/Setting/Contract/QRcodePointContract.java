package com.haixiajiemei.app.Module.Setting.Contract;

public interface QRcodePointContract {

    interface ViewAction {
        void QRcodePointSuccess(String s);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }


    interface PresenterAction {

        void doQRcodePoint();
    }
}
