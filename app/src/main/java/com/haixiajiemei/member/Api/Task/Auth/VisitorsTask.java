package com.haixiajiemei.member.Api.Task.Auth;

import android.content.Context;
import android.content.SharedPreferences;

import com.haixiajiemei.member.Api.Rtf.AuthRtf;
import com.haixiajiemei.member.Api.Task.DataTask;

import static android.content.Context.MODE_PRIVATE;

public class VisitorsTask extends DataTask<String> {
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
    protected String parseData(String s) throws Exception {
        SharedPreferences pref = context.getSharedPreferences("UserToken", MODE_PRIVATE);
        pref.edit()
                .putString("access_token", s.replace("\"", ""))
                .commit();
        return s;
    }
}
