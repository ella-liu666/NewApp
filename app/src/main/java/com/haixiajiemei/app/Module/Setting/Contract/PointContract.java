package com.haixiajiemei.app.Module.Setting.Contract;

public interface PointContract {

    interface ViewAction {
        void PointSuccess(String s);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }


    interface PresenterAction {

        void doPoint();
    }
}
