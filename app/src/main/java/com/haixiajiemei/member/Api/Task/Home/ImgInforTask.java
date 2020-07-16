package com.haixiajiemei.member.Api.Task.Home;

import android.content.Context;
import androidx.annotation.NonNull;

import com.haixiajiemei.member.Api.Rtf.HomeRtf;
import com.haixiajiemei.member.Api.Task.DataTask;
import com.haixiajiemei.member.Module.Home.Model.ImgAndTxt;

import java.util.List;

import static com.haixiajiemei.member.Util.FunTools.JSONArrayToTags;

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
        List<ImgAndTxt> response = JSONArrayToTags(s);
        return response;
    }
}
