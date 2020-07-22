package com.haixiajiemei.member.Module.Setting.Contract;

import com.haixiajiemei.member.Module.Setting.Model.CardDetail;

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
