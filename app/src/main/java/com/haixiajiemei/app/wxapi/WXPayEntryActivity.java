package com.haixiajiemei.app.wxapi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.haixiajiemei.app.Module.Setting.Contract.StoredValueContract;
import com.haixiajiemei.app.Module.Setting.Present.StoredValuePresenter;
import com.haixiajiemei.app.R;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler, StoredValueContract.ViewAction {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
    public static final String APP_ID = "wxd2070218abd7547a";
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private StoredValuePresenter storedValuePresenter;

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
//		Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);
        if (resp.errCode == BaseResp.ErrCode.ERR_USER_CANCEL) {
            Toast.makeText(this, "errCode:" + resp.errCode, Toast.LENGTH_LONG).show();
        } else if (resp.errCode == BaseResp.ErrCode.ERR_OK) {
            Toast.makeText(this, "errCode:" + "支付成功", Toast.LENGTH_LONG).show();
        }
//            switch (resp.errCode) {
//                case 0:
//                    storedValuePresenter = new StoredValuePresenter(this, this, getSharedPreferences("WxPay", MODE_PRIVATE).getString("orderNo", "")
//                            , getSharedPreferences("WxPay", MODE_PRIVATE).getFloat("value", 0), getSharedPreferences("WxPay", MODE_PRIVATE).getString("sourceID", ""));
//                    storedValuePresenter.doStoredValue();
//                    break;
//                case -1:
//                    AlertDialog(R.string.RechargeFailed);
//                    Toast.makeText(this, "errCode:" + resp.errCode, Toast.LENGTH_LONG).show();
//                    break;
//                case -2:
//                    AlertDialog(R.string.RechargeCancel);
//                    Toast.makeText(this, "errCode:" + resp.errCode, Toast.LENGTH_LONG).show();
//                    break;
//            }
    }

    private void AlertDialog(int Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setMessage(Message);
        builder.setPositiveButton(R.string.confirm, (dialog, which) -> {
            dialog.dismiss();
            finish();
        });
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void StoredValueSuccess() {
        mHandler.postDelayed(() -> {
            AlertDialog(R.string.TopUpSuccessfully);
        }, 1);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void errorOccurred(String reason) {

    }

    @Override
    public void ApierrorOccurred(String Access_token) {
//        mHandler.postDelayed(() -> {
//            AlertDialog(R.string.TopUpSuccessfully);
//        }, 1);
    }
}