package com.haixiajiemei.app.Module.Setting.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Setting.CouponExpiredTask;
import com.haixiajiemei.app.Module.Setting.Contract.CouponExpiredContract;
import com.haixiajiemei.app.Module.Setting.Model.Coupon;

import java.util.List;

public class CouponExpiredPresenter implements CouponExpiredContract.PresenterAction{

    private CouponExpiredContract.ViewAction viewAction;
    private Context mcontext;

    public CouponExpiredPresenter(CouponExpiredContract.ViewAction viewAction, Context mcontext) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
    }

    @Override
    public void doCouponExpired() {
        DataLoader.run(new CouponExpiredTask(mcontext){

            @Override
            protected void onResult(List<Coupon> coupons) throws Exception {
                viewAction.CouponExpiredSuccess(coupons);
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
