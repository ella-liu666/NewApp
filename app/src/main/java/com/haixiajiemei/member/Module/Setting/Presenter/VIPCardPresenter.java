package com.haixiajiemei.member.Module.Setting.Presenter;

import android.content.Context;

import com.haixiajiemei.member.Api.Basic.ApiException;
import com.haixiajiemei.member.Api.DataLoader;
import com.haixiajiemei.member.Api.Task.Setting.VIPCardTask;
import com.haixiajiemei.member.Module.Home.Model.ImgAndTxt;
import com.haixiajiemei.member.Module.Setting.Contract.VIPCardContract;

import java.util.List;

public class VIPCardPresenter implements VIPCardContract.PresenterAction {

    private VIPCardContract.ViewAction viewAction;
    private Context mcontext;

    public VIPCardPresenter(VIPCardContract.ViewAction viewAction, Context mcontext) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
    }

    @Override
    public void doVIPCard() {
        DataLoader.run(new VIPCardTask(mcontext) {

            @Override
            protected void onResult(List<ImgAndTxt> imgAndTxts) throws Exception {
                viewAction.VIPCardSuccess(imgAndTxts);
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
