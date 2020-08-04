package com.haixiajiemei.app.Module.Home.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.haixiajiemei.app.Helper.GlideApp;
import com.haixiajiemei.app.Module.Home.Contract.BrandIntroductionImgContract;
import com.haixiajiemei.app.Module.Home.Model.ImgAndTxt;
import com.haixiajiemei.app.Module.Home.Present.BrandIntroductionImgPresenter;
import com.haixiajiemei.app.R;

import java.util.List;

public class IntroductionFragment extends Fragment implements BrandIntroductionImgContract.ViewAction {

    @BindView(R.id.img_infor)
    ImageView img_infor;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private BrandIntroductionImgPresenter presenter;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_introduction, container, false);
        ButterKnife.bind(this, view);

        presenter=new BrandIntroductionImgPresenter(this,requireContext(),getArguments().getInt("id"));
        presenter.doBrandIntroductionImg();
        return view;
    }

    @Override
    public void BrandIntroductionImgContractSuccess(ImgAndTxt imgAndTxt) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                GlideApp.with(requireContext())
                        .load(imgAndTxt.getImg().toString())
                        .fitCenter()
                        .into(img_infor);
            }
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
            presenter=new BrandIntroductionImgPresenter(this,requireContext(),getArguments().getInt("id"));
            presenter.doBrandIntroductionImg();
        }, 1);
    }
}
