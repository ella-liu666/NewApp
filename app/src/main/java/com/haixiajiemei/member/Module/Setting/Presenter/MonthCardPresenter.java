package com.haixiajiemei.member.Module.Setting.Presenter;

import android.content.Context;

import com.haixiajiemei.member.Api.Basic.ApiException;
import com.haixiajiemei.member.Api.DataLoader;
import com.haixiajiemei.member.Api.Task.Setting.MonthCardTask;
import com.haixiajiemei.member.Module.Home.Model.ImgAndTxt;
import com.haixiajiemei.member.Module.Setting.Contract.MonthCardContract;

import java.util.List;

public class MonthCardPresenter implements MonthCardContract.PresenterAction {

    private MonthCardContract.ViewAction viewAction;
    private Context mcontext;

    public MonthCardPresenter(MonthCardContract.ViewAction viewAction, Context mcontext) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
    }

    @Override
    public void doMonthCard() {
        DataLoader.run(new MonthCardTask(mcontext) {

            @Override
            protected void onResult(List<ImgAndTxt> imgAndTxts) throws Exception {
                viewAction.MonthCardSuccess(imgAndTxts);
            }

            @Override
            protected void onStart() {
                viewAction.showProgress();
            }

            @Override
            protected void onFinish() {
                viewAction.hideProgress();
            }

            @Override
            protected void onApiException(ApiException e) {
                viewAction.errorOccurred(e.getReason());
            }

            @Override
            protected void onException(Exception e) {
                viewAction.errorOccurred(e.getMessage());
            }
        });
    }
}
