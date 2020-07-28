package com.haixiajiemei.app.Module.Setting.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.haixiajiemei.app.Aipay.util.PayResult;
import com.haixiajiemei.app.MainActivity;
import com.haixiajiemei.app.Module.Setting.Contract.AlipayRequestContract;
import com.haixiajiemei.app.Module.Setting.Contract.StoredValueContract;
import com.haixiajiemei.app.Module.Setting.Model.AlipayRequest;
import com.haixiajiemei.app.Module.Setting.Present.AlipayRequestPresenter;
import com.haixiajiemei.app.Module.Setting.Present.StoredValuePresenter;
import com.haixiajiemei.app.R;
import com.haixiajiemei.app.ToolBarActivity;
import com.haixiajiemei.app.wxapi.Constants;
import com.haixiajiemei.app.wxapi.WXPayActivity;
import com.haixiajiemei.app.wxapi.WXPayEntryActivity;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.haixiajiemei.app.Util.FunTools.CreateAlertDialogTool;
import static com.haixiajiemei.app.Util.FunTools.switchFragmentToBack;
import static com.haixiajiemei.app.Util.Proclaim.RECHARGEPLAN;

public class AccountFragment extends Fragment implements AlipayRequestContract.ViewAction, StoredValueContract.ViewAction {

    @BindView(R.id.RechargePlan)
    TextView RechargePlan;
    @BindView(R.id.Statement)
    TextView Statement;
    @BindView(R.id.three_hundred)
    LinearLayout three_hundred;
    @BindView(R.id.five_hundred)
    LinearLayout five_hundred;
    @BindView(R.id.one_thousand)
    LinearLayout one_thousand;
    @BindView(R.id.two_thousand)
    LinearLayout two_thousand;
    @BindView(R.id.Five_thousand)
    LinearLayout Five_thousand;

    @BindView(R.id.txt_three_hundred)
    TextView txt_three_hundred;
    @BindView(R.id.txt_five_hundred)
    TextView txt_five_hundred;
    @BindView(R.id.txt_one_thousand)
    TextView txt_one_thousand;
    @BindView(R.id.txt_two_thousand)
    TextView txt_two_thousand;
    @BindView(R.id.txt_Five_thousand)
    TextView txt_Five_thousand;
    @BindView(R.id.BalanceNum)
    TextView BalanceNum;

    @BindView(R.id.payment_method)
    RadioGroup payment_method;
    @BindView(R.id.WeChat)
    RadioButton WeChat;
    @BindView(R.id.Alipay)
    RadioButton Alipay;
    @BindView(R.id.edit_Bonus)
    EditText edit_Bonus;
    @BindView(R.id.btn_Bonus)
    Button btn_Bonus;

    private Handler mHandler = new Handler(Looper.getMainLooper());
    private AlipayRequestPresenter alipayRequestPresenter;
    private StoredValuePresenter storedValuePresenter;
    private static final int SDK_PAY_FLAG = 1;
    private float value;
    private String orderNo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this, view);
        if (getArguments().getString("Balance") != null) {
            BalanceNum.setText(String.valueOf(getArguments().getString("Balance")));
        } else {
            BalanceNum.setText(String.valueOf(getArguments().getFloat("Balance")));
        }
        return view;
    }

    @OnClick({R.id.RechargePlan, R.id.three_hundred, R.id.five_hundred, R.id.one_thousand, R.id.two_thousand, R.id.Five_thousand, R.id.Statement, R.id.btn_Bonus})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.RechargePlan:
                Intent intent = new Intent(requireActivity(), ToolBarActivity.class);
                intent.putExtra("Type", RECHARGEPLAN);
                intent.putExtra("title", RECHARGEPLAN);
                startActivity(intent);
                break;
            case R.id.three_hundred:
                edit_Bonus.setText(txt_three_hundred.getText().toString());
                break;
            case R.id.five_hundred:
                edit_Bonus.setText(txt_five_hundred.getText().toString());
                break;
            case R.id.one_thousand:
                edit_Bonus.setText(txt_one_thousand.getText().toString());
                break;
            case R.id.two_thousand:
                edit_Bonus.setText(txt_two_thousand.getText().toString());
                break;
            case R.id.Five_thousand:
                edit_Bonus.setText(txt_Five_thousand.getText().toString());
                break;
            case R.id.Statement:
                RechargeRecordFragment rechargeRecordFragment = new RechargeRecordFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title", "Statement");
                rechargeRecordFragment.setArguments(bundle);
                switchFragmentToBack(R.id.fragment_Introduction, rechargeRecordFragment, requireActivity());
                break;
            case R.id.btn_Bonus:
                if (WeChat.isChecked()) {
//                    try {//喚醒app

//                        IWXAPI api = WXAPIFactory.createWXAPI(requireContext(), Constants.APP_ID);
//                        api.registerApp(Constants.APP_ID);
//
//                        PayReq req = new PayReq();
//                        req.appId = Constants.APP_ID;// 也就是Values.WXPAYAPPID，如果二者不一致的話是沒法發起支付的。
//                        req.partnerId = json.getString("partnerId");
//                        req.prepayId = json.getString("prepayid");
//                        req.nonceStr = json.getString("noncestr");
//                        req.timeStamp = json.getString("timestamp");
//                        req.packageValue = "Sign=WXPay";
//                        req.sign = json.getString("sign");//de7cd6d4ffe79297c787a1806fcf671c
//
//                        boolean r = api.sendReq(req);
//                        if (!r) {
//                            Toast.makeText(requireContext(), "開啟微信支付失敗!", Toast.LENGTH_SHORT).show();
//                        }

                    intent = new Intent(requireActivity(), WXPayActivity.class);
//                        intent.setAction(Intent.ACTION_VIEW);
//                        intent.setData(Uri.parse("weixin://wap/pay?"));//微信
                    startActivity(intent);
//                    } catch (android.content.ActivityNotFoundException e) {
//                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://weixin.qq.com/"));//微信
//                        startActivity(intent);
//
//                    }
                } else if (Alipay.isChecked()) {
                    value=Float.parseFloat(edit_Bonus.getText().toString().replace("元", ""));
                    alipayRequestPresenter = new AlipayRequestPresenter(this, requireContext(), Float.parseFloat(edit_Bonus.getText().toString().replace("元", "")));
                    alipayRequestPresenter.doAlipayRequest();

                } else {
                    CreateAlertDialogTool(requireContext(), R.string.note, R.string.Please_Select_Mode_of_Payment);
                }
                break;
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler Handler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    Log.e("MainActivity", payResult + "");
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        storedValuePresenter = new StoredValuePresenter(AccountFragment.this, requireContext(),orderNo,value,"2");
                        storedValuePresenter.doStoredValue();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        CreateAlertDialogTool(requireContext(), "", R.string.PaymentFailed);
                    }
                    break;

                default:
                    break;
            }
        }
    };


    private void pay(final String orderInfo) {
        final Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(requireActivity());
                Map<String, String> result = alipay.payV2(orderInfo, true);//第二个参数设置为true，将会在调用pay接口的时候直接唤起一个loading
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                Handler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Override
    public void AlipayRequestSuccess(AlipayRequest alipayRequest) {
        mHandler.postDelayed(() -> {
            orderNo=alipayRequest.getOrderNo();
            pay(alipayRequest.getAliBody());
        }, 1);
    }

    @Override
    public void StoredValueSuccess() {
        mHandler.postDelayed(() -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle("");
            builder.setMessage(R.string.PaymentSuccessful);
            builder.setPositiveButton(R.string.confirm, (dialog, which) -> requireActivity().finish());
            AlertDialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
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
}
