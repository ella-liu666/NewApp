package com.haixiajiemei.app.Module.Setting.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.haixiajiemei.app.Helper.GlideApp;
import com.haixiajiemei.app.Module.Setting.Adapter.SettingItemAdapter;
import com.haixiajiemei.app.Module.Setting.Contract.SettingContract;
import com.haixiajiemei.app.Module.Setting.Contract.SettingItemCallback;
import com.haixiajiemei.app.Module.Setting.Model.MemberInfo;
import com.haixiajiemei.app.Module.Setting.Present.SettingPresenter;
import com.haixiajiemei.app.R;
import com.haixiajiemei.app.ToolBarActivity;

import static android.content.Context.MODE_PRIVATE;
import static com.haixiajiemei.app.Util.FunTools.CreateAlertDialogTool;
import static com.haixiajiemei.app.Util.Proclaim.ACCOUNT;
import static com.haixiajiemei.app.Util.Proclaim.COUPON;
import static com.haixiajiemei.app.Util.Proclaim.EXPENSESRECORD;
import static com.haixiajiemei.app.Util.Proclaim.MEMBERSHIPCARDUPGRADE;
import static com.haixiajiemei.app.Util.Proclaim.MESSAGECENTER;
import static com.haixiajiemei.app.Util.Proclaim.MYORDER;
import static com.haixiajiemei.app.Util.Proclaim.QRCODE;
import static com.haixiajiemei.app.Util.Proclaim.RECHARGEPLAN;
import static com.haixiajiemei.app.Util.Proclaim.PROFILE;
import static com.haixiajiemei.app.WelcomeActivity.img;
import static com.haixiajiemei.app.WelcomeActivity.name;
import static com.haixiajiemei.app.WelcomeActivity.img2;
import static com.haixiajiemei.app.WelcomeActivity.name2;

public class SettingFragment extends Fragment implements SettingItemCallback, SettingContract.ViewAction {
    @BindView(R.id.txt_accountType)
    TextView txt_accountType;
    @BindView(R.id.txt_Name)
    TextView txt_Name;
    @BindView(R.id.txt_dollar)
    TextView txt_dollar;
    @BindView(R.id.txt_CurrentPoints)
    TextView txt_CurrentPoints;
    @BindView(R.id.txt_integral)
    TextView txt_integral;
    @BindView(R.id.txt_coupon)
    TextView txt_coupon;
    @BindView(R.id.txt_MembershipCard)
    TextView txt_MembershipCard;
    @BindView(R.id.setting_item)
    RecyclerView setting_item;
    @BindView(R.id.membership_item)
    RecyclerView membership_item;
    @BindView(R.id.user_Avatar)
    ImageView user_Avatar;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private SettingItemAdapter settingItemAdapter;
    private SettingPresenter presenter;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private float Balance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter = new SettingPresenter(this, requireContext());
        presenter.doSettingMemberInfo();

        GlideApp.with(requireContext())
                .load(R.drawable.avatar)
                .fitCenter()
                .into(user_Avatar);

