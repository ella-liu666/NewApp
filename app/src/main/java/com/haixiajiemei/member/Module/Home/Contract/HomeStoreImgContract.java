package com.haixiajiemei.member.Module.Home.Contract;

import com.haixiajiemei.member.Module.Home.Model.ImgAndTxt;

import java.util.List;

public interface HomeStoreImgContract {

    interface ViewAction {
        void HomeStoreImgContractSuccess(List<ImgAndTxt> imgAndTxt);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doHomeStoreImg();
    }
}
