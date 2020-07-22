package com.haixiajiemei.app.Module.Setting.Contract;

public interface PointContract {

    interface ViewAction {
        void PointSuccess(String s);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doPoint();
    }
}
