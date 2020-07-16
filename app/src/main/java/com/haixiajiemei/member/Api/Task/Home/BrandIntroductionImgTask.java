package com.haixiajiemei.member.Api.Task.Home;

import android.content.Context;

import com.haixiajiemei.member.Api.Rtf.HomeRtf;
import com.haixiajiemei.member.Api.Task.DataTask;
import com.haixiajiemei.member.Module.Home.Model.ImgAndTxt;

import java.util.List;

import androidx.annotation.NonNull;

import static com.haixiajiemei.member.Util.FunTools.JSONArrayToTags;

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
        List<ImgAndTxt> response = JSONArrayToTags(s);
        return response;
    }
}
