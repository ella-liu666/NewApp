package com.haixiajiemei.app.wxapi;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.haixiajiemei.app.Module.Account.Fragment.LoginFragment;
import com.haixiajiemei.app.R;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

import static com.haixiajiemei.app.wxapi.Constants.APP_ID;

public class WXPayActivity extends AppCompatActivity {

    OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
    private static final String TAG = "PayActivity";
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wxpay);
        final IWXAPI wxapi = WXAPIFactory.createWXAPI(this, APP_ID, false);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setEnabled(false);
                String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
                Request request = new Request.Builder().url(url).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        mHandler.postDelayed(() -> {
                            button.setEnabled(true);
                            Toast.makeText(WXPayActivity.this, "请求失败", Toast.LENGTH_LONG).show();
                        }, 1);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        mHandler.postDelayed(() -> {
                            if (response.isSuccessful()) {
                                try {
                                    JSONObject jsonObject = null;
                                    try {
                                        jsonObject = new JSONObject(response.body().string());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    Log.i(TAG, jsonObject.toString());
                                    int code = jsonObject.getInt("code");
                                    if (code == 0) {
                                        JSONObject data = jsonObject.getJSONObject("data");
                                        String appId = data.getString("appid");
                                        String partnerId = data.getString("partnerid");
                                        String prepayId = data.getString("prepayid");
                                        String packageValue = data.getString("package");
                                        String nonceStr = data.getString("noncestr");
                                        String timeStamp = data.getString("timestamp");
                                        String extData = data.getString("extdata");
                                        String sign = data.getString("sign");
                                        PayReq req = new PayReq();
                                        req.appId = appId;
                                        req.partnerId = partnerId;
                                        req.prepayId = prepayId;
                                        req.packageValue = packageValue;
                                        req.nonceStr = nonceStr;
                                        req.timeStamp = timeStamp;
                                        req.extData = extData;
                                        req.sign = sign;
                                        boolean result = wxapi.sendReq(req);
                                        Toast.makeText(WXPayActivity.this, "调起支付结果:" + result, Toast.LENGTH_LONG).show();
//
                                    } else {
                                        Toast.makeText(WXPayActivity.this, "数据出错", Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            button.setEnabled(true);
                        }, 1);
                    }
                });
            }
        });
    }
}
