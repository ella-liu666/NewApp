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
import com.haixiajiemei.member.Module.Setting.Contract.TradeContract;
import com.haixiajiemei.member.Module.Setting.Model.Recharge;
import com.haixiajiemei.member.Module.Setting.Presenter.TradePresenter;
import com.haixiajiemei.member.R;

import java.util.ArrayList;
import java.util.List;

public class ExpensesRecordFragment extends Fragment implements TradeContract.ViewAction{

    private TradePresenter presenter;
    private RechargeAdapter rechargeAdapter;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @BindView(R.id.ExpensesRecord)
    RecyclerView ExpensesRecord;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expenses_record, container, false);
        ButterKnife.bind(this, view);

        presenter=new TradePresenter(this,requireContext());
        presenter.doTrade();
        return view;
    }

    @Override
    public void TradeSuccess(List<Recharge> rechargeList) {
        mHandler.postDelayed(() -> {

            ArrayList<Recharge> Recharge = new ArrayList<>();
            Recharge.addAll(rechargeList);

            rechargeAdapter = new RechargeAdapter(requireContext(), Recharge,"Trade");
            ExpensesRecord.setLayoutManager(new LinearLayoutManager(requireContext()));
            ExpensesRecord.setAdapter(rechargeAdapter);
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
