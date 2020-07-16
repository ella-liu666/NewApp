package com.haixiajiemei.member.Module.Setting.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haixiajiemei.member.R;

public class MessageCenterFragment extends Fragment {

    @BindView(R.id.news_item)
    RecyclerView news_item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_center, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
