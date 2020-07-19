package com.haixiajiemei.member.Module.Setting.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.haixiajiemei.member.Helper.GlideApp;
import com.haixiajiemei.member.Module.Home.Model.ImgAndTxt;
import com.haixiajiemei.member.Module.Setting.Contract.MonthCardContract;
import com.haixiajiemei.member.Module.Setting.Contract.VIPCardContract;
import com.haixiajiemei.member.Module.Setting.Presenter.MonthCardPresenter;
import com.haixiajiemei.member.Module.Setting.Presenter.VIPCardPresenter;
import com.haixiajiemei.member.R;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import java.util.List;

public class BuyMembershipCardFragment extends Fragment implements VIPCardContract.ViewAction, MonthCardContract.ViewAction {

    @BindView(R.id.MembershipCardItemContainer)
    LinearLayout MembershipCardItemContainer;
    @BindView(R.id.MonthlyCardItemContainer)
    LinearLayout MonthlyCardItemContainer;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private VIPCardPresenter vipCardPresenter;
    private MonthCardPresenter monthCardPresenter;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy_membership_card, container, false);
        ButterKnife.bind(this, view);

        vipCardPresenter=new VIPCardPresenter(this,requireContext());
        vipCardPresenter.doVIPCard();
        monthCardPresenter=new MonthCardPresenter(this,requireContext());
        monthCardPresenter.doMonthCard();
        return view;
    }


    @Override
    public void VIPCardSuccess(List<ImgAndTxt> imgAndTxt) {
        mHandler.postDelayed(() -> {
            for (int i = 0; i < imgAndTxt.size(); i++) {
                ImageView imageView=new ImageView(requireContext());
                GlideApp.with(requireContext())
                        .load(imgAndTxt.get(i).getImg().toString())
                        .fitCenter()
                        .into(imageView);
                imageView.setPadding(0,0,16,0);
//                MembershipCardItemContainer.addView(imageView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                MembershipCardItemContainer.addView(imageView, new LayoutParams(800, 450));
            }
        }, 1);
    }

    @Override
    public void MonthCardSuccess(List<ImgAndTxt> imgAndTxt) {
        mHandler.postDelayed(() -> {
            for (int i = 0; i < imgAndTxt.size(); i++) {
                ImageView imageView=new ImageView(requireContext());
                GlideApp.with(requireContext())
                        .load(imgAndTxt.get(i).getImg().toString())
                        .fitCenter()
                        .into(imageView);
                imageView.setPadding(0,0,16,0);
//                MonthlyCardItemContainer.addView(imageView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                MonthlyCardItemContainer.addView(imageView, new LayoutParams(800, 450));
            }
        }, 1);
    }

    @Override
    public void showProgress() {
        getActivity().runOnUiThread(() -> progressBar.setVisibility(View.VISIBLE));
    }

    @Override
    public void hideProgress() {
        getActivity().runOnUiThread(() -> progressBar.setVisibility(View.GONE));
    }

    @Override
    public void errorOccurred(String reason) {

    }
}
