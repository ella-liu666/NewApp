package com.haixiajiemei.app.Module.Setting.Contract;

import com.haixiajiemei.app.Module.Home.Model.ImgAndTxt;

import java.util.List;

public interface VIPCardBuyContract {

    interface ViewAction {
        void VIPCardBuySuccess(List<ImgAndTxt> imgAndTxt);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doVIPCardBuy();
    }
}
