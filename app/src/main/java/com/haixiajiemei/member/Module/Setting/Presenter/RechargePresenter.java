package com.haixiajiemei.member.Module.Setting.Presenter;

import android.content.Context;

import com.haixiajiemei.member.Api.Basic.ApiException;
import com.haixiajiemei.member.Api.DataLoader;
import com.haixiajiemei.member.Api.Task.Setting.RechargeTask;
import com.haixiajiemei.member.Module.Setting.Contract.RechargeContract;
import com.haixiajiemei.member.Module.Setting.Model.Recharge;

import java.util.List;

public class RechargePresenter implements RechargeContract.PresenterAction {

    private RechargeContract.ViewAction viewAction;
    private Context mcontext;

    public RechargePresenter(RechargeContract.ViewAction viewAction, Context mcontext) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
    }

    @Override
    public void doRecharge() {
        DataLoader.run(new RechargeTask(mcontext){

            @Override
            protected void onResult(List<Recharge> rechargeList) throws Exception {
               viewAction.RechargeSuccess(rechargeList);
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
