package com.haixiajiemei.member.Module.Account.Present;

import android.content.Context;

import com.haixiajiemei.member.Api.Basic.ApiException;
import com.haixiajiemei.member.Api.DataLoader;
import com.haixiajiemei.member.Api.Task.Auth.RegisterTask;
import com.haixiajiemei.member.Module.Account.Contract.RegisterContract;

public class RegisterPresenter implements RegisterContract.PresenterAction {

    private RegisterContract.ViewAction viewAction;

    public RegisterPresenter(RegisterContract.ViewAction viewAction) {
        this.viewAction = viewAction;
    }

    @Override
    public void doSignUp(Context context, String userName, String password, String name, String telephone) {
        DataLoader.run(new RegisterTask(context, userName, password,name,telephone) {

            @Override
            protected void onResult(String s) throws Exception {
                viewAction.RegisterSuccess();
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
