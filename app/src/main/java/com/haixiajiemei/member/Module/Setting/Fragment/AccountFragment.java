package com.haixiajiemei.member.Module.Setting.Fragment;

import android.content.Intent;
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

import com.haixiajiemei.member.Module.Setting.Contract.RechargeContract;
import com.haixiajiemei.member.Module.Setting.Model.Recharge;
import com.haixiajiemei.member.Module.Setting.Presenter.RechargePresenter;
import com.haixiajiemei.member.R;
import com.haixiajiemei.member.ToolBarActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.haixiajiemei.member.Util.FunTools.switchFragmentToBack;
import static com.haixiajiemei.member.Util.Proclaim.RECHARGEPLAN;

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

        return view;
    }

    @OnClick({R.id.RechargePlan,R.id.three_hundred,R.id.five_hundred,R.id.one_thousand,R.id.two_thousand,R.id.Five_thousand,R.id.Statement,R.id.btn_Bonus})
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
                RechargeRecordFragment rechargeRecordFragment=new RechargeRecordFragment();
                switchFragmentToBack(R.id.fragment_Introduction,rechargeRecordFragment, requireActivity());
                break;
            case R.id.btn_Bonus:

                break;
        }
    }
}
