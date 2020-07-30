package com.haixiajiemei.app.Api.Task.Order;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.OrderRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Order.Model.SuccessMessage;
import com.haixiajiemei.app.Parser.ClassParser;

public class InsertDeliveryListTask extends DataTask<SuccessMessage> {
    private OrderRtf api;

    private Context context;
    private String name;
    private String gender;
    private String telephone;
    private String address;
    private String memo;

    public InsertDeliveryListTask(Context context, String name, String gender, String telephone, String address, String memo) {
        api = new OrderRtf(context);

        this.context = context;
        this.name = name;
        this.gender = gender;
        this.telephone = telephone;
        this.address = address;
        this.memo = memo;
    }

    @Override
    protected String load() throws Exception {
        return api.getInsertDeliveryList(name,gender,telephone,address,memo);
    }

    @Override
    protected SuccessMessage parseData(String s) throws Exception {
        SuccessMessage response = ClassParser.toData(s, SuccessMessage.class);
        return response;
    }
}
