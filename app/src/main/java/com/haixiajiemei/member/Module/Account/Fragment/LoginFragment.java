package com.haixiajiemei.member.Module.Account.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.haixiajiemei.member.Util.FunTools.CreateAlertDialogTool;
import static com.haixiajiemei.member.Util.FunTools.switchFragmentToActivity;
import static com.haixiajiemei.member.Util.FunTools.switchFragmentToBack;

import com.haixiajiemei.member.Module.Account.Contract.LoginContract;
import com.haixiajiemei.member.Module.Account.Present.LoginPresenter;
import com.haixiajiemei.member.Module.Setting.Fragment.SettingFragment;
import com.haixiajiemei.member.R;

public class LoginFragment extends Fragment implements LoginContract.ViewAction {
    @BindView(R.id.confirm)
    Button confirm;
    @BindView(R.id.edit_account)
    EditText edit_account;
    @BindView(R.id.edit_password)
    EditText edit_password;
    @BindView(R.id.txtRegister)
    TextView txtRegister;
    @BindView(R.id.txtForgetPassword)
    TextView txtForgetPassword;

    private LoginPresenter presenter;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        presenter = new LoginPresenter(this);

        confirm.setText(R.string.Sign_in);
        confirm.setTextColor(getResources().getColor(R.color.PureWhite));
        confirm.setTextSize(16);

        return view;
    }

    @OnClick({R.id.confirm, R.id.txtRegister, R.id.txtForgetPassword})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                presenter.doLogin(getContext(), edit_account.getText().toString(), edit_password.getText().toString());
                break;
            case R.id.txtRegister:
                RegisterFragment registerFragment = new RegisterFragment();
                switchFragmentToBack(R.id.fragment_container,registerFragment, requireActivity());
                break;
            case R.id.txtForgetPassword:
                ForgetFragment forgetFragment = new ForgetFragment();
                switchFragmentToBack(R.id.fragment_container,forgetFragment, requireActivity());
                break;
        }
    }

    @Override
    public void LoginSuccess() {
        mHandler.postDelayed(() -> {
            SettingFragment settingFragment = new SettingFragment();
            switchFragmentToActivity(R.id.fragment_container, settingFragment, requireActivity());
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
        mHandler.postDelayed(() -> {
            CreateAlertDialogTool(requireContext(), R.string.note, reason);
        }, 1);
    }
}
