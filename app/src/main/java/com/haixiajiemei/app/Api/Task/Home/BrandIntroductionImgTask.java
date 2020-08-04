package com.haixiajiemei.app.Api.Task.Home;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.HomeRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Home.Model.ImgAndTxt;
import com.haixiajiemei.app.Parser.ClassParser;


import androidx.annotation.NonNull;


public class BrandIntroductionImgTask extends DataTask<ImgAndTxt> {
    private HomeRtf api;

    private final Context context;
    private final int Id;

    public BrandIntroductionImgTask(@NonNull Context context,@NonNull int Id) {
        api=new HomeRtf(context);

        this.context = context;
        this.Id = Id;
    }
    @Override
    protected String load() throws Exception {
        return api.getBrandIntroductionImg(Id);
    }

    @Override
    protected ImgAndTxt parseData(String s) throws Exception {
        ImgAndTxt response =  ClassParser.toData(s,ImgAndTxt.class);
        return response;
    }
}
