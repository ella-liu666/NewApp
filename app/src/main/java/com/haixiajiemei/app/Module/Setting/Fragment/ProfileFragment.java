package com.haixiajiemei.app.Module.Setting.Fragment;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haixiajiemei.app.MainActivity;
import com.haixiajiemei.app.Module.Setting.Contract.ProfileContract;
import com.haixiajiemei.app.Module.Setting.Contract.SignOutContract;
import com.haixiajiemei.app.Module.Setting.Model.Profile;
import com.haixiajiemei.app.Module.Setting.Present.ProfilePresenter;
import com.haixiajiemei.app.Module.Setting.Present.SignOutPresenter;
import com.haixiajiemei.app.R;

import static com.haixiajiemei.app.Util.FunTools.switchFragmentToBack;

public class ProfileFragment extends Fragment implements ProfileContract.ViewAction, SignOutContract.ViewAction {
    private ProfilePresenter presenter;
    private SignOutPresenter signOutPresenter;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    @BindView(R.id.Nickname)
    LinearLayout Nickname;
    @BindView(R.id.pass)
    LinearLayout pass;
    @BindView(R.id.userName_content)
    TextView userName_content;
    @BindView(R.id.name_content)
    TextView name_content;
    @BindView(R.id.telephone_content)
    TextView telephone_content;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter = new ProfilePresenter(this, requireContext());
        presenter.doProfile();
    }

    @OnClick({R.id.Nickname, R.id.pass,R.id.sign_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Nickname:
                EditNicknameFragment editNicknameFragment = new EditNicknameFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", name_content.getText().toString());
                editNicknameFragment.setArguments(bundle);
                switchFragmentToBack(R.id.fragment_Introduction, editNicknameFragment, requireActivity());
                break;
            case R.id.pass:
                EditPasswordFragment editPasswordFragment=new EditPasswordFragment();
                switchFragmentToBack(R.id.fragment_Introduction, editPasswordFragment, requireActivity());
                break;
            case R.id.sign_out:
                signOutPresenter=new SignOutPresenter(this,requireContext());
                signOutPresenter.doSignOut();
                break;
        }
    }

    @Override
    public void ProfileSuccess(Profile Profile) {
        mHandler.postDelayed(() -> {
            userName_content.setText(Profile.getUserName());
            name_content.setText(Profile.getName());
            telephone_content.setText(Profile.getTelephone());
        }, 1);
    }

    @Override
    public void SignOutSuccess() {
        mHandler.postDelayed(() -> {
            Intent intent = new Intent(requireActivity(), MainActivity.class);
            startActivity(intent);
            requireActivity().finish();
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
