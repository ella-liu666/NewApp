package com.haixiajiemei.member.Module.Order;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.haixiajiemei.member.R;

public class OrderFragment extends Fragment {

    @BindView(R.id.PickupMethod)
    Switch PickupMethod;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this, view);

        PickupMethod.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
        return view;
    }
}
