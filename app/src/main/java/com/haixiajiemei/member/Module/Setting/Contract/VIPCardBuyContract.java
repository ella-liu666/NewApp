package com.haixiajiemei.member.Module.Setting.Contract;

import com.haixiajiemei.member.Module.Home.Model.ImgAndTxt;

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
