package com.haixiajiemei.app.Api.Rtf;

import android.content.Context;
import android.text.TextUtils;

import com.haixiajiemei.app.Api.Basic.ApiException;
import com.haixiajiemei.app.Api.Basic.ErrorBody;
import com.haixiajiemei.app.BuildConfig;
import com.haixiajiemei.app.Helper.PrefsHelper;
import com.haixiajiemei.app.Parser.ClassParser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public abstract class BaseRtf<T> {
    private static final MediaType _JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType _TEXT = MediaType.parse("text/plain");
    private static final MediaType _JPEG = MediaType.parse("image/jpeg");

    protected T api;

    protected BaseRtf(@Nullable Context context) {
        String accessToken = null;
        String Token = null;
        if (context != null) {
            accessToken = PrefsHelper.getAccessToken(context);
            String userToken = context.getSharedPreferences("UserToken", MODE_PRIVATE)
                    .getString("access_token", "");
            Token = userToken;
        }

        this.init(accessToken, Token);
    }

    private void init(@Nullable final String accessToken, @Nullable final String Token) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        // Request customization: add request headers
                        Request.Builder requestBuilder = original.newBuilder()
                                .method(original.method(), original.body());

                        if (!TextUtils.isEmpty(Token)) {
                            requestBuilder.addHeader("Authorization", "Bearer " + Token);
//                            requestBuilder.addHeader("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ4aWFvYmluYmluIiwianRpIjoiYzllZDQ1OWUtM2UyZS00YzJmLWIzYTQtZjgzZmU4NmY2Y2U5Iiwicm9sZXMiOlsiTWVtYmVycyIsIlZpc2l0b3JzIl0sIlVzZXJJRCI6IjEiLCJuYmYiOjE1OTQ4NzUyODcsImV4cCI6MTU5NDg3NTM0NywiaWF0IjoxNTk0ODc1Mjg3LCJpc3MiOiJDaGluYVByb2pjZXQuQVBJIn0.4qTL6L11AC9WOTcbSXrH9HP2sv5nvyRA4A2CbdSHcgs");
                        }

                        Request request = requestBuilder.build();

                        return chain.proceed(request);
                    }
                })
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        this.api = retrofit.create(this.getType());
    }

    protected String execute(Call<String> call) throws Exception {
        Response<String> response = call.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new ApiException(response.code(), response.message(), response.body(), ClassParser.toData(response.errorBody().string(), ErrorBody.class));
        }
    }

    protected abstract Class<T> getType();

    protected RequestBody createTextBody(String value) {
        return RequestBody.create(_TEXT, value);
    }

    protected RequestBody createImageBody(File file) {
        return RequestBody.create(_JPEG, file);
    }

    protected Map<String, RequestBody> createBodyMap(Map<String, String> valueMap) {
        Map<String, RequestBody> bodyMap = new HashMap<>();

        for (String key : valueMap.keySet()) {
            bodyMap.put(key, createTextBody(valueMap.get(key)));
        }

        return bodyMap;
    }
}