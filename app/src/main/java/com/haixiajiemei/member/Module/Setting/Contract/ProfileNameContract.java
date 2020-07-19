package com.haixiajiemei.member.Module.Setting.Contract;

public interface ProfileNameContract {

    interface ViewAction {
        void ProfileNameSuccess();

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doProfileName();
    }
}
