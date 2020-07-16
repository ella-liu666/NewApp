package com.haixiajiemei.member.Module.Home.Contract;

import com.haixiajiemei.member.Module.Home.Model.ImgAndTxt;

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
