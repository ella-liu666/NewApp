package com.haixiajiemei.member.Module.Setting.Presenter;

import android.content.Context;

import com.haixiajiemei.member.Api.Basic.ApiException;
import com.haixiajiemei.member.Api.DataLoader;
import com.haixiajiemei.member.Api.Task.Setting.QRcodeCouponTask;
import com.haixiajiemei.member.Module.Setting.Contract.QRcodeCouponContract;


public class QRcodeCouponPresenter implements QRcodeCouponContract.PresenterAction{

    private QRcodeCouponContract.ViewAction viewAction;
    private Context mcontext;

    public QRcodeCouponPresenter(QRcodeCouponContract.ViewAction viewAction, Context mcontext) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
    }

    @Override
    public void doQRcodeCoupon(Context context, int accountCouponMapID, float denomination, String name, String storeName, String dueTime) {
        DataLoader.run(new QRcodeCouponTask(accountCouponMapID,denomination,name,storeName,dueTime){

            @Override
            protected void onResult(String s) throws Exception {
                viewAction.QRcodeCouponSuccess(s);
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
