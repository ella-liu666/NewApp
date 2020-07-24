package com.haixiajiemei.app.Api.Task.Home;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.HomeRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Home.Model.ImgAndTxt;

import java.util.List;

import androidx.annotation.NonNull;

import static com.haixiajiemei.app.Util.FunTools.JSONArrayToClass;

public class BrandIntroductionImgTask extends DataTask<List<ImgAndTxt>> {
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
    protected List<ImgAndTxt> parseData(String s) throws Exception {
//        List<ImgAndTxt> response = JSONArrayToTags(s);
        List<ImgAndTxt> response = JSONArrayToClass(s,ImgAndTxt.class);
        return response;
    }
}
