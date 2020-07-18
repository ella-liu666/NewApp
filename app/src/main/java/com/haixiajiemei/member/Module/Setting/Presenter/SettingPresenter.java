package com.haixiajiemei.member.Module.Setting.Presenter;

import android.content.Context;

import com.haixiajiemei.member.Api.Basic.ApiException;
import com.haixiajiemei.member.Api.DataLoader;
import com.haixiajiemei.member.Api.Task.Setting.MemberInfoTask;
import com.haixiajiemei.member.Module.Setting.Contract.SettingContract;
import com.haixiajiemei.member.Module.Setting.Model.MemberInfo;

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
                viewAction.errorOccurred(e.getReason());
            }

            @Override
            protected void onException(Exception e) {
                viewAction.errorOccurred(e.getMessage());
            }
        });
    }
}
