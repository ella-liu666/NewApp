package com.haixiajiemei.member;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.haixiajiemei.member.Module.Home.Fragment.IntroductionFragment;
import com.haixiajiemei.member.Module.Setting.Fragment.AccountFragment;
import com.haixiajiemei.member.Module.Setting.Fragment.MembershipCardUpgradeFragment;
import com.haixiajiemei.member.Module.Setting.Fragment.QRCodeFragment;
import com.haixiajiemei.member.Module.Setting.Fragment.RechargePlanFragment;

import static com.haixiajiemei.member.Util.FunTools.switchFragmentToActivity;
import static com.haixiajiemei.member.Util.Proclaim.ACCOUNT;
import static com.haixiajiemei.member.Util.Proclaim.DISTRIBUTION;
import static com.haixiajiemei.member.Util.Proclaim.EXPENSESRECORD;
import static com.haixiajiemei.member.Util.Proclaim.INTRODUCTION;
import static com.haixiajiemei.member.Util.Proclaim.MEMBERSHIPCARDUPGRADE;
import static com.haixiajiemei.member.Util.Proclaim.MESSAGECENTER;
import static com.haixiajiemei.member.Util.Proclaim.MYAPPOINTMENT;
import static com.haixiajiemei.member.Util.Proclaim.MYORDER;
import static com.haixiajiemei.member.Util.Proclaim.MYPOST;
import static com.haixiajiemei.member.Util.Proclaim.QRCODE;
import static com.haixiajiemei.member.Util.Proclaim.RECHARGEPLAN;
import static com.haixiajiemei.member.Util.Proclaim.RECHARGERECORD;

public class ToolBarActivity extends AppCompatActivity {
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);
        ButterKnife.bind(this);
        intent = getIntent();

        init();

    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment.getArguments() != null && fragment.getArguments().getString("title") != null) {
            if (fragment.getArguments().getString("title").equals("Statement")) {
                toolbar_title.setTextColor(getResources().getColor(R.color.PureWhite));
                toolbar_title.setText(R.string.title_RechargeDetails);
                toolbar_title.setTextSize(18);
            }
        }
    }

    private void init() {
        switch (intent.getExtras().getString("Type")) {
            case INTRODUCTION:
                IntroductionFragment introductionFragment = new IntroductionFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", intent.getExtras().getInt("id"));
                toolbar_title.setText(getString(R.string.brand_introduction, intent.getExtras().getString("title")));
                introductionFragment.setArguments(bundle);
                switchFragmentToActivity(R.id.fragment_Introduction, introductionFragment, this);
                break;
            case ACCOUNT:
                toolbar.setBackgroundColor(getResources().getColor(R.color.azure));
                toolbar_title.setTextColor(getResources().getColor(R.color.PureWhite));
                toolbar_title.setText(R.string.MyAccount);
                toolbar_title.setTextSize(18);
                Drawable drawable = getResources().getDrawable(R.drawable.baseline_keyboard_arrow_left_white_18dp);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                toolbar_title.setCompoundDrawables(drawable, null, null, null);
                AccountFragment accountFragment = new AccountFragment();
                bundle = new Bundle();
                bundle.putFloat("Balance", intent.getExtras().getFloat("Balance"));
                accountFragment.setArguments(bundle);
                switchFragmentToActivity(R.id.fragment_Introduction, accountFragment, this);
                break;
            case RECHARGEPLAN:
                RechargePlanFragment rechargePlanFragment = new RechargePlanFragment();
                bundle = new Bundle();
                bundle.putString("title", intent.getExtras().getString("title"));
                rechargePlanFragment.setArguments(bundle);
                switchFragmentToActivity(R.id.fragment_Introduction, rechargePlanFragment, this);
                break;
            case MESSAGECENTER:
                toolbar_title.setText(intent.getExtras().getString("title"));
                toolbar_title.setTextSize(18);
                break;
            case MYAPPOINTMENT:
                toolbar_title.setText(intent.getExtras().getString("title"));
                toolbar_title.setTextSize(18);
                break;
            case MYPOST:
                toolbar_title.setText(intent.getExtras().getString("title"));
                toolbar_title.setTextSize(18);
                break;
            case MYORDER:
                toolbar_title.setText(intent.getExtras().getString("title"));
                toolbar_title.setTextSize(18);
                break;
            case EXPENSESRECORD:
                toolbar_title.setText(intent.getExtras().getString("title"));
                toolbar_title.setTextSize(18);
                break;
            case DISTRIBUTION:
                toolbar_title.setText(intent.getExtras().getString("title"));
                toolbar_title.setTextSize(18);
                break;
            case MEMBERSHIPCARDUPGRADE:
                toolbar_title.setText(intent.getExtras().getString("title"));
                toolbar_title.setTextSize(18);
                MembershipCardUpgradeFragment membershipCardUpgradeFragment=new MembershipCardUpgradeFragment();
                bundle = new Bundle();
                bundle.putString("title", intent.getExtras().getString("title"));
                membershipCardUpgradeFragment.setArguments(bundle);
                switchFragmentToActivity(R.id.fragment_Introduction, membershipCardUpgradeFragment, this);
                break;
            case QRCODE:
                toolbar.setBackgroundColor(getResources().getColor(R.color.original_bg));
                toolbar_title.setText(R.string.Payment_QR_code);
                toolbar_title.setTextSize(18);
                QRCodeFragment qrCodeFragment = new QRCodeFragment();
                bundle = new Bundle();
                bundle.putFloat("Balance", intent.getExtras().getFloat("Balance"));
                if (intent.getExtras().getString("Balance") != null) {
                    bundle.putString("Balance", intent.getExtras().getString("Balance"));
                }
                qrCodeFragment.setArguments(bundle);
                switchFragmentToActivity(R.id.fragment_Introduction, qrCodeFragment, this);
                break;
        }
    }

    @OnClick(R.id.toolbar_title)
    public void onClick() {
        finish();
    }
}
