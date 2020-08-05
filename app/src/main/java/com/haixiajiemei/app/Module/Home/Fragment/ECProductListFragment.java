package com.haixiajiemei.app.Module.Home.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.haixiajiemei.app.Module.Home.Adapter.SpinnerAdapter;
import com.haixiajiemei.app.Module.Home.Contract.GetItemsListContract;
import com.haixiajiemei.app.Module.Home.Model.ItemsList;
import com.haixiajiemei.app.Module.Home.Present.GetItemsListPresenter;
import com.haixiajiemei.app.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ECProductListFragment extends Fragment implements GetItemsListContract.ViewAction {
    @BindView(R.id.category)
    Spinner category;
    @BindView(R.id.ECProductList)
    RecyclerView ECProductList;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private ArrayList<String> categorylist;
    private GetItemsListPresenter getItemsListPresenter;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ecproduct_list, container, false);
        ButterKnife.bind(this, view);

        init();
        return view;
    }

    private void init() {
        categorylist = new ArrayList<>();
        categorylist.addAll(Arrays.asList(getResources().getStringArray(R.array.category_list)));
        SpinnerAdapter listAdapter = new SpinnerAdapter(requireContext(), categorylist);
        category.setAdapter(listAdapter);
//        category.setOnItemSelectedListener(Select);

        getItemsListPresenter=new GetItemsListPresenter(this,requireContext(),getArguments().getInt("key"));
        getItemsListPresenter.doItemsList();
    }

    @Override
    public void ItemsListSuccess(List<ItemsList> itemsLists) {
        mHandler.postDelayed(() -> {
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

    }
}
