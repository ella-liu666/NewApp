package com.haixiajiemei.member.Api.Task.Auth;

import android.content.Context;
import android.content.SharedPreferences;

import com.haixiajiemei.member.Api.Rtf.AuthRtf;
import com.haixiajiemei.member.Api.Task.DataTask;
import com.haixiajiemei.member.Module.Account.Model.SignIn;
import com.haixiajiemei.member.Parser.ClassParser;

import static android.content.Context.MODE_PRIVATE;

public class LoginTask extends DataTask<SignIn> {
    private AuthRtf api;

    private final Context context;
    private final String username;
    private final String password;

    public LoginTask(Context context, String username, String password) {
        api = new AuthRtf(context);

        this.context = context;
        this.username = username;
        this.password = password;
    }

    @Override
    protected String load() throws Exception {
        return api.login(username, password);
    }

    @Override
    protected SignIn parseData(String s) throws Exception {
        SignIn response = ClassParser.toData(s, SignIn.class);
        SharedPreferences pref = context.getSharedPreferences("UserToken", MODE_PRIVATE);
        pref.edit()
                .putString("access_token", response.getAccess_token())
                .putBoolean("loginStatus", response.getLoginStatus())
                .commit();

        return response;
    }
}
