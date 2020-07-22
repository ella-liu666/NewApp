package com.haixiajiemei.app.Module.Home.Contract;

import com.haixiajiemei.app.Module.Home.Model.ImgAndTxt;

import java.util.List;

public interface BrandIntroductionImgContract {

    interface ViewAction {
        void BrandIntroductionImgContractSuccess(List<ImgAndTxt> imgAndTxt);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doBrandIntroductionImg();
    }
}
