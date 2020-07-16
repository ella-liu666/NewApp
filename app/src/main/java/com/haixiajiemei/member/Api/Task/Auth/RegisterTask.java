package com.haixiajiemei.member.Api.Task.Auth;

import android.content.Context;

import com.haixiajiemei.member.Api.Rtf.AuthRtf;
import com.haixiajiemei.member.Api.Task.DataTask;

public class RegisterTask  extends DataTask<String> {
    private AuthRtf api;

    private final Context context;
    private final String username;
    private final String password;
    private final String name;
    private final String telephone;

    public RegisterTask(Context context, String username, String password, String name, String telephone) {
        api = new AuthRtf(context);

        this.context = context;
        this.username = username;
        this.password = password;
        this.name = name;
        this.telephone = telephone;
    }

    @Override
    protected String load() throws Exception {
        return api.signUp(username, password,name,telephone);
    }

    @Override
    protected String parseData(String s) throws Exception {
        return s;
    }
}
