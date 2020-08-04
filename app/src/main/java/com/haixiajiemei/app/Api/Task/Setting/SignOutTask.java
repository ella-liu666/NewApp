package com.haixiajiemei.app.Api.Task.Setting;

import android.content.Context;
import android.content.SharedPreferences;

import com.haixiajiemei.app.Api.Rtf.AuthRtf;
import com.haixiajiemei.app.Api.Rtf.SettingRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Account.Model.SignIn;
import com.haixiajiemei.app.Parser.ClassParser;

import androidx.annotation.NonNull;

import static android.content.Context.MODE_PRIVATE;

public class SignOutTask extends DataTask<SignIn> {
    private AuthRtf api;

    private Context context;

    public SignOutTask(@NonNull Context context) {
        api = new AuthRtf(context);

        this.context = context;
    }

    @Override
    protected String load() throws Exception {
        return api.getsign_out();
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
