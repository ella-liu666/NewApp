package com.haixiajiemei.app.Module.Setting.Contract;

public interface SignOutContract {

    interface ViewAction {

        void SignOutSuccess();

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }

    interface PresenterAction {

        void doSignOut();

    }
}
