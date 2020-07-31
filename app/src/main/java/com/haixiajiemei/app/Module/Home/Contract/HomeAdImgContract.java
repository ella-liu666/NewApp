package com.haixiajiemei.app.Module.Home.Contract;

import com.haixiajiemei.app.Module.Home.Model.ImgAndTxt;

import java.util.List;

public interface HomeAdImgContract {

    interface ViewAction {
        void HomeAdImgContractSuccess(List<ImgAndTxt> imgAndTxt);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }


    interface PresenterAction {

        void doHomeAdImg();
    }
}
