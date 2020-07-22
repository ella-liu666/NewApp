package com.haixiajiemei.member.Api.Task.Setting;

import android.content.Context;

import com.haixiajiemei.member.Api.Rtf.SettingRtf;
import com.haixiajiemei.member.Api.Task.DataTask;
import com.haixiajiemei.member.Module.Setting.Model.CardDetail;
import com.haixiajiemei.member.Parser.ClassParser;

import androidx.annotation.NonNull;

public class VIPDetailTask extends DataTask<CardDetail> {
    private SettingRtf api;

    private final Context context;
    private final int Id;

    public VIPDetailTask(@NonNull Context context,@NonNull int Id) {
        api = new SettingRtf(context);

        this.context = context;
        this.Id = Id;
    }

    @Override
    protected String load() throws Exception {
        return api.getVIPDetail(Id);
    }

    @Override
    protected CardDetail parseData(String s) throws Exception {
        CardDetail response = ClassParser.toData(s, CardDetail.class);
        return response;
    }
}
