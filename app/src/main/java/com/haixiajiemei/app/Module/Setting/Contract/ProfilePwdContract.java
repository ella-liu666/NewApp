package com.haixiajiemei.app.Module.Setting.Contract;

public interface ProfilePwdContract {

    interface ViewAction {
        void ProfilePwdSuccess();

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }


    interface PresenterAction {

        void doProfilePwd();
    }
}
