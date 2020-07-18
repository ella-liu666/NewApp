package com.haixiajiemei.member.Module.Setting.Presenter;

import android.content.Context;

import com.haixiajiemei.member.Api.Basic.ApiException;
import com.haixiajiemei.member.Api.DataLoader;
import com.haixiajiemei.member.Api.Task.Setting.PointTask;
import com.haixiajiemei.member.Module.Setting.Contract.PointContract;

public class PointPresenter implements PointContract.PresenterAction {

    private PointContract.ViewAction viewAction;
    private Context mcontext;

    public PointPresenter(PointContract.ViewAction viewAction, Context mcontext) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
    }

    @Override
    public void doPoint() {
        DataLoader.run(new PointTask(mcontext){

            @Override
            protected void onResult(String s) throws Exception {
                viewAction.PointSuccess(s);
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
