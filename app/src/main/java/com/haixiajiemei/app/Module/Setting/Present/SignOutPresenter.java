package com.haixiajiemei.app.Module.Setting.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Setting.SignOutTask;
import com.haixiajiemei.app.Module.Account.Model.SignIn;
import com.haixiajiemei.app.Module.Setting.Contract.SignOutContract;

public class SignOutPresenter implements SignOutContract.PresenterAction{

    private SignOutContract.ViewAction viewAction;
    private Context mcontext;

    public SignOutPresenter(SignOutContract.ViewAction viewAction, Context mcontext) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
    }

    @Override
    public void doSignOut() {
        DataLoader.run(new SignOutTask(mcontext){

            @Override
            protected void onResult(SignIn signIn) throws Exception {
                viewAction.SignOutSuccess();
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
