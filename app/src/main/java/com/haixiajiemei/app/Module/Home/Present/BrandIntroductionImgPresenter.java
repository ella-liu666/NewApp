package com.haixiajiemei.app.Module.Home.Present;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.DataLoader;
import com.haixiajiemei.app.Api.Task.Home.BrandIntroductionImgTask;
import com.haixiajiemei.app.Module.Home.Contract.BrandIntroductionImgContract;
import com.haixiajiemei.app.Module.Home.Model.ImgAndTxt;

import java.util.List;

public class BrandIntroductionImgPresenter implements BrandIntroductionImgContract.PresenterAction {

    private BrandIntroductionImgContract.ViewAction viewAction;
    private Context mcontext;
    private int Id;

    public BrandIntroductionImgPresenter(BrandIntroductionImgContract.ViewAction viewAction, Context mcontext, int id) {
        this.viewAction = viewAction;
        this.mcontext = mcontext;
        Id = id;
    }

    @Override
    public void doBrandIntroductionImg() {
        DataLoader.run(new BrandIntroductionImgTask(mcontext, Id) {

            @Override
            protected void onResult(List<ImgAndTxt> imgAndTxt) throws Exception {
                viewAction.BrandIntroductionImgContractSuccess(imgAndTxt);
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
