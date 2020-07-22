package com.haixiajiemei.app.Module.Setting.Presenter;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Setting.VIPCardBuyTask;
import com.haixiajiemei.app.Module.Home.Model.ImgAndTxt;
import com.haixiajiemei.app.Module.Setting.Contract.VIPCardBuyContract;

import java.util.List;

public class VIPCardBuyPresenter implements VIPCardBuyContract.PresenterAction{

    private VIPCardBuyContract.ViewAction viewAction;
    private Context mcontext;

    public VIPCardBuyPresenter(VIPCardBuyContract.ViewAction viewAction, Context mcontext) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
    }

    @Override
    public void doVIPCardBuy() {
        DataLoader.run(new VIPCardBuyTask(mcontext) {

            @Override
            protected void onResult(List<ImgAndTxt> imgAndTxts) throws Exception {
                viewAction.VIPCardBuySuccess(imgAndTxts);
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
