package com.haixiajiemei.app.Module.Setting.Contract;

import com.haixiajiemei.app.Module.Setting.Model.CardDetail;

public interface VIPDetailContract {

    interface ViewAction {
        void VIPDetailSuccess(CardDetail cardDetail);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }


    interface PresenterAction {

        void doVIPDetail();
    }
}
