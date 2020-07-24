package com.haixiajiemei.app.Module.Order.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haixiajiemei.app.Helper.GlideApp;
import com.haixiajiemei.app.Module.Order.Contract.StoreListContract;
import com.haixiajiemei.app.Module.Order.Model.StoreList;
import com.haixiajiemei.app.Module.Order.Present.StoreListPresenter;
import com.haixiajiemei.app.R;
import android.view.ViewGroup.LayoutParams;

import java.util.List;

public class OrderFragment extends Fragment implements StoreListContract.ViewAction {

    @BindView(R.id.storeNameItemContainer)
    LinearLayout storeNameItemContainer;

    private StoreListPresenter storeListPresenter;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this, view);

        storeListPresenter=new StoreListPresenter(this,requireContext());
        storeListPresenter.doStoreList();
        return view;
    }

    @Override
    public void StoreListSuccess(List<StoreList> storeListList) {
        mHandler.postDelayed(() -> {
            storeNameItemContainer.removeAllViews();
            for (int i = 0; i < storeListList.size(); i++) {
                LinearLayout layout=new LinearLayout(requireContext());
                storeNameItemContainer.addView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setPadding(0, 0, 16, 0);

                ImageView imageView = new ImageView(requireContext());
                GlideApp.with(requireContext())
                        .load(storeListList.get(i).getStoreInfo().getImage().toString())
                        .fitCenter()
                        .into(imageView);
                LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 0, 8);
                lp.gravity= Gravity.CENTER;
                layout.addView(imageView, lp);

                TextView txt=new TextView(requireContext());
                txt.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                txt.setTextSize(14);
                txt.setSingleLine(true);
                txt.setSelected(true);
                txt.setHorizontallyScrolling(true);
                txt.setText(storeListList.get(i).getStoreInfo().getcName());


                layout.setOnClickListener(view -> {

                });
//                storeNameItemContainer.addView(txt,new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                layout.addView(txt,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 0,1));
            }
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
