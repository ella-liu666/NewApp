package com.haixiajiemei.app.Module.Setting.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.haixiajiemei.app.Helper.GlideApp;
import com.haixiajiemei.app.Module.Home.Model.ImgAndTxt;
import com.haixiajiemei.app.Module.Setting.Contract.MonthCardBuyContract;
import com.haixiajiemei.app.Module.Setting.Contract.VIPCardBuyContract;
import com.haixiajiemei.app.Module.Setting.Presenter.MonthCardBuyPresenter;
import com.haixiajiemei.app.Module.Setting.Presenter.VIPCardBuyPresenter;
import com.haixiajiemei.app.R;
import com.haixiajiemei.app.ToolBarActivity;

import java.util.List;

import static com.haixiajiemei.app.Util.Proclaim.CARDDETAILS;

public class MyMembershipCardFragment extends Fragment implements VIPCardBuyContract.ViewAction, MonthCardBuyContract.ViewAction {

    @BindView(R.id.MembershipCardItemContainer)
    LinearLayout MembershipCardItemContainer;
    @BindView(R.id.MonthlyCardItemContainer)
    LinearLayout MonthlyCardItemContainer;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private VIPCardBuyPresenter vipCardBuyPresenter;
    private MonthCardBuyPresenter monthCardBuyPresenter;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_membership_card, container, false);
        ButterKnife.bind(this, view);

        vipCardBuyPresenter = new VIPCardBuyPresenter(this, requireContext());
        vipCardBuyPresenter.doVIPCardBuy();
        monthCardBuyPresenter = new MonthCardBuyPresenter(this, requireContext());
        monthCardBuyPresenter.doMonthCardBuy();
        return view;
    }

    private String getCenterItem(View view) {
        String s = String.valueOf (((ImageView) view).getId());
        Log.v("performItemClick", s);
        return s;
    }

    @Override
    public void VIPCardBuySuccess(List<ImgAndTxt> imgAndTxt) {
        mHandler.postDelayed(() -> {
            for (int i = 0; i < imgAndTxt.size(); i++) {
                ImageView imageView = new ImageView(requireContext());
                GlideApp.with(requireContext())
                        .load(imgAndTxt.get(i).getImg().toString())
                        .fitCenter()
                        .into(imageView);
                imageView.setPadding(0, 0, 16, 0);
                imageView.setId(imgAndTxt.get(i).getId());
                imageView.setOnClickListener(view -> {
                    Intent intent = new Intent(getActivity(), ToolBarActivity.class);
                    intent.putExtra("Type", CARDDETAILS);
                    intent.putExtra("cardID", getCenterItem(view));
                    intent.putExtra("Tag","VIP");
                    startActivity(intent);
                });
//                MembershipCardItemContainer.addView(imageView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                MembershipCardItemContainer.addView(imageView, new ViewGroup.LayoutParams(800, 450));
            }
        }, 1);
    }

    @Override
    public void MonthCardBuySuccess(List<ImgAndTxt> imgAndTxt) {
        mHandler.postDelayed(() -> {
            for (int i = 0; i < imgAndTxt.size(); i++) {
                ImageView imageView = new ImageView(requireContext());
                GlideApp.with(requireContext())
                        .load(imgAndTxt.get(i).getImg().toString())
                        .fitCenter()
                        .into(imageView);
                imageView.setPadding(0, 0, 16, 0);
                imageView.setId(imgAndTxt.get(i).getId());
                imageView.setOnClickListener(view -> {
                    Intent intent = new Intent(getActivity(), ToolBarActivity.class);
                    intent.putExtra("Type", CARDDETAILS);
                    intent.putExtra("cardID", getCenterItem(view));
                    intent.putExtra("Tag","Card");
                    startActivity(intent);
                });
//                MonthlyCardItemContainer.addView(imageView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                MonthlyCardItemContainer.addView(imageView, new ViewGroup.LayoutParams(800, 450));
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
