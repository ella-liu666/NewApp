package com.haixiajiemei.member.Module.Setting.Contract;

import com.haixiajiemei.member.Module.Home.Model.ImgAndTxt;

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
