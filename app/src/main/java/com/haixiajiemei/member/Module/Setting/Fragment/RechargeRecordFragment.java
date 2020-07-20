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

import com.haixiajiemei.member.Module.Setting.Adapter.RechargeAdapter;
import com.haixiajiemei.member.Module.Setting.Contract.RechargeContract;
import com.haixiajiemei.member.Module.Setting.Model.Recharge;
import com.haixiajiemei.member.Module.Setting.Presenter.RechargePresenter;
import com.haixiajiemei.member.R;

import java.util.ArrayList;
import java.util.List;

public class RechargeRecordFragment extends Fragment implements RechargeContract.ViewAction {

    private RechargePresenter presenter;
    private RechargeAdapter rechargeAdapter;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @BindView(R.id.RechargeRecord)
    RecyclerView RechargeRecord;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recharge_record, container, false);
        ButterKnife.bind(this, view);

        presenter = new RechargePresenter(this, requireContext());
        presenter.doRecharge();

        return view;
    }

    @Override
    public void RechargeSuccess(List<Recharge> rechargeList) {
        mHandler.postDelayed(() -> {

            ArrayList<Recharge> Recharge = new ArrayList<>();
            Recharge.addAll(rechargeList);

            rechargeAdapter = new RechargeAdapter(requireContext(), Recharge);
            RechargeRecord.setLayoutManager(new LinearLayoutManager(requireContext()));
            RechargeRecord.setAdapter(rechargeAdapter);
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
