package com.haixiajiemei.app.Module.Setting.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Payment.AlipayRequestTask;
import com.haixiajiemei.app.Module.Setting.Contract.AlipayRequestContract;
import com.haixiajiemei.app.Module.Setting.Model.PayRequest;

public class AlipayRequestPresenter implements AlipayRequestContract.PresenterAction{

    private AlipayRequestContract.ViewAction viewAction;
    private Context mcontext;
    private float rechargeTotal;

    public AlipayRequestPresenter(AlipayRequestContract.ViewAction viewAction, Context mcontext, float rechargeTotal) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
        this.rechargeTotal = rechargeTotal;
    }

    @Override
    public void doAlipayRequest() {
        DataLoader.run(new AlipayRequestTask(mcontext,rechargeTotal){

            @Override
            protected void onResult(PayRequest payRequest) throws Exception {
                viewAction.AlipayRequestSuccess(payRequest);
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
