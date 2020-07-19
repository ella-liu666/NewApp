package com.haixiajiemei.member.Module.Setting.Presenter;

import android.content.Context;

import com.haixiajiemei.member.Api.Basic.ApiException;
import com.haixiajiemei.member.Api.DataLoader;
import com.haixiajiemei.member.Api.Task.Setting.MonthCardBuyTask;
import com.haixiajiemei.member.Module.Home.Model.ImgAndTxt;
import com.haixiajiemei.member.Module.Setting.Contract.MonthCardBuyContract;

import java.util.List;

public class MonthCardBuyPresenter implements MonthCardBuyContract.PresenterAction{

    private MonthCardBuyContract.ViewAction viewAction;
    private Context mcontext;

    public MonthCardBuyPresenter(MonthCardBuyContract.ViewAction viewAction, Context mcontext) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
    }

    @Override
    public void doMonthCardBuy() {
        DataLoader.run(new MonthCardBuyTask(mcontext) {

            @Override
            protected void onResult(List<ImgAndTxt> imgAndTxts) throws Exception {
                viewAction.MonthCardBuySuccess(imgAndTxts);
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
