package com.haixiajiemei.member.Api.Task.Setting;

import android.content.Context;

import com.haixiajiemei.member.Api.Rtf.SettingRtf;
import com.haixiajiemei.member.Api.Task.DataTask;

public class ProfilePwdTask extends DataTask<String> {
    private SettingRtf api;

    private final Context context;
    private final String nowPassword;
    private final String newPassword;

    public ProfilePwdTask(Context context, String nowPassword, String newPassword) {
        api = new SettingRtf(context);

        this.context = context;
        this.nowPassword = nowPassword;
        this.newPassword = newPassword;
    }

    @Override
    protected String load() throws Exception {
        return api.getProfilePwd(nowPassword,newPassword);
    }

    @Override
    protected String parseData(String s) throws Exception {
        return s;
    }
}
