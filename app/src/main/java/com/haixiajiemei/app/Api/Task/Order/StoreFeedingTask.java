package com.haixiajiemei.app.Api.Task.Order;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.OrderRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Order.Model.IdAndTxt;
import com.haixiajiemei.app.Parser.ClassParser;

public class StoreFeedingTask extends DataTask<IdAndTxt> {
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
    protected IdAndTxt parseData(String s) throws Exception {
        IdAndTxt response = ClassParser.toData(s, IdAndTxt.class);
        return response;
    }
}
