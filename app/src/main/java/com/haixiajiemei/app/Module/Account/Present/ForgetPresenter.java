package com.haixiajiemei.app.Module.Account.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Auth.ForgetTask;
import com.haixiajiemei.app.Module.Account.Contract.ForgetContract;

public class ForgetPresenter implements ForgetContract.PresenterAction {

    private ForgetContract.ViewAction viewAction;

    public ForgetPresenter(ForgetContract.ViewAction viewAction) {
        this.viewAction = viewAction;
    }

    @Override
    public void doForget(Context context, String username, String telephone, String password) {
        DataLoader.run(new ForgetTask(context, username, telephone,password) {

            @Override
            protected void onResult(String s) throws Exception {
                viewAction.ForgetSuccess();
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
                viewAction.errorOccurred(e.getErrorBody().getMessage());
            }

            @Override
            protected void onException(Exception e) {
                viewAction.errorOccurred(e.getMessage());
            }
        });
    }
}
