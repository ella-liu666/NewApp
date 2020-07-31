package com.haixiajiemei.app.Module.Setting.Present;

import android.content.Context;
import android.util.Log;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Setting.MemberInfoTask;
import com.haixiajiemei.app.Module.Setting.Contract.SettingContract;
import com.haixiajiemei.app.Module.Setting.Model.MemberInfo;

public class SettingPresenter implements SettingContract.PresenterAction {

    private SettingContract.ViewAction viewAction;
    private Context mcontext;

    public SettingPresenter(SettingContract.ViewAction viewAction, Context mcontext) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
    }

    @Override
    public void doSettingMemberInfo() {
        DataLoader.run(new MemberInfoTask(mcontext){

            @Override
            protected void onResult(MemberInfo memberInfos) throws Exception {
                viewAction.SettingMemberInfoSuccess(memberInfos);
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
