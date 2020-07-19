package com.haixiajiemei.member.Api.Task.Setting;

import android.content.Context;

import com.haixiajiemei.member.Api.Rtf.SettingRtf;
import com.haixiajiemei.member.Api.Task.DataTask;
import com.haixiajiemei.member.Module.Setting.Model.Profile;
import com.haixiajiemei.member.Parser.ClassParser;

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
