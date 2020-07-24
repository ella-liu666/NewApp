package com.haixiajiemei.app.Module.Setting.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.haixiajiemei.app.Module.Setting.Contract.ProfileNameContract;
import com.haixiajiemei.app.Module.Setting.Present.ProfileNamePresenter;
import com.haixiajiemei.app.R;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditNicknameFragment extends Fragment implements ProfileNameContract.ViewAction{
    private ProfileNamePresenter profileNamePresenter;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @BindView(R.id.edit_Nickname)
    EditText edit_Nickname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_nickname, container, false);
        ButterKnife.bind(this, view);
        edit_Nickname.setText(getArguments().getString("name"));
        return view;
    }

    @OnClick(R.id.Send)
    public void onClick(View view) {
        profileNamePresenter = new ProfileNamePresenter(this, requireContext(), edit_Nickname.getText().toString());
        profileNamePresenter.doProfileName();
    }

    @Override
    public void ProfileNameSuccess() {
        mHandler.postDelayed(() -> {
            getActivity().onBackPressed();
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
