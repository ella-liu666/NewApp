package com.haixiajiemei.member.Module.Account.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.haixiajiemei.member.Module.Account.Contract.RegisterContract;
import com.haixiajiemei.member.Module.Account.Present.RegisterPresenter;
import com.haixiajiemei.member.R;
import com.haixiajiemei.member.ToolBarActivity;

import java.util.regex.Pattern;

import static com.haixiajiemei.member.Util.FunTools.CreateAlertDialogTool;
import static com.haixiajiemei.member.Util.FunTools.FragmentKEYCODE_BACK;
import static com.haixiajiemei.member.Util.FunTools.switchFragmentToActivity;
import static com.haixiajiemei.member.Util.Proclaim.RECHARGEPLAN;

public class RegisterFragment extends Fragment implements RegisterContract.ViewAction {
    @BindView(R.id.confirm)
    Button confirm;
    @BindView(R.id.edit_account)
    EditText edit_account;
    @BindView(R.id.edit_password)
    EditText edit_password;
    @BindView(R.id.edit_name)
    EditText edit_name;
    @BindView(R.id.edit_phone)
    EditText edit_phone;
    @BindView(R.id.Terms)
    RadioButton Terms;
    @BindView(R.id.registered_Terms)
    TextView registered_Terms;
    @BindView(R.id.registered_Privacy)
    TextView registered_Privacy;

    private RegisterPresenter presenter;
    private boolean Check = false;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);

        presenter = new RegisterPresenter(this);

        confirm.setText(R.string.registered);
        confirm.setTextColor(getResources().getColor(R.color.PureWhite));
        confirm.setTextSize(16);

        return view;
    }

    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    @OnClick({R.id.confirm, R.id.Terms, R.id.registered_Terms, R.id.registered_Privacy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                if (Terms.isChecked()) {
                    if (isEmail(edit_account.getText().toString())) {
                        if ("".equals(edit_account.getText().toString()) | "".equals(edit_password.getText().toString()) |
                                "".equals(edit_name.getText().toString()) | "".equals(edit_phone.getText().toString())) {
                            CreateAlertDialogTool(requireContext(), R.string.note, R.string.not_complete);
                        } else {
                            presenter.doSignUp(requireContext(), edit_account.getText().toString(), edit_password.getText().toString()
                                    , edit_name.getText().toString(), edit_phone.getText().toString());
                        }
                    } else {
                        CreateAlertDialogTool(requireContext(), R.string.note, R.string.wrong_format);
                    }
                } else {
                    CreateAlertDialogTool(requireContext(), R.string.note, R.string.Unchecked);
                }
                break;
            case R.id.Terms:
                if (Check) {
                    if (Terms.isChecked()) {
                        Terms.setChecked(false);
                        Check = false;
                    }
                } else {
                    Check = true;
                }
                break;
            case R.id.registered_Terms:
                Intent intent = new Intent(getActivity(), ToolBarActivity.class);
                intent.putExtra("Type", RECHARGEPLAN);
                intent.putExtra("title", getString(R.string.Terms));
                startActivity(intent);
                break;
            case R.id.registered_Privacy:
                intent = new Intent(getActivity(), ToolBarActivity.class);
                intent.putExtra("Type", RECHARGEPLAN);
                intent.putExtra("title", getString(R.string.Privacy));
                startActivity(intent);
                break;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        FragmentKEYCODE_BACK(requireContext(), this, requireActivity(), R.string.note, R.string.Exit);
    }

    @Override
    public void RegisterSuccess() {
        mHandler.postDelayed(() -> {
            LoginFragment loginFragment = new LoginFragment();
            switchFragmentToActivity(R.id.fragment_container, loginFragment, requireActivity());
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
