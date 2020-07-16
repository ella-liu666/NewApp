package com.haixiajiemei.member.Module.Account.Contract;

import android.content.Context;

public interface ForgetContract {

    interface ViewAction {

        void ForgetSuccess();

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }

    interface PresenterAction {

        void doForget(Context context, String username, String telephone, String password);

    }
}
