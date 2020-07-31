package com.haixiajiemei.app.Module.Setting.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Setting.ProfileTask;
import com.haixiajiemei.app.Module.Setting.Contract.ProfileContract;
import com.haixiajiemei.app.Module.Setting.Model.Profile;

public class ProfilePresenter implements ProfileContract.PresenterAction {

    private ProfileContract.ViewAction viewAction;
    private Context mcontext;

    public ProfilePresenter(ProfileContract.ViewAction viewAction, Context mcontext) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
    }

    @Override
    public void doProfile() {
        DataLoader.run(new ProfileTask(mcontext){

            @Override
            protected void onResult(Profile Profile) throws Exception {
                viewAction.ProfileSuccess(Profile);
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
