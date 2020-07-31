package com.haixiajiemei.app.Module.Setting.Contract;

import com.haixiajiemei.app.Module.Setting.Model.Profile;

public interface ProfileContract {

    interface ViewAction {
        void ProfileSuccess(Profile Profile);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }


    interface PresenterAction {

        void doProfile();
    }
}
