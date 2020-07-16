package com.haixiajiemei.member.Module.Account.Present;

import android.content.Context;

import com.haixiajiemei.member.Api.Basic.ApiException;
import com.haixiajiemei.member.Api.DataLoader;
import com.haixiajiemei.member.Api.Task.Auth.LoginTask;
import com.haixiajiemei.member.Module.Account.Contract.LoginContract;
import com.haixiajiemei.member.Module.Account.Model.SignIn;

public class LoginPresenter implements LoginContract.PresenterAction{

    private LoginContract.ViewAction viewAction;


    public LoginPresenter(LoginContract.ViewAction viewAction) {
        this.viewAction = viewAction;
    }

    @Override
    public void doLogin(Context context, String username, String password) {
        DataLoader.run(new LoginTask(context, username, password) {

            @Override
            protected void onResult(SignIn signIn) throws Exception {
                viewAction.LoginSuccess();
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
