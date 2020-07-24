package com.haixiajiemei.app.Api.Task.Order;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.OrderRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Order.Model.StoreItem;
import com.haixiajiemei.app.Parser.ClassParser;

public class StoreItemTask extends DataTask<StoreItem> {
    private OrderRtf api;

    private Context context;
    private String dbName;
    private int mcid;

    public StoreItemTask(Context context, String dbName, int mcid) {
        api = new OrderRtf(context);

        this.context = context;
        this.dbName = dbName;
        this.mcid = mcid;
    }

    @Override
    protected String load() throws Exception {
        return api.getStoreItem(dbName,mcid);
    }

    @Override
    protected StoreItem parseData(String s) throws Exception {
        StoreItem response = ClassParser.toData(s, StoreItem.class);
        return response;
    }
}
