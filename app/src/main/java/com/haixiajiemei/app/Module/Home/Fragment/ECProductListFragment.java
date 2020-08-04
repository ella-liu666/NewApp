package com.haixiajiemei.app.Module.Home.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.haixiajiemei.app.Module.Home.Adapter.SpinnerAdapter;
import com.haixiajiemei.app.R;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ECProductListFragment extends Fragment {
    @BindView(R.id.category)
    Spinner category;

    private ArrayList<String> categorylist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ecproduct_list, container, false);
        ButterKnife.bind(this, view);

        init();
        return view;
    }

    private void init(){
        categorylist=new ArrayList<>();
        categorylist.addAll(Arrays.asList(getResources().getStringArray(R.array.category_list)));
        SpinnerAdapter listAdapter = new SpinnerAdapter(requireContext(), categorylist);
        category.setAdapter(listAdapter);
//        category.setOnItemSelectedListener(Select);

        SwitchCategory();
    }

    private void SwitchCategory(){
        switch (getArguments().getInt("key")){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }
}
