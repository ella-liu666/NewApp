package com.haixiajiemei.app.Api.Task.Order;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.OrderRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Order.Model.IdAndTxt;
import com.haixiajiemei.app.Module.Order.Model.StoreFeed;
import com.haixiajiemei.app.Parser.ClassParser;

public class StoreFeedingTask extends DataTask<StoreFeed> {
    private OrderRtf api;

    private Context context;
    private String dbName;
    private int mcid;

    public StoreFeedingTask(Context context, String dbName, int mcid) {
        api = new OrderRtf(context);

        this.context = context;
        this.dbName = dbName;
        this.mcid = mcid;
    }

    @Override
    protected String load() throws Exception {
        return api.getStoreFeeding(dbName,mcid);
    }

    @Override
    protected StoreFeed parseData(String s) throws Exception {
        StoreFeed response = ClassParser.toData(s, StoreFeed.class);
        return response;
    }
}
