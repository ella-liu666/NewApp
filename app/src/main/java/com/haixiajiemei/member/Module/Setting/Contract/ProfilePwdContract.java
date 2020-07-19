package com.haixiajiemei.member.Module.Setting.Contract;

public interface ProfilePwdContract {

    interface ViewAction {
        void ProfilePwdSuccess();

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doProfilePwd();
    }
}
