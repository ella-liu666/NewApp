package com.haixiajiemei.member.Module.Account.Present;

import android.content.Context;

import com.haixiajiemei.member.Api.Basic.ApiException;
import com.haixiajiemei.member.Api.DataLoader;
import com.haixiajiemei.member.Api.Task.Auth.VisitorsTask;
import com.haixiajiemei.member.Module.Account.Contract.VisitorsContract;

public class VisitorsPresenter implements VisitorsContract.PresenterAction {

    private VisitorsContract.ViewAction viewAction;
    private Context mcontext;
    private String Key;

    public VisitorsPresenter(VisitorsContract.ViewAction viewAction, Context mcontext, String key) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
        Key = key;
    }

    @Override
    public void doVisitors() {
        DataLoader.run(new VisitorsTask(mcontext,Key){

            @Override
            protected void onResult(String s) throws Exception {
                viewAction.VisitorsSuccess();
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
