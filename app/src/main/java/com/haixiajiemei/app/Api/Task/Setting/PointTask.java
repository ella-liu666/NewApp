package com.haixiajiemei.app.Api.Task.Setting;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.SettingRtf;
import com.haixiajiemei.app.Api.Task.DataTask;

import androidx.annotation.NonNull;

public class PointTask extends DataTask<String> {
    private SettingRtf api;

    public PointTask(@NonNull Context context) {
        api = new SettingRtf(context);
    }
    @Override
    protected String load() throws Exception {
        return api.getPoint();
    }

    @Override
    protected String parseData(String s) throws Exception {
        String point=s.replace("\"", "");
        return point;
    }
}
