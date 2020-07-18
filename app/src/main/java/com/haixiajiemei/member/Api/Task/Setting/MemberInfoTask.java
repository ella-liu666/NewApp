package com.haixiajiemei.member.Api.Task.Setting;

import android.content.Context;

import com.haixiajiemei.member.Api.Rtf.SettingRtf;
import com.haixiajiemei.member.Api.Task.DataTask;
import com.haixiajiemei.member.Module.Setting.Model.MemberInfo;
import com.haixiajiemei.member.Parser.ClassParser;

import androidx.annotation.NonNull;

public class MemberInfoTask extends DataTask<MemberInfo> {
    private SettingRtf api;

    public MemberInfoTask(@NonNull Context context) {
        api = new SettingRtf(context);
    }

    @Override
    protected String load() throws Exception {
        return api.getMemberInfo();
    }

    @Override
    protected MemberInfo parseData(String s) throws Exception {
        MemberInfo response = ClassParser.toData(s, MemberInfo.class);
        return response;
    }
}
