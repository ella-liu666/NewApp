package com.haixiajiemei.app.Api.Task.Order;

import android.content.Context;

import com.haixiajiemei.app.Api.Rtf.OrderRtf;
import com.haixiajiemei.app.Api.Task.DataTask;
import com.haixiajiemei.app.Module.Order.Model.SuccessMessage;
import com.haixiajiemei.app.Parser.ClassParser;

public class DeleteDeliveryAddressTask extends DataTask<SuccessMessage> {
    private OrderRtf api;

    private Context context;
    private int deliveryID;

    public DeleteDeliveryAddressTask(Context context, int deliveryID) {
        api = new OrderRtf(context);

        this.context = context;
        this.deliveryID = deliveryID;
    }

    @Override
    protected String load() throws Exception {
        return api.getDeleteDeliveryAddress(deliveryID);
    }

    @Override
    protected SuccessMessage parseData(String s) throws Exception {
        SuccessMessage response = ClassParser.toData(s, SuccessMessage.class);
        return response;
    }
}
