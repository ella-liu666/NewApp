package com.haixiajiemei.member.Module.Home.Present;

import android.content.Context;

import com.haixiajiemei.member.Api.Basic.ApiException;
import com.haixiajiemei.member.Api.DataLoader;
import com.haixiajiemei.member.Api.Task.Home.AdImgInforTask;
import com.haixiajiemei.member.Module.Home.Contract.HomeAdImgContract;
import com.haixiajiemei.member.Module.Home.Model.ImgAndTxt;

import java.util.List;

public class HomeAdImgPresenter implements HomeAdImgContract.PresenterAction {

    private HomeAdImgContract.ViewAction viewAction;
    private Context mcontext;

    public HomeAdImgPresenter(HomeAdImgContract.ViewAction viewAction, Context mcontext) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
    }

    @Override
    public void doHomeAdImg() {
        DataLoader.run(new AdImgInforTask(mcontext){

            @Override
            protected void onResult(List<ImgAndTxt> imgAndTxt) throws Exception {
                viewAction.HomeAdImgContractSuccess(imgAndTxt);
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
