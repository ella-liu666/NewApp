package com.haixiajiemei.member.Api.Task.Setting;

import android.content.Context;
import android.content.SharedPreferences;

import com.haixiajiemei.member.Api.Rtf.SettingRtf;
import com.haixiajiemei.member.Api.Task.DataTask;
import com.haixiajiemei.member.Module.Account.Model.SignIn;
import com.haixiajiemei.member.Parser.ClassParser;

import androidx.annotation.NonNull;

import static android.content.Context.MODE_PRIVATE;

public class SignOutTask extends DataTask<SignIn> {
    private SettingRtf api;

    private Context context;

    public SignOutTask(@NonNull Context context) {
        api = new SettingRtf(context);

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
