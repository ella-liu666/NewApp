package com.haixiajiemei.member.Module.Home.Present;

import android.content.Context;
import android.util.Log;

import com.haixiajiemei.member.Api.Basic.ApiException;
import com.haixiajiemei.member.Api.DataLoader;
import com.haixiajiemei.member.Api.Task.Home.BrandIntroductionImgTask;
import com.haixiajiemei.member.Module.Home.Contract.BrandIntroductionImgContract;
import com.haixiajiemei.member.Module.Home.Model.ImgAndTxt;

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
        DataLoader.run(new BrandIntroductionImgTask(mcontext,Id){

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
                viewAction.errorOccurred(e.getReason());
            }

            @Override
            protected void onException(Exception e) {
                viewAction.errorOccurred(e.getMessage());
                Log.e("bbbb====","gg ="+e.getMessage());
            }
        });
    }

}
