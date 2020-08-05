package com.haixiajiemei.app.Api.Rtf;

import android.content.Context;

import androidx.annotation.NonNull;

import com.haixiajiemei.app.Api.Basic.Params;
import com.haixiajiemei.app.Module.Order.Model.ShoppingCart;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class ECRtf extends BaseRtf<ECRtf.Service> {

    public ECRtf(Context context) {
        super(context);
    }

    @Override
    protected Class<Service> getType() {
        return ECRtf.Service.class;
    }

    public String getItemsList(@NonNull int ecCategoryID) throws Exception {
        Params params = new Params();
        params.putRequired("ecCategoryID", ecCategoryID);
        return this.execute(this.api.GetItemsList(params.getMap()));
    }

    public String getCheckoutEC(@NonNull float total, @NonNull String delType, @NonNull int deliveryID, @NonNull List<ShoppingCart> cart) throws Exception {
        Params params = new Params();
        params.putRequired("total", total);
        params.putRequired("delType", delType);
        params.putRequired("deliveryID", deliveryID);
        params.putRequired("cart", cart);
        return this.execute(this.api.CheckoutEC(params.getMap()));
    }
    public interface Service {
        @POST("EC/GetItemsList")
        Call<String> GetItemsList(@Body Map<String, Object> params);

        @POST("EC/CheckoutEC")
        Call<String> CheckoutEC(@Body Map<String, Object> params);
    }
}
