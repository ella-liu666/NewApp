package com.haixiajiemei.app.Module.Setting.Contract;

import com.haixiajiemei.app.Module.Setting.Model.CardDetail;

public interface VIPDetailContract {

    interface ViewAction {
        void VIPDetailSuccess(CardDetail cardDetail);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doVIPDetail();
    }
}
