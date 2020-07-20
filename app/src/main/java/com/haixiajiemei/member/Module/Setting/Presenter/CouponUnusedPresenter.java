package com.haixiajiemei.member.Module.Setting.Presenter;

import android.content.Context;

import com.haixiajiemei.member.Api.Basic.ApiException;
import com.haixiajiemei.member.Api.DataLoader;
import com.haixiajiemei.member.Api.Task.Setting.CouponUnusedTask;
import com.haixiajiemei.member.Module.Setting.Contract.CouponUnusedContract;
import com.haixiajiemei.member.Module.Setting.Model.Coupon;

import java.util.List;

public class CouponUnusedPresenter implements CouponUnusedContract.PresenterAction {

    private CouponUnusedContract.ViewAction viewAction;
    private Context mcontext;

    public CouponUnusedPresenter(CouponUnusedContract.ViewAction viewAction, Context mcontext) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
    }

    @Override
    public void doCouponUnused() {
        DataLoader.run(new CouponUnusedTask(mcontext){

            @Override
            protected void onResult(List<Coupon> coupons) throws Exception {
                viewAction.CouponUnusedSuccess(coupons);
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
