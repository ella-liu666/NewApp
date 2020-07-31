package com.haixiajiemei.app.Module.Setting.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Setting.PointTask;
import com.haixiajiemei.app.Module.Setting.Contract.PointContract;

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
                viewAction.ApierrorOccurred(e.getErrorBody().getAccess_token());
            }

            @Override
            protected void onException(Exception e) {
                viewAction.errorOccurred(e.getMessage());
            }
        });
    }
}
