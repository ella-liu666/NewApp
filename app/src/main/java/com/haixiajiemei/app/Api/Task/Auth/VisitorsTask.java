package com.haixiajiemei.app.Api.Task.Auth;

import android.content.Context;
import android.content.SharedPreferences;

import com.haixiajiemei.app.Api.Rtf.AuthRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Account.Model.SignIn;
import com.haixiajiemei.app.Parser.ClassParser;

import static android.content.Context.MODE_PRIVATE;

public class VisitorsTask extends DataTask<SignIn> {
    private AuthRtf api;

    private final Context context;
    private final String Key;

    public VisitorsTask(Context context, String Key) {
        api = new AuthRtf(null);

        this.context = context;
        this.Key = Key;
    }

    @Override
    protected String load() throws Exception {
        return api.getVisitors(Key);
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
