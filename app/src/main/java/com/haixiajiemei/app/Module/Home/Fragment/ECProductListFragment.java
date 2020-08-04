package com.haixiajiemei.app.Module.Home.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haixiajiemei.app.R;

import butterknife.ButterKnife;

public class ECProductListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ecproduct_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
