package com.haixiajiemei.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.haixiajiemei.app.Module.Home.Fragment.ECProductListFragment;
import com.haixiajiemei.app.Module.Home.Fragment.IntroductionFragment;
import com.haixiajiemei.app.Module.Order.Fragment.AddAddressFragment;
import com.haixiajiemei.app.Module.Order.Fragment.AddressFragment;
import com.haixiajiemei.app.Module.Order.Fragment.PaymentScreenFragment;
import com.haixiajiemei.app.Module.Setting.Fragment.AccountFragment;
import com.haixiajiemei.app.Module.Setting.Fragment.CardDetailsFragment;
import com.haixiajiemei.app.Module.Setting.Fragment.CouponFragment;
import com.haixiajiemei.app.Module.Setting.Fragment.ExpensesRecordFragment;
import com.haixiajiemei.app.Module.Setting.Fragment.MembershipCardUpgradeFragment;
import com.haixiajiemei.app.Module.Setting.Fragment.MessageCenterFragment;
import com.haixiajiemei.app.Module.Setting.Fragment.MyOrderFragment;
import com.haixiajiemei.app.Module.Setting.Fragment.ProfileFragment;
import com.haixiajiemei.app.Module.Setting.Fragment.QRCodeFragment;
import com.haixiajiemei.app.Module.Setting.Fragment.RechargePlanFragment;

import static com.haixiajiemei.app.Util.FunTools.switchFragmentToActivity;
import static com.haixiajiemei.app.Util.Proclaim.ACCOUNT;
import static com.haixiajiemei.app.Util.Proclaim.ADDADDRESS;
import static com.haixiajiemei.app.Util.Proclaim.ADDRESS;
import static com.haixiajiemei.app.Util.Proclaim.CARDDETAILS;
import static com.haixiajiemei.app.Util.Proclaim.COUPON;
import static com.haixiajiemei.app.Util.Proclaim.DISTRIBUTION;
import static com.haixiajiemei.app.Util.Proclaim.ECPRODUCTLIST;
import static com.haixiajiemei.app.Util.Proclaim.EXPENSESRECORD;
import static com.haixiajiemei.app.Util.Proclaim.INTRODUCTION;
import static com.haixiajiemei.app.Util.Proclaim.MEMBERSHIPCARDUPGRADE;
import static com.haixiajiemei.app.Util.Proclaim.MESSAGECENTER;
import static com.haixiajiemei.app.Util.Proclaim.MYAPPOINTMENT;
import static com.haixiajiemei.app.Util.Proclaim.MYORDER;
import static com.haixiajiemei.app.Util.Proclaim.MYPOST;
import static com.haixiajiemei.app.Util.Proclaim.PAYMENTSCREEN;
import static com.haixiajiemei.app.Util.Proclaim.PROFILE;
import static com.haixiajiemei.app.Util.Proclaim.QRCODE;
import static com.haixiajiemei.app.Util.Proclaim.RECHARGEPLAN;

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
                if (intent.getExtras().getString("Balance") != null) {
                    bundle.putString("Balance", intent.getExtras().getString("Balance"));
                } else {
                    bundle.putFloat("Balance", intent.getExtras().getFloat("Balance"));
                }
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
                MessageCenterFragment messageCenterFragment = new MessageCenterFragment();
                switchFragmentToActivity(R.id.fragment_Introduction, messageCenterFragment, this);
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
                MyOrderFragment myOrderFragment = new MyOrderFragment();
                switchFragmentToActivity(R.id.fragment_Introduction, myOrderFragment, this);
                break;
            case EXPENSESRECORD:
                toolbar_title.setText(intent.getExtras().getString("title"));
                toolbar_title.setTextSize(18);
                ExpensesRecordFragment expensesRecordFragment = new ExpensesRecordFragment();
                switchFragmentToActivity(R.id.fragment_Introduction, expensesRecordFragment, this);
                break;
            case DISTRIBUTION:
                toolbar_title.setText(intent.getExtras().getString("title"));
                toolbar_title.setTextSize(18);
                break;
            case MEMBERSHIPCARDUPGRADE:
                toolbar_title.setText(intent.getExtras().getString("title"));
                toolbar_title.setTextSize(18);
                MembershipCardUpgradeFragment membershipCardUpgradeFragment = new MembershipCardUpgradeFragment();
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
            case PROFILE:
                toolbar_title.setText(intent.getExtras().getString("title"));
                toolbar_title.setTextSize(18);
                ProfileFragment profileFragment = new ProfileFragment();
                switchFragmentToActivity(R.id.fragment_Introduction, profileFragment, this);
                break;
            case COUPON:
                toolbar_title.setText(intent.getExtras().getString("title"));
                toolbar_title.setTextSize(18);
                CouponFragment couponFragment = new CouponFragment();
                switchFragmentToActivity(R.id.fragment_Introduction, couponFragment, this);
                break;
            case CARDDETAILS:
                CardDetailsFragment cardDetailsFragment = new CardDetailsFragment();
                bundle = new Bundle();
                bundle.putString("cardID", intent.getExtras().getString("cardID"));
                bundle.putString("Tag", intent.getExtras().getString("Tag"));
                cardDetailsFragment.setArguments(bundle);
                switchFragmentToActivity(R.id.fragment_Introduction, cardDetailsFragment, this);
                break;
            case PAYMENTSCREEN:
                toolbar_title.setText(intent.getExtras().getString("title"));
                toolbar_title.setTextSize(18);
                PaymentScreenFragment paymentScreenFragment = new PaymentScreenFragment();

                if (intent.getExtras().getString("Address") != null && intent.getExtras().getString("PhoneName") != null) {
                    bundle = new Bundle();
                    bundle.putString("Address", intent.getExtras().getString("Address"));
                    bundle.putString("PhoneName", intent.getExtras().getString("PhoneName"));
                    bundle.putInt("deliveryID", intent.getExtras().getInt("deliveryID"));
                    paymentScreenFragment.setArguments(bundle);
                }
                switchFragmentToActivity(R.id.fragment_Introduction, paymentScreenFragment, this);
                break;
            case ADDRESS:
                toolbar_title.setText(intent.getExtras().getString("title"));
                toolbar_title.setTextSize(18);
                AddressFragment addressFragment = new AddressFragment();
                switchFragmentToActivity(R.id.fragment_Introduction, addressFragment, this);
                break;
            case ADDADDRESS:
                toolbar_title.setText(intent.getExtras().getString("title"));
                toolbar_title.setTextSize(18);
                AddAddressFragment addAddressFragment = new AddAddressFragment();
                switchFragmentToActivity(R.id.fragment_Introduction, addAddressFragment, this);
                break;
            case ECPRODUCTLIST:
                toolbar_title.setText(intent.getExtras().getString("title"));
                toolbar_title.setTextSize(18);
                ECProductListFragment ecProductListFragment=new ECProductListFragment();
                bundle = new Bundle();
                bundle.putInt("key", intent.getExtras().getInt("key"));
                ecProductListFragment.setArguments(bundle);
                switchFragmentToActivity(R.id.fragment_Introduction, ecProductListFragment, this);
                break;

        }
    }

    @OnClick(R.id.toolbar_title)
    public void onClick() {
        onBackPressed();
    }

}
