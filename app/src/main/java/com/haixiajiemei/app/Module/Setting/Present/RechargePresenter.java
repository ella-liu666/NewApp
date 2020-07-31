package com.haixiajiemei.app.Module.Setting.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Setting.RechargeTask;
import com.haixiajiemei.app.Module.Setting.Contract.RechargeContract;
import com.haixiajiemei.app.Module.Setting.Model.Recharge;

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
                viewAction.ApierrorOccurred(e.getErrorBody().getAccess_token());
            }

            @Override
            protected void onException(Exception e) {
                viewAction.errorOccurred(e.getMessage());
            }
        });
    }
}
