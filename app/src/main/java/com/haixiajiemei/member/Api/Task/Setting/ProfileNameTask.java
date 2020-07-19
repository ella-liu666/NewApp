package com.haixiajiemei.member.Api.Task.Setting;

import android.content.Context;

import com.haixiajiemei.member.Api.Rtf.SettingRtf;
import com.haixiajiemei.member.Api.Task.DataTask;

public class ProfileNameTask extends DataTask<String> {
    private SettingRtf api;

    private final Context context;
    private final String name;

    public ProfileNameTask(Context context, String name) {
        api = new SettingRtf(context);

        this.context = context;
        this.name = name;
    }

    @Override
    protected String load() throws Exception {
        return api.getProfileName(name);
    }

    @Override
    protected String parseData(String s) throws Exception {
        return s;
    }
}
