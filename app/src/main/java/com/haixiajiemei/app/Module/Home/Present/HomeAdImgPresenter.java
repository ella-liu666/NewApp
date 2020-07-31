package com.haixiajiemei.app.Module.Home.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Home.AdImgInforTask;
import com.haixiajiemei.app.Module.Home.Contract.HomeAdImgContract;
import com.haixiajiemei.app.Module.Home.Model.ImgAndTxt;

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
                viewAction.ApierrorOccurred(e.getErrorBody().getAccess_token());
            }

            @Override
            protected void onException(Exception e) {
                viewAction.errorOccurred(e.getMessage());
            }
        });
    }
}
