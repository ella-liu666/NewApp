package com.haixiajiemei.app.Module.Account.Contract;

import android.content.Context;

public interface RegisterContract {

    interface ViewAction {

        void RegisterSuccess();

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }

    interface PresenterAction {

        void doSignUp(Context context, String userName, String password, String name, String telephone);

    }
}
