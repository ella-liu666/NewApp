package com.haixiajiemei.app.Module.Setting.Contract;

public interface ProfileNameContract {

    interface ViewAction {
        void ProfileNameSuccess();

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }


    interface PresenterAction {

        void doProfileName();
    }
}
