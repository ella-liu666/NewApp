package com.haixiajiemei.member.Module.Setting.Presenter;

import android.content.Context;

import com.haixiajiemei.member.Api.Basic.ApiException;
import com.haixiajiemei.member.Api.DataLoader;
import com.haixiajiemei.member.Api.Task.Setting.CouponUsedTask;
import com.haixiajiemei.member.Module.Setting.Contract.CouponUsedContract;
import com.haixiajiemei.member.Module.Setting.Model.Coupon;

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
                viewAction.errorOccurred(e.getReason());
            }

            @Override
            protected void onException(Exception e) {
                viewAction.errorOccurred(e.getMessage());
            }
        });
    }
}
