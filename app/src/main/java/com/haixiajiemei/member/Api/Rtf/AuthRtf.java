package com.haixiajiemei.member.Api.Rtf;

import android.content.Context;

import com.haixiajiemei.member.Api.Basic.Params;

import java.util.Map;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public class AuthRtf extends BaseRtf<AuthRtf.Service> {

    public AuthRtf(Context context) {
        super(context);
    }

    @Override
    protected Class<Service> getType() {
        return AuthRtf.Service.class;
    }

    public String signUp(@NonNull String userName, @NonNull String password, @NonNull String name, @NonNull String telephone) throws Exception {
        Params params = new Params();
        params.putRequired("userName", userName);
        params.putRequired("password", password);
        params.putRequired("name", name);
        params.putRequired("telephone", telephone);

        return this.execute(this.api.signUp(params.getMap()));
    }

    public String login(@NonNull String userName, @NonNull String password) throws Exception {
        Params params = new Params();
        params.putRequired("userName", userName);
        params.putRequired("password", password);

        return this.execute(this.api.login(params.getMap()));
    }

    public String RefreshToken(@NonNull String refreshToken) throws Exception {
        Params params = new Params();
        params.putRequired("refreshToken", refreshToken);

        return this.execute(this.api.RefreshToken(params.getMap()));
    }

    public String Forget(@NonNull String userName, @NonNull String telephone, @NonNull String password) throws Exception {
        Params params = new Params();
        params.putRequired("userName", userName);
        params.putRequired("telephone", telephone);
        params.putRequired("password", password);

        return this.execute(this.api.Forget(params.getMap()));
    }

    public String getVisitors(@NonNull String Key) throws Exception {
        return this.execute(this.api.Visitors(Key));
    }

    public interface Service {
        @POST("Wallet/Member/Visitors")
        Call<String> Visitors(@Query("SecretKey") String Key );

        @POST("Wallet/Member/SignUp")
        Call<String> signUp(@Body Map<String, Object> params);

        @POST("Wallet/Member")
        Call<String> login(@Body Map<String, Object> params);

        @PUT("Wallet/Member")
        Call<String> RefreshToken(@Body Map<String, Object> params);

        @PUT("Wallet/Member/PassWord")
        Call<String> Forget(@Body Map<String, Object> params);
    }
}
