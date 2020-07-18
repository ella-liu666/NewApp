package com.haixiajiemei.member.Module.Setting.Presenter;

import android.content.Context;

import com.haixiajiemei.member.Api.Basic.ApiException;
import com.haixiajiemei.member.Api.DataLoader;
import com.haixiajiemei.member.Api.Task.Setting.QRcodePointTask;
import com.haixiajiemei.member.Module.Setting.Contract.QRcodePointContract;

public class QRcodePointPresenter implements QRcodePointContract.PresenterAction {

    private QRcodePointContract.ViewAction viewAction;
    private Context mcontext;

    public QRcodePointPresenter(QRcodePointContract.ViewAction viewAction, Context mcontext) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
    }

    @Override
    public void doQRcodePoint() {
        DataLoader.run(new QRcodePointTask(mcontext){

            @Override
            protected void onResult(String s) throws Exception {
                viewAction.QRcodePointSuccess(s);
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
