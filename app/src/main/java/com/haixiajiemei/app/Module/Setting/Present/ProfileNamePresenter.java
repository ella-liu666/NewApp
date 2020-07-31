package com.haixiajiemei.app.Module.Setting.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Setting.ProfileNameTask;
import com.haixiajiemei.app.Module.Setting.Contract.ProfileNameContract;

public class ProfileNamePresenter implements ProfileNameContract.PresenterAction{

    private ProfileNameContract.ViewAction viewAction;
    private Context mcontext;
    private String name;

    public ProfileNamePresenter(ProfileNameContract.ViewAction viewAction, Context mcontext, String name) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
        this.name = name;
    }

    @Override
    public void doProfileName() {
        DataLoader.run(new ProfileNameTask(mcontext,name) {

            @Override
            protected void onResult(String s) throws Exception {
                viewAction.ProfileNameSuccess();
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
