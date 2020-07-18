package com.haixiajiemei.member.Module.Setting.Contract;

public interface QRcodePointContract {

    interface ViewAction {
        void QRcodePointSuccess(String s);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doQRcodePoint();
    }
}
