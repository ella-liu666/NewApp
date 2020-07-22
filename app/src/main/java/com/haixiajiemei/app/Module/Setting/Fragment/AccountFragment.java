package com.haixiajiemei.app.Module.Setting.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.haixiajiemei.app.R;
import com.haixiajiemei.app.ToolBarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.haixiajiemei.app.Util.FunTools.CreateAlertDialogTool;
import static com.haixiajiemei.app.Util.FunTools.switchFragmentToBack;
import static com.haixiajiemei.app.Util.Proclaim.RECHARGEPLAN;

public class AccountFragment extends Fragment {

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
                Intent intent = new Intent(getActivity(), ToolBarActivity.class);
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
                    try {//喚醒app
                        intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("weixin://wap/pay?"));//微信
                        startActivity(intent);
                    } catch (android.content.ActivityNotFoundException e) {
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://weixin.qq.com/"));//微信
                        startActivity(intent);

                    }
                } else if (Alipay.isChecked()) {
                    try {//喚醒app
                        intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("alipays://platformapi/startApp"));//支付寶
                        startActivity(intent);
                    } catch (android.content.ActivityNotFoundException e) {
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cshall.alipay.com/lab/help_detail.htm?help_id=255346"));//支付寶
                        startActivity(intent);

                    }
                } else {
                    CreateAlertDialogTool(requireContext(), R.string.note, R.string.Please_Select_Mode_of_Payment);
                }
                break;
        }
    }
}