        setting_item_init();
    }

    private void setting_item_init() {
        settingItemAdapter = new SettingItemAdapter(requireContext(), name, img, SettingFragment.this);
        setting_item.setLayoutManager(new LinearLayoutManager(requireContext()));
        setting_item.setAdapter(settingItemAdapter);

        settingItemAdapter = new SettingItemAdapter(requireContext(), name2, img2, SettingFragment.this);
        membership_item.setLayoutManager(new LinearLayoutManager(requireContext()));
        membership_item.setAdapter(settingItemAdapter);
    }

    @OnClick({R.id.Balance, R.id.Coupon, R.id.MembershipCard, R.id.user_info, R.id.qrcode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Balance:
                Intent intent = new Intent(requireActivity(), ToolBarActivity.class);
                intent.putExtra("Type", ACCOUNT);
                intent.putExtra("Balance", Balance);
                startActivity(intent);
                break;
            case R.id.Coupon:
                intent = new Intent(requireActivity(), ToolBarActivity.class);
                intent.putExtra("Type", COUPON);
                intent.putExtra("title", getString(R.string.Coupon));
                startActivity(intent);
                break;
            case R.id.MembershipCard:
                intent = new Intent(requireActivity(), ToolBarActivity.class);
                intent.putExtra("Type", MEMBERSHIPCARDUPGRADE);
                intent.putExtra("title", getString(R.string.MembershipCard));
                startActivity(intent);
                break;
            case R.id.user_info:
                intent = new Intent(requireActivity(), ToolBarActivity.class);
                intent.putExtra("Type", PROFILE);
                intent.putExtra("title", getString(R.string.personal));
                startActivity(intent);
                break;
            case R.id.qrcode:
                intent = new Intent(requireActivity(), ToolBarActivity.class);
                intent.putExtra("Type", QRCODE);
                intent.putExtra("title", R.string.Payment_QR_code);
                intent.putExtra("Balance", Balance);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onSettingItemClicked(int position, String title) {
        switch (position) {
            case 0:
                if (title.equals(getString(R.string.Terms))) {
                    Intent intent = new Intent(requireActivity(), ToolBarActivity.class);
                    intent.putExtra("Type", RECHARGEPLAN);
                    intent.putExtra("title", title);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(requireActivity(), ToolBarActivity.class);
                    intent.putExtra("Type", MESSAGECENTER);
                    intent.putExtra("title", title);
                    startActivity(intent);

                }
                break;
            case 1:
                if (title.equals(getString(R.string.Privacy))) {
                    Intent intent = new Intent(requireActivity(), ToolBarActivity.class);
                    intent.putExtra("Type", RECHARGEPLAN);
                    intent.putExtra("title", title);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(requireActivity(), ToolBarActivity.class);
                    intent.putExtra("Type", MYORDER);
                    intent.putExtra("title", title);
                    startActivity(intent);

                }
                break;
            case 2:
                Intent intent = new Intent(requireActivity(), ToolBarActivity.class);
                intent.putExtra("Type", EXPENSESRECORD);
                intent.putExtra("title", title);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(requireActivity(), ToolBarActivity.class);
                intent.putExtra("Type", MEMBERSHIPCARDUPGRADE);
                intent.putExtra("title", title);
                startActivity(intent);
                break;

//            case 0:
//                if (title.equals(getString(R.string.Terms))) {
//                    Intent intent = new Intent(getActivity(), ToolBarActivity.class);
//                    intent.putExtra("Type", RECHARGEPLAN);
//                    intent.putExtra("title", title);
//                    startActivity(intent);
//                } else {
//                    Intent intent = new Intent(getActivity(), ToolBarActivity.class);
//                    intent.putExtra("Type", MESSAGECENTER);
//                    intent.putExtra("title", title);
//                    startActivity(intent);
//
//                }
//                break;
//            case 1:
//                if (title.equals(getString(R.string.Privacy))) {
//                    Intent intent = new Intent(getActivity(), ToolBarActivity.class);
//                    intent.putExtra("Type", RECHARGEPLAN);
//                    intent.putExtra("title", title);
//                    startActivity(intent);
//                } else {
//                    Intent intent = new Intent(getActivity(), ToolBarActivity.class);
//                    intent.putExtra("Type", MYAPPOINTMENT);
//                    intent.putExtra("title", title);
//                    startActivity(intent);
//
//                }
//                break;
//            case 2:
//                Intent intent = new Intent(getActivity(), ToolBarActivity.class);
//                intent.putExtra("Type", MYPOST);
//                intent.putExtra("title", title);
//                startActivity(intent);
//                break;
//            case 3:
//                intent = new Intent(getActivity(), ToolBarActivity.class);
//                intent.putExtra("Type", MYORDER);
//                intent.putExtra("title", title);
//                startActivity(intent);
//                break;
//            case 4:
//                intent = new Intent(getActivity(), ToolBarActivity.class);
//                intent.putExtra("Type", EXPENSESRECORD);
//                intent.putExtra("title", title);
//                startActivity(intent);
//                break;
//            case 5:
//                intent = new Intent(getActivity(), ToolBarActivity.class);
//                intent.putExtra("Type", DISTRIBUTION);
//                intent.putExtra("title", title);
//                startActivity(intent);
//                break;
//            case 6:
//                intent = new Intent(getActivity(), ToolBarActivity.class);
//                intent.putExtra("Type", MEMBERSHIPCARDUPGRADE);
//                intent.putExtra("title", title);
//                startActivity(intent);
//                break;

        }
    }

    @SuppressLint("StringFormatMatches")
    @Override
    public void SettingMemberInfoSuccess(MemberInfo MemberInfo) {
        mHandler.postDelayed(() -> {
            Balance = MemberInfo.getPointTotal();
            txt_accountType.setText(MemberInfo.getAccountType());
            txt_Name.setText(MemberInfo.getName());
            txt_dollar.setText(getString(R.string.dollar, MemberInfo.getPointTotal()));
            txt_CurrentPoints.setText(getString(R.string.Currently_accumulated, MemberInfo.getExperience()));
            txt_integral.setText(getString(R.string.fraction, MemberInfo.getExperience()));
            txt_coupon.setText(getString(R.string.leaf, MemberInfo.getCouponNum()));
            txt_MembershipCard.setText(getString(R.string.leaf, MemberInfo.getCardNum()));
        }, 1);
    }

    @Override
    public void showProgress() {
        mHandler.postDelayed(() -> {
            requireActivity().runOnUiThread(() -> {
                progressBar.setVisibility(View.VISIBLE);
                requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            });
        }, 1);
    }

    @Override
    public void hideProgress() {
        mHandler.postDelayed(() -> {
            requireActivity().runOnUiThread(() -> {
                progressBar.setVisibility(View.GONE);
                requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            });
        }, 1);
    }

    @Override
    public void errorOccurred(String reason) {

    }

    @Override
    public void ApierrorOccurred(String Access_token) {
        mHandler.postDelayed(() -> {
            SharedPreferences pref = requireContext().getSharedPreferences("UserToken", MODE_PRIVATE);
            pref.edit()
                    .putString("access_token", Access_token)
                    .commit();

            presenter = new SettingPresenter(this, requireContext());
            presenter.doSettingMemberInfo();

            GlideApp.with(requireContext())
                    .load(R.drawable.avatar)
                    .fitCenter()
                    .into(user_Avatar);

            setting_item_init();
        }, 1);
    }
}
