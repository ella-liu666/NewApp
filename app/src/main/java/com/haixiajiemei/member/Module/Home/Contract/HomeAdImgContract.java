package com.haixiajiemei.member.Module.Home.Contract;

import com.haixiajiemei.member.Module.Home.Model.ImgAndTxt;

import java.util.List;

public interface HomeAdImgContract {

    interface ViewAction {
        void HomeAdImgContractSuccess(List<ImgAndTxt> imgAndTxt);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doHomeAdImg();
    }
}
