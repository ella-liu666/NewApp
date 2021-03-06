package com.haixiajiemei.app.Api.Task.Setting;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.SettingRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Setting.Model.CardDetail;
import com.haixiajiemei.app.Parser.ClassParser;

import androidx.annotation.NonNull;

public class CardDetailTask extends DataTask<CardDetail> {
    private SettingRtf api;

    private final Context context;
    private final int Id;

    public CardDetailTask(@NonNull Context context,@NonNull int Id) {
        api = new SettingRtf(context);

        this.context = context;
        this.Id = Id;
    }

    @Override
    protected String load() throws Exception {
        return api.getCardDetail(Id);
    }

    @Override
    protected CardDetail parseData(String s) throws Exception {
        CardDetail response = ClassParser.toData(s, CardDetail.class);
        return response;
    }
}
