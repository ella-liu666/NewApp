package com.haixiajiemei.member.Api.Task.Setting;

import android.content.Context;

import com.haixiajiemei.member.Api.Rtf.SettingRtf;
import com.haixiajiemei.member.Api.Task.DataTask;
import com.haixiajiemei.member.Module.Home.Model.ImgAndTxt;

import java.util.List;

import androidx.annotation.NonNull;

import static com.haixiajiemei.member.Util.FunTools.JSONArrayToTags;

public class VIPCardTask extends DataTask<List<ImgAndTxt>> {

    private SettingRtf api;

    public VIPCardTask(@NonNull Context context) {
        api = new SettingRtf(context);
    }
    @Override
    protected String load() throws Exception {
        return api.getVIPCard();
    }

    @Override
    protected List<ImgAndTxt> parseData(String s) throws Exception {
        List<ImgAndTxt> response = JSONArrayToTags(s);
        return response;
    }
}