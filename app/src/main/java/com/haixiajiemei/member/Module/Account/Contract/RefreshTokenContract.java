package com.haixiajiemei.member.Module.Account.Contract;

import android.content.Context;

public interface RefreshTokenContract {

    interface ViewAction {

        void RefreshTokenSuccess();

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }

    interface PresenterAction {

        void doRefreshToken(Context context, String refreshToken);

    }
}
