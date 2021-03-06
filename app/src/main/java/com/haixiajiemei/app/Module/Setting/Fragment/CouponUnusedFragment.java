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
import com.haixiajiemei.app.Module.Setting.Contract.CouponItemCallback;
import com.haixiajiemei.app.Module.Setting.Contract.CouponUnusedContract;
import com.haixiajiemei.app.Module.Setting.Model.Coupon;
import com.haixiajiemei.app.Module.Setting.Present.CouponUnusedPresenter;
import com.haixiajiemei.app.R;

import java.util.List;

import static com.haixiajiemei.app.Util.FunTools.switchFragmentToBack;

public class CouponUnusedFragment extends Fragment implements CouponUnusedContract.ViewAction, CouponItemCallback {
    @BindView(R.id.CouponUnused)
    RecyclerView CouponUnused;

    private CouponItemAdapter adapter;
    private CouponUnusedPresenter presenter;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupon_unused, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter = new CouponUnusedPresenter(this, requireContext());
        presenter.doCouponUnused();
    }

    @Override
    public void CouponUnusedSuccess(List<Coupon> rechargeList) {
        mHandler.postDelayed(() -> {
            adapter = new CouponItemAdapter(rechargeList, CouponUnusedFragment.this, requireContext(),"Coupon");
            CouponUnused.setLayoutManager(new LinearLayoutManager(requireContext()));
            CouponUnused.setAdapter(adapter);
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
            presenter = new CouponUnusedPresenter(this, requireContext());
            presenter.doCouponUnused();
        }, 1);
    }

    @Override
    public void onCouponItemClicked(int accountCouponMapID, float denomination, String name, String storeName, String dueTime) {
        QRcodeCouponFragment qRcodeCouponFragment = new QRcodeCouponFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("accountCouponMapID", accountCouponMapID);
        bundle.putFloat("denomination", denomination);
        bundle.putString("name", name);
        bundle.putString("storeName", storeName);
        bundle.putString("dueTime", dueTime);
        qRcodeCouponFragment.setArguments(bundle);
        switchFragmentToBack(R.id.fragment_Introduction, qRcodeCouponFragment, requireActivity());
    }
}
