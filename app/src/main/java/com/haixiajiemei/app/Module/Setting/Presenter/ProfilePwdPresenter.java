package com.haixiajiemei.app.Module.Setting.Presenter;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Setting.ProfilePwdTask;
import com.haixiajiemei.app.Module.Setting.Contract.ProfilePwdContract;

public class ProfilePwdPresenter implements ProfilePwdContract.PresenterAction {

    private ProfilePwdContract.ViewAction viewAction;
    private Context mcontext;
    private String nowPassword;
    private String newPassword;

    public ProfilePwdPresenter(ProfilePwdContract.ViewAction viewAction, Context mcontext, String nowPassword, String newPassword) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
        this.nowPassword = nowPassword;
        this.newPassword = newPassword;
    }

    @Override
    public void doProfilePwd() {
        DataLoader.run(new ProfilePwdTask(mcontext,nowPassword,newPassword) {

            @Override
            protected void onResult(String s) throws Exception {
                viewAction.ProfilePwdSuccess();
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
