package com.haixiajiemei.app.Module.Order.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.haixiajiemei.app.Module.Order.Adapter.StoreItemAdapter;
import com.haixiajiemei.app.Module.Order.Contract.StoreFilterContract;
import com.haixiajiemei.app.Module.Order.Contract.StoreItemCallback;
import com.haixiajiemei.app.Module.Order.Contract.StoreItemContract;
import com.haixiajiemei.app.Module.Order.Contract.StoreListContract;
import com.haixiajiemei.app.Module.Order.Model.IdAndTxt;
import com.haixiajiemei.app.Module.Order.Model.StoreItem;
import com.haixiajiemei.app.Module.Order.Model.StoreList;
import com.haixiajiemei.app.Module.Order.Model.StoreListModel;
import com.haixiajiemei.app.Module.Order.Present.StoreFilterPresenter;
import com.haixiajiemei.app.Module.Order.Present.StoreItemPresenter;
import com.haixiajiemei.app.Module.Order.Present.StoreListPresenter;
import com.haixiajiemei.app.R;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment implements StoreListContract.ViewAction, StoreItemContract.ViewAction, StoreFilterContract.ViewAction, StoreItemCallback {

    @BindView(R.id.storeNameItemContainer)
    LinearLayout storeNameItemContainer;
    @BindView(R.id.SelectStore)
    LinearLayout SelectStore;
    @BindView(R.id.txt_tab)
    LinearLayout txt_tab;
    @BindView(R.id.NoData)
    LinearLayout NoData;
    @BindView(R.id.order_recycler)
    RecyclerView order_recycler;
    @BindView(R.id.store_infor)
    TextView store_infor;

    private StoreListPresenter storeListPresenter;
    private StoreFilterPresenter storeFilterPresenter;
    private StoreItemPresenter storeItemPresenter;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private StoreItemAdapter storeItemAdapter;

    private List<StoreListModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this, view);

        storeListPresenter = new StoreListPresenter(this, requireContext());
        storeListPresenter.doStoreList();
        return view;
    }

    private int getCenterItem(View view) {
        int s = ((ImageView) view).getId();
        return s;
    }

    @Override
    public void StoreListSuccess(List<StoreList> storeListList) {
        mHandler.postDelayed(() -> {
            storeNameItemContainer.removeAllViews();
            for (int i = 0; i < storeListList.size(); i++) {
                LinearLayout layout = new LinearLayout(requireContext());
                storeNameItemContainer.addView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setPadding(0, 16, 32, 16);

                ImageView imageView = new ImageView(requireContext());
                GlideApp.with(requireContext())
                        .load(storeListList.get(i).getStoreInfo().getImage().toString())
                        .fitCenter()
                        .into(imageView);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(200, 200);
                lp.gravity = Gravity.CENTER;
                imageView.setPadding(0, 0, 0, 16);
                imageView.setId(storeListList.get(i).getDbid());
                imageView.setOnClickListener(view -> {
                    for (StoreList storeList : storeListList) {
                        if (storeList.getDbid() == getCenterItem(view)) {
                            store_infor.setText(storeList.getStoreInfo().getcName());
                            storeFilterPresenter = new StoreFilterPresenter(this,
                                    requireContext(), getCenterItem(view), storeList.getDbName());
                            storeFilterPresenter.doStoreFilter();
                        }
                    }
                });

                layout.addView(imageView, lp);

                TextView txt = new TextView(requireContext());
                txt.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                txt.setTextSize(14);
                txt.setSingleLine(true);
                txt.setSelected(true);
                txt.setHorizontallyScrolling(true);
                txt.setText(storeListList.get(i).getStoreInfo().getcName());

//                storeNameItemContainer.addView(txt,new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                layout.addView(txt, new LinearLayout.LayoutParams(200, LinearLayout.LayoutParams.WRAP_CONTENT));
            }
        }, 1);
    }

    @Override
    public void StoreItemSuccess(List<StoreItem> storeItem) {
        mHandler.postDelayed(() -> {
            if(storeItem.size()==0){
                order_recycler.setVisibility(View.GONE);
                NoData.setVisibility(View.VISIBLE);
            }else {
                NoData.setVisibility(View.GONE);
                order_recycler.setVisibility(View.VISIBLE);
                storeItemAdapter=new StoreItemAdapter(storeItem,requireContext(),OrderFragment.this);
                order_recycler.setLayoutManager(new LinearLayoutManager(requireContext()));
                order_recycler.setAdapter(storeItemAdapter);
            }

        }, 1);
    }

    @Override
    public void StoreFilterSuccess(List<IdAndTxt> idAndTxt) {
        mHandler.postDelayed(() -> {
            SelectStore.setVisibility(View.GONE);
            txt_tab.removeAllViews();
            list= new ArrayList<>();
            for (int i = 0; i < idAndTxt.size(); i++) {
                LinearLayout layout = new LinearLayout(requireContext());
                layout.setGravity(Gravity.CENTER_VERTICAL);
                layout.setId(i);
                if (i == 0) {
                    storeItemPresenter=new StoreItemPresenter(this,requireContext(),idAndTxt.get(0).getDbName(),idAndTxt.get(0).getId());
                    storeItemPresenter.doStoreItem();
                    layout.setBackground(getResources().getDrawable(R.drawable.order_item_bg));
                }

                txt_tab.addView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                TextView txt = new TextView(requireContext());

                txt.setTextSize(20);
                txt.setText(idAndTxt.get(i).getName());
                txt.setPadding(16, 32, 16, 32);
                layout.addView(txt, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                StoreListModel storeListModel = new StoreListModel();
                storeListModel.dbid=idAndTxt.get(i).getId();
                storeListModel.dbName=idAndTxt.get(i).getDbName();
                storeListModel.tab = layout;


                list.add(storeListModel);

                layout.setOnClickListener(view -> {
                    for (StoreListModel SLM : list) {
                        SLM.tab.setBackgroundColor(getResources().getColor(R.color.original_bg));
                        if (SLM.tab.getId() == view.getId()) {
                            storeItemPresenter=new StoreItemPresenter(this,requireContext(),SLM.dbName,SLM.dbid);
                            storeItemPresenter.doStoreItem();
                            storeListModel.tab.setBackground(getResources().getDrawable(R.drawable.order_item_bg));
                        }
                    }
                });
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

    @Override
    public void onSettingItemClicked(int id, String name) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
//        builder.setMessage(R.string.UnderConstruction);
//        builder.setPositiveButton(R.string.confirm, (dialog, which) -> dialog.dismiss());
//        AlertDialog dialog = builder.create();
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.show();
    }
}
