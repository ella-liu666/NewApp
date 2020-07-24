package com.haixiajiemei.app.Api.Task.Setting;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.SettingRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Home.Model.ImgAndTxt;

import java.util.List;

import androidx.annotation.NonNull;

import static com.haixiajiemei.app.Util.FunTools.JSONArrayToClass;

public class VIPCardBuyTask extends DataTask<List<ImgAndTxt>> {

    private SettingRtf api;

    public VIPCardBuyTask(@NonNull Context context) {
        api = new SettingRtf(context);
    }
    @Override
    protected String load() throws Exception {
        return api.getVIPCardBuy();
    }

    @Override
    protected List<ImgAndTxt> parseData(String s) throws Exception {
//        List<ImgAndTxt> response = JSONArrayToTags(s);
        List<ImgAndTxt> response = JSONArrayToClass(s,ImgAndTxt.class);
        return response;
    }
}