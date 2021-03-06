package com.haixiajiemei.app.Module.Setting.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haixiajiemei.app.Module.Setting.Adapter.CouponItemAdapter;
import com.haixiajiemei.app.Module.Setting.Contract.CouponExpiredContract;
import com.haixiajiemei.app.Module.Setting.Model.Coupon;
import com.haixiajiemei.app.Module.Setting.Present.CouponExpiredPresenter;
import com.haixiajiemei.app.R;

import java.util.List;

public class CouponExpiredFragment extends Fragment implements CouponExpiredContract.ViewAction {
    @BindView(R.id.CouponExpired)
    RecyclerView CouponExpired;

    private CouponItemAdapter adapter;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private CouponExpiredPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupon_expired, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter=new CouponExpiredPresenter(this,requireContext());
        presenter.doCouponExpired();
    }

    @Override
    public void CouponExpiredSuccess(List<Coupon> rechargeList) {
        mHandler.postDelayed(() -> {
            adapter = new CouponItemAdapter(rechargeList, requireContext());
            CouponExpired.setLayoutManager(new LinearLayoutManager(requireContext()));
            CouponExpired.setAdapter(adapter);
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
            presenter=new CouponExpiredPresenter(this,requireContext());
            presenter.doCouponExpired();
        }, 1);
    }
}
