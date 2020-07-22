package com.haixiajiemei.app.Api.Task.Setting;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.SettingRtf;
import com.haixiajiemei.app.Api.Task.DataTask;

import androidx.annotation.NonNull;

public class QRcodePointTask extends DataTask<String> {
    private SettingRtf api;

    public QRcodePointTask(@NonNull Context context) {
        api = new SettingRtf(context);
    }
    @Override
    protected String load() throws Exception {
        return api.getQRcodePoint();
    }

    @Override
    protected String parseData(String s) throws Exception {
        return s;
    }
}
