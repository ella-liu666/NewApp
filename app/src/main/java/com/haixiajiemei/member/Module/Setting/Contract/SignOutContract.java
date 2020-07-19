package com.haixiajiemei.member.Module.Setting.Contract;

public interface SignOutContract {

    interface ViewAction {

        void SignOutSuccess();

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }

    interface PresenterAction {

        void doSignOut();

    }
}
