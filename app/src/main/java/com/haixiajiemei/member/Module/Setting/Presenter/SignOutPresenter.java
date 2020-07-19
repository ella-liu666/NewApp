package com.haixiajiemei.member.Module.Setting.Presenter;

import android.content.Context;

import com.haixiajiemei.member.Api.Basic.ApiException;
import com.haixiajiemei.member.Api.DataLoader;
import com.haixiajiemei.member.Api.Task.Setting.SignOutTask;
import com.haixiajiemei.member.Module.Account.Model.SignIn;
import com.haixiajiemei.member.Module.Setting.Contract.SignOutContract;

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
                viewAction.errorOccurred(e.getReason());
            }

            @Override
            protected void onException(Exception e) {
                viewAction.errorOccurred(e.getMessage());
            }
        });
    }
}
