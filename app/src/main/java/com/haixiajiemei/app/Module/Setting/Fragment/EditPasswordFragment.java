package com.haixiajiemei.app.Module.Setting.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.haixiajiemei.app.Module.Setting.Contract.ProfilePwdContract;
import com.haixiajiemei.app.Module.Setting.Present.ProfilePwdPresenter;
import com.haixiajiemei.app.R;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.haixiajiemei.app.Util.FunTools.CreateAlertDialogTool;

public class EditPasswordFragment extends Fragment implements ProfilePwdContract.ViewAction {
    private ProfilePwdPresenter profilePwdPresenter;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @BindView(R.id.edit_old_password)
    EditText edit_old_password;
    @BindView(R.id.edit_password)
    EditText edit_password;
    @BindView(R.id.edit_second_password)
    EditText edit_second_password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_password, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.send)
    public void onClick(View view) {
        if ("".equals(edit_old_password.getText().toString()) |
                "".equals(edit_password.getText().toString()) | "".equals(edit_second_password.getText().toString())) {
            CreateAlertDialogTool(requireContext(), R.string.note, R.string.not_complete);
        } else if (!edit_password.getText().toString().equals(edit_second_password.getText().toString())) {
            CreateAlertDialogTool(requireContext(), R.string.note, R.string.Passwords_are_inconsistent);
        }else {
            profilePwdPresenter=new ProfilePwdPresenter(this,requireContext(),edit_old_password.getText().toString(),edit_password.getText().toString());
            profilePwdPresenter.doProfilePwd();
        }
    }

    @Override
    public void ProfilePwdSuccess() {
        mHandler.postDelayed(() -> {
            requireActivity().onBackPressed();
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
        mHandler.postDelayed(() -> {
            if ("".equals(edit_old_password.getText().toString()) |
                    "".equals(edit_password.getText().toString()) | "".equals(edit_second_password.getText().toString())) {
                CreateAlertDialogTool(requireContext(), R.string.note, R.string.not_complete);
            } else if (!edit_password.getText().toString().equals(edit_second_password.getText().toString())) {
                CreateAlertDialogTool(requireContext(), R.string.note, R.string.Passwords_are_inconsistent);
            }else {
                profilePwdPresenter=new ProfilePwdPresenter(this,requireContext(),edit_old_password.getText().toString(),edit_password.getText().toString());
                profilePwdPresenter.doProfilePwd();
            }
        }, 1);
    }
}
