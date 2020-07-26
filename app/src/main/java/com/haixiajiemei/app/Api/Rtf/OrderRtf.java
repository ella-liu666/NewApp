package com.haixiajiemei.app.Api.Rtf;

import android.content.Context;

import com.haixiajiemei.app.Api.Basic.Params;
import com.haixiajiemei.app.Module.Order.Model.ShoppingCart;

import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class OrderRtf extends BaseRtf<OrderRtf.Service> {

    public OrderRtf(Context context) {
        super(context);
    }

    @Override
    protected Class<OrderRtf.Service> getType() {
            return OrderRtf.Service.class;
    }

    public String getStoreList() throws Exception {
        return this.execute(this.api.StoreList());
    }

    public String getStoreFilter(@NonNull int dbid,@NonNull String dbName) throws Exception {
        Params params = new Params();
        params.putRequired("dbid", dbid);
        params.putRequired("dbName", dbName);

        return this.execute(this.api.StoreFilter(params.getMap()));
    }

    public String getStoreItem(@NonNull String dbName,@NonNull int mcid) throws Exception {
        Params params = new Params();
        params.putRequired("dbName", dbName);
        params.putRequired("mcid", mcid);

        return this.execute(this.api.StoreItem(params.getMap()));
    }

    public String getStoreFeeding(@NonNull String dbName,@NonNull int mcid) throws Exception {
        Params params = new Params();
        params.putRequired("dbName", dbName);
        params.putRequired("mcid", mcid);

        return this.execute(this.api.StoreFeeding(params.getMap()));
    }

    public String getCart(@NonNull String storeAccount,@NonNull float total,@NonNull String delType,@NonNull List<ShoppingCart> cart) throws Exception {
        Params params = new Params();
        params.putRequired("storeAccount", storeAccount);
        params.putRequired("total", total);
        params.putRequired("delType", delType);
        params.putRequired("cart", cart);

        return this.execute(this.api.Cart(params.getMap()));
    }



    public interface Service {
        @GET("Order/Order/StoreList")
        Call<String> StoreList();

        @POST("Order/Order/StoreFilter")
        Call<String> StoreFilter(@Body Map<String, Object> params);

        @POST("Order/Order/StoreItem")
        Call<String> StoreItem(@Body Map<String, Object> params);

        @POST("Order/Order/StoreFeeding")
        Call<String> StoreFeeding(@Body Map<String, Object> params);

        @POST("Order/Order/Cart")
        Call<String> Cart(@Body Map<String, Object> params);
    }
}
