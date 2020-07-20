package com.haixiajiemei.member.Module.Setting.Fragment;

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

import com.haixiajiemei.member.Module.Setting.Adapter.CouponItemAdapter;
import com.haixiajiemei.member.Module.Setting.Contract.CouponUsedContract;
import com.haixiajiemei.member.Module.Setting.Model.Coupon;
import com.haixiajiemei.member.Module.Setting.Presenter.CouponUsedPresenter;
import com.haixiajiemei.member.R;

import java.util.List;

public class CouponUsedFragment extends Fragment implements CouponUsedContract.ViewAction {
    @BindView(R.id.CouponUsed)
    RecyclerView CouponUsed;

    private CouponItemAdapter adapter;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private CouponUsedPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupon_used, container, false);
        ButterKnife.bind(this, view);

        presenter = new CouponUsedPresenter(this, requireContext());
        presenter.doCouponUsed();
        return view;
    }

    @Override
    public void CouponUsedSuccess(List<Coupon> rechargeList) {
        mHandler.postDelayed(() -> {
            adapter = new CouponItemAdapter(rechargeList, requireContext());
            CouponUsed.setLayoutManager(new LinearLayoutManager(requireContext()));
            CouponUsed.setAdapter(adapter);
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
