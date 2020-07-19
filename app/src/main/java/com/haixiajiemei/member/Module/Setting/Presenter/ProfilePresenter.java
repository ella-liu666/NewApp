package com.haixiajiemei.member.Module.Setting.Presenter;

import android.content.Context;

import com.haixiajiemei.member.Api.Basic.ApiException;
import com.haixiajiemei.member.Api.DataLoader;
import com.haixiajiemei.member.Api.Task.Setting.ProfileTask;
import com.haixiajiemei.member.Module.Setting.Contract.ProfileContract;
import com.haixiajiemei.member.Module.Setting.Model.Profile;

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
                viewAction.errorOccurred(e.getReason());
            }

            @Override
            protected void onException(Exception e) {
                viewAction.errorOccurred(e.getMessage());
            }
        });
    }
}
