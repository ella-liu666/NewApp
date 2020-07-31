package com.haixiajiemei.app.Module.Setting.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Setting.CouponUsedTask;
import com.haixiajiemei.app.Module.Setting.Contract.CouponUsedContract;
import com.haixiajiemei.app.Module.Setting.Model.Coupon;

import java.util.List;

public class CouponUsedPresenter implements CouponUsedContract.PresenterAction{

    private CouponUsedContract.ViewAction viewAction;
    private Context mcontext;

    public CouponUsedPresenter(CouponUsedContract.ViewAction viewAction, Context mcontext) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
    }

    @Override
    public void doCouponUsed() {
        DataLoader.run(new CouponUsedTask(mcontext){

            @Override
            protected void onResult(List<Coupon> coupons) throws Exception {
                viewAction.CouponUsedSuccess(coupons);
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
