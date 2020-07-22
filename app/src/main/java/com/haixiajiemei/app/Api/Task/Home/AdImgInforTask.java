package com.haixiajiemei.app.Api.Task.Home;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.HomeRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Home.Model.ImgAndTxt;

import java.util.List;

import androidx.annotation.NonNull;

import static com.haixiajiemei.app.Util.FunTools.JSONArrayToTags;

public class AdImgInforTask  extends DataTask<List<ImgAndTxt>> {
    private HomeRtf api;

    public AdImgInforTask(@NonNull Context context) {
        api=new HomeRtf(context);
    }
    @Override
    protected String load() throws Exception {
        return  api.getAdImgInfor();
    }

    @Override
    protected List<ImgAndTxt> parseData(String s) throws Exception {
        List<ImgAndTxt> response = JSONArrayToTags(s);
        return response;
    }
}
