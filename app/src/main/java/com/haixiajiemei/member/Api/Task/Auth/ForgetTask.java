package com.haixiajiemei.member.Api.Task.Auth;

import android.content.Context;

import com.haixiajiemei.member.Api.Rtf.AuthRtf;
import com.haixiajiemei.member.Api.Task.DataTask;

public class ForgetTask extends DataTask<String> {
    private AuthRtf api;

    private final Context context;
    private final String username;
    private final String telephone;
    private final String password;

    public ForgetTask(Context context, String username, String telephone, String password) {
        api = new AuthRtf(context);

        this.context = context;
        this.username = username;
        this.telephone = telephone;
        this.password = password;
    }

    @Override
    protected String load() throws Exception {
        return api.Forget(username, telephone,password);
    }

    @Override
    protected String parseData(String s) throws Exception {
        return s;
    }
}
