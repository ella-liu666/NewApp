package com.haixiajiemei.member.Module.Account.Contract;

import android.content.Context;

public interface LoginContract {

    interface ViewAction {

        void LoginSuccess();

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }

    interface PresenterAction {

        void doLogin(Context context, String usernameOrEmail, String password);

    }
}
