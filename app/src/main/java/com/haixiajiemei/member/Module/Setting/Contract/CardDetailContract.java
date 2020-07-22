package com.haixiajiemei.member.Module.Setting.Contract;

import com.haixiajiemei.member.Module.Setting.Model.CardDetail;

public interface CardDetailContract {

    interface ViewAction {
        void CardDetailSuccess(CardDetail cardDetail);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doCardDetail();
    }
}
