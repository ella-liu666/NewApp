package com.haixiajiemei.member.Module.Setting.Contract;

import com.haixiajiemei.member.Module.Setting.Model.Profile;

public interface ProfileContract {

    interface ViewAction {
        void ProfileSuccess(Profile Profile);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doProfile();
    }
}
