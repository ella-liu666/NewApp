package com.haixiajiemei.app.Module.Setting.Contract;

import com.haixiajiemei.app.Module.Home.Model.ImgAndTxt;

import java.util.List;

public interface MonthCardContract {

    interface ViewAction {
        void MonthCardSuccess(List<ImgAndTxt> imgAndTxt);

        void showProgress();

        void hideProgress();

        void errorOccurred(String reason);
    }


    interface PresenterAction {

        void doMonthCard();
    }
}
