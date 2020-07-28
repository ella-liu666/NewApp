package com.haixiajiemei.app.Api.Task.Setting;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.SettingRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Setting.Model.StoredValue;
import com.haixiajiemei.app.Parser.ClassParser;

public class StoredValueTask extends DataTask<StoredValue> {
    private SettingRtf api;

    private final Context context;
    private final String orderNo;
    private final float value;
    private final String sourceID;//1:微信，2:支付寶

    public StoredValueTask(Context context, String orderNo, float value, String sourceID) {
        api = new SettingRtf(context);

        this.context = context;
        this.orderNo = orderNo;
        this.value = value;
        this.sourceID = sourceID;
    }

    @Override
    protected String load() throws Exception {
        return api.getStoredValue(orderNo,value,sourceID);
    }

    @Override
    protected StoredValue parseData(String s) throws Exception {
        StoredValue response = ClassParser.toData(s, StoredValue.class);
        return response;
    }
}
