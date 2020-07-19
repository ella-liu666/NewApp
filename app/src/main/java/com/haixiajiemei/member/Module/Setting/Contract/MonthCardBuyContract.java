package com.haixiajiemei.member.Module.Setting.Contract;

import com.haixiajiemei.member.Module.Home.Model.ImgAndTxt;

import java.util.List;

public interface MonthCardBuyContract {

    interface ViewAction {
        void MonthCardBuySuccess(List<ImgAndTxt> imgAndTxt);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doMonthCardBuy();
    }
}
