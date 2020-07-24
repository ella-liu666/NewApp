package com.haixiajiemei.app.Api.Task.Home;

import android.content.Context;
import androidx.annotation.NonNull;

import com.haixiajiemei.app.Api.Rtf.HomeRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Home.Model.ImgAndTxt;

import java.util.List;

import static com.haixiajiemei.app.Util.FunTools.JSONArrayToClass;

public class ImgInforTask extends DataTask<List<ImgAndTxt>> {
    private HomeRtf api;

    public ImgInforTask(@NonNull Context context) {
        api=new HomeRtf(context);
    }
    @Override
    protected String load() throws Exception {
        return api.getBrandImgInfor();
    }

    @Override
    protected List<ImgAndTxt> parseData(String s) throws Exception {
//        List<ImgAndTxt> response = JSONArrayToTags(s);
        List<ImgAndTxt> response = JSONArrayToClass(s,ImgAndTxt.class);
        return response;
    }
}
