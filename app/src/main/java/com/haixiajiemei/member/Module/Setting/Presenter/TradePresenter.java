package com.haixiajiemei.member.Module.Setting.Presenter;

import android.content.Context;

import com.haixiajiemei.member.Api.Basic.ApiException;
import com.haixiajiemei.member.Api.DataLoader;
import com.haixiajiemei.member.Api.Task.Setting.TradeTask;
import com.haixiajiemei.member.Module.Setting.Contract.TradeContract;
import com.haixiajiemei.member.Module.Setting.Model.Expenses;

import java.util.List;

public class TradePresenter implements TradeContract.PresenterAction {

    private TradeContract.ViewAction viewAction;
    private Context mcontext;

    public TradePresenter(TradeContract.ViewAction viewAction, Context mcontext) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
    }

    @Override
    public void doTrade() {
        DataLoader.run(new TradeTask(mcontext){

            @Override
            protected void onResult(List<Expenses> rechargeList) throws Exception {
                viewAction.TradeSuccess(rechargeList);
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
