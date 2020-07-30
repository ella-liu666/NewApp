package com.haixiajiemei.app.Module.Setting.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Payment.WxPayRequestTask;
import com.haixiajiemei.app.Module.Setting.Contract.WxPayRequestContract;
import com.haixiajiemei.app.Module.Setting.Model.PayRequest;

public class WxPayRequestPresenter implements WxPayRequestContract.PresenterAction {

    private WxPayRequestContract.ViewAction viewAction;
    private Context mcontext;
    private float rechargeTotal;

    public WxPayRequestPresenter(WxPayRequestContract.ViewAction viewAction, Context mcontext, float rechargeTotal) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
        this.rechargeTotal = rechargeTotal;
    }

    @Override
    public void doWxPayRequest() {
        DataLoader.run(new WxPayRequestTask(mcontext,rechargeTotal){

            @Override
            protected void onResult(PayRequest payRequest) throws Exception {
                viewAction.WxPayRequestSuccess(payRequest);
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
