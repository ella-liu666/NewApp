package com.haixiajiemei.app.Module.Setting.Contract;

import com.haixiajiemei.app.Module.Home.Model.ImgAndTxt;

import java.util.List;

public interface MonthCardBuyContract {

    interface ViewAction {
        void MonthCardBuySuccess(List<ImgAndTxt> imgAndTxt);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);

        void ApierrorOccurred(String Access_token);
    }


    interface PresenterAction {

        void doMonthCardBuy();
    }
}
