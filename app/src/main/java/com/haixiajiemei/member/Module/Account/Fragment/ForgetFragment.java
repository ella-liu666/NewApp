package com.haixiajiemei.member.Module.Account.Fragment;

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

import com.haixiajiemei.member.Module.Account.Contract.ForgetContract;
import com.haixiajiemei.member.Module.Account.Present.ForgetPresenter;
import com.haixiajiemei.member.R;

import static com.haixiajiemei.member.Util.FunTools.CreateAlertDialogTool;
import static com.haixiajiemei.member.Util.FunTools.FragmentKEYCODE_BACK;
import static com.haixiajiemei.member.Util.FunTools.switchFragmentToActivity;

public class ForgetFragment extends Fragment implements ForgetContract.ViewAction {
    @BindView(R.id.confirm)
    Button confirm;
    @BindView(R.id.edit_account)
    EditText edit_account;
    @BindView(R.id.edit_phone)
    EditText edit_phone;
    @BindView(R.id.edit_password)
    EditText edit_password;
    @BindView(R.id.edit_second_password)
    EditText edit_second_password;

    private ForgetPresenter presenter;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget, container, false);
        ButterKnife.bind(this, view);

        presenter = new ForgetPresenter(this);

        confirm.setText(R.string.Send);
        confirm.setTextColor(getResources().getColor(R.color.PureWhite));
        confirm.setTextSize(16);

        return view;
    }

    @OnClick(R.id.confirm)
    public void onClick(View view) {
        if (!edit_password.getText().toString().equals(edit_second_password.getText().toString())) {
            CreateAlertDialogTool(requireContext(), R.string.note, R.string.Passwords_are_inconsistent);
        }
        if ("".equals(edit_account.getText().toString()) | "".equals(edit_password.getText().toString()) |
                "".equals(edit_phone.getText().toString()) | "".equals(edit_second_password.getText().toString())) {
            CreateAlertDialogTool(requireContext(), R.string.note, R.string.not_complete);
        } else {
            presenter.doForget(requireContext(), edit_account.getText().toString(), edit_phone.getText().toString()
                    , edit_password.getText().toString());
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        FragmentKEYCODE_BACK(requireContext(), this, requireActivity(), R.string.note, R.string.Exit);
    }

    @Override
    public void ForgetSuccess() {
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
        CreateAlertDialogTool(requireContext(), R.string.note, reason);
    }
}
