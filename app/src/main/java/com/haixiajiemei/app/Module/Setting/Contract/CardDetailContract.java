package com.haixiajiemei.app.Module.Setting.Contract;

import com.haixiajiemei.app.Module.Setting.Model.CardDetail;

public interface CardDetailContract {

    interface ViewAction {
        void CardDetailSuccess(CardDetail cardDetail);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }


    interface PresenterAction {

        void doCardDetail();
    }
}
