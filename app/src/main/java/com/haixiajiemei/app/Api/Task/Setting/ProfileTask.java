package com.haixiajiemei.app.Api.Task.Setting;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.SettingRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Setting.Model.Profile;
import com.haixiajiemei.app.Parser.ClassParser;

import androidx.annotation.NonNull;

public class ProfileTask extends DataTask<Profile> {
    private SettingRtf api;

    public ProfileTask(@NonNull Context context) {
        api = new SettingRtf(context);
    }

    @Override
    protected String load() throws Exception {
        return api.getProfile();
    }

    @Override
    protected Profile parseData(String s) throws Exception {
        Profile response = ClassParser.toData(s, Profile.class);
        return response;
    }
}
