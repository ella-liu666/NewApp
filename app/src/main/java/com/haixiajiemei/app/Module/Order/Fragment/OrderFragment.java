package com.haixiajiemei.app.Module.Order.Fragment;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.haixiajiemei.app.Helper.GlideApp;
import com.haixiajiemei.app.Module.Order.Adapter.StoreItemAdapter;
import com.haixiajiemei.app.Module.Order.Contract.OrderCallback;
import com.haixiajiemei.app.Module.Order.Contract.StoreFeedingContract;
import com.haixiajiemei.app.Module.Order.Contract.StoreFilterContract;
import com.haixiajiemei.app.Module.Order.Contract.StoreItemCallback;
import com.haixiajiemei.app.Module.Order.Contract.StoreItemContract;
import com.haixiajiemei.app.Module.Order.Contract.StoreListContract;
import com.haixiajiemei.app.Module.Order.Model.ECShopping;
import com.haixiajiemei.app.Module.Order.Model.Feeding;
import com.haixiajiemei.app.Module.Order.Model.IdAndTxt;
import com.haixiajiemei.app.Module.Order.Model.ShoppingCart;
import com.haixiajiemei.app.Module.Order.Model.ShoppingCartList;
import com.haixiajiemei.app.Module.Order.Model.StoreFeed;
import com.haixiajiemei.app.Module.Order.Model.StoreItem;
import com.haixiajiemei.app.Module.Order.Model.StoreList;
import com.haixiajiemei.app.Module.Order.Model.StoreListModel;
import com.haixiajiemei.app.Module.Order.Present.StoreFeedingPresenter;
import com.haixiajiemei.app.Module.Order.Present.StoreFilterPresenter;
import com.haixiajiemei.app.Module.Order.Present.StoreItemPresenter;
import com.haixiajiemei.app.Module.Order.Present.StoreListPresenter;
import com.haixiajiemei.app.R;
import com.haixiajiemei.app.SQLite.ShoppingCartDB;

import java.util.ArrayList;
import java.util.List;

import static com.haixiajiemei.app.SQLite.ECShoppingDB.GetECShopping;
import static com.haixiajiemei.app.SQLite.ShoppingCartDB.GetShoppingCart;
import static com.haixiajiemei.app.Util.FunTools.CreateAlertDialogTool;

public class OrderFragment extends Fragment implements StoreListContract.ViewAction, StoreItemContract.ViewAction,
        StoreFilterContract.ViewAction, StoreItemCallback, StoreFeedingContract.ViewAction {

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
    @BindView(R.id.distance)
    TextView distance;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private StoreListPresenter storeListPresenter;
    private StoreFilterPresenter storeFilterPresenter;
    private StoreItemPresenter storeItemPresenter;
    private StoreFeedingPresenter storeFeedingPresenter;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private StoreItemAdapter storeItemAdapter;
    private int Num = 1;
    private boolean check = false;
    private List<StoreListModel> list;
    private List<RadioButton> radioButtonlist;
    private ShoppingCartList shoppingCartList = new ShoppingCartList();
    private OrderCallback callback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this, view);

        storeListPresenter = new StoreListPresenter(this, requireContext());
        storeListPresenter.doStoreList();

        shoppingCartList.cart = new ArrayList<>();
        return view;
    }

    private int getCenterItem(View view) {
        int s = ((ImageView) view).getId();
        return s;
    }

    @Override
    public void StoreListSuccess(List<StoreList> storeListList) {
        mHandler.postDelayed(() -> {
            ECShopping EC = GetECShopping(requireContext());
            ShoppingCartList sd = GetShoppingCart(requireContext());
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
                    shoppingCartList.setDbid(view.getId());

                    if (EC == null) {
                        if (sd != null && sd.getDbid() != view.getId()) {
                            CreateAlertDialogTool(requireContext(), R.string.note, R.string.clear_shopping_cart);
                        } else {
                            for (StoreList storeList : storeListList) {
                                if (storeList.getDbid() == getCenterItem(view)) {
                                    store_infor.setText(storeList.getStoreInfo().getcName());
                                    distance.setText(storeList.getStoreInfo().getAddress());
                                    shoppingCartList.setStoreAccount(storeList.getDbName());
                                    storeFilterPresenter = new StoreFilterPresenter(this,
                                            requireContext(), getCenterItem(view), storeList.getDbName());
                                    storeFilterPresenter.doStoreFilter();
                                }
                            }
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
            if (storeItem.size() == 0) {
                order_recycler.setVisibility(View.GONE);
                NoData.setVisibility(View.VISIBLE);
            } else {
                NoData.setVisibility(View.GONE);
                order_recycler.setVisibility(View.VISIBLE);
                storeItemAdapter = new StoreItemAdapter(storeItem, requireContext(), OrderFragment.this);
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
            list = new ArrayList<>();
            for (int i = 0; i < idAndTxt.size(); i++) {
                LinearLayout layout = new LinearLayout(requireContext());
                layout.setGravity(Gravity.CENTER_VERTICAL);
                layout.setId(i);
                if (i == 0) {
                    storeItemPresenter = new StoreItemPresenter(this, requireContext(), idAndTxt.get(0).getDbName(), idAndTxt.get(0).getId());
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
                storeListModel.dbid = idAndTxt.get(i).getId();
                storeListModel.dbName = idAndTxt.get(i).getDbName();
                storeListModel.tab = layout;


                list.add(storeListModel);

                layout.setOnClickListener(view -> {
                    for (StoreListModel SLM : list) {
                        SLM.tab.setBackgroundColor(getResources().getColor(R.color.original_bg));
                        if (SLM.tab.getId() == view.getId()) {
                            storeItemPresenter = new StoreItemPresenter(this, requireContext(), SLM.dbName, SLM.dbid);
                            storeItemPresenter.doStoreItem();
                            storeListModel.tab.setBackground(getResources().getDrawable(R.drawable.order_item_bg));
                        }
                    }
                });
            }
        }, 1);
    }

    @Override
    public void StoreFeedingSuccess(StoreFeed storeFeed) {
        mHandler.postDelayed(() -> {
            AlertDialog(storeFeed);
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
        mHandler.postDelayed(() -> {
            storeListPresenter = new StoreListPresenter(this, requireContext());
            storeListPresenter.doStoreList();

            shoppingCartList.cart = new ArrayList<>();
        }, 1);
    }

    @Override
    public void onSettingItemClicked(int id, String name) {
        storeFeedingPresenter = new StoreFeedingPresenter(this, requireContext(), name, id);
        storeFeedingPresenter.doStoreFeeding();
    }

    private void AlertDialog(StoreFeed storeFeed) {
        radioButtonlist = new ArrayList<>();
        Num = 1;
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        View view = getLayoutInflater().inflate(R.layout.store_feed, null);
        ImageView feed_img = view.findViewById(R.id.feed_img);
        GlideApp.with(requireContext())
                .load(storeFeed.getImage().toString())
                .fitCenter()
                .into(feed_img);

        TextView feed_title = view.findViewById(R.id.feed_title);
        feed_title.setText(storeFeed.getName());
        TextView feed_price = view.findViewById(R.id.feed_price);
        feed_price.setText("¥" + String.valueOf(storeFeed.getPrice()));
        TextView Introduction = view.findViewById(R.id.Introduction);
        Introduction.setText(storeFeed.getDetail());
        TextView num = view.findViewById(R.id.num);
        num.setText(String.valueOf(Num));
        ImageView less = view.findViewById(R.id.less);
        less.setOnClickListener(view1 -> {
            Num--;
            if (Num < 1) {
                Num = 1;
            }
            num.setText(String.valueOf(Num));
        });

        ImageView plus = view.findViewById(R.id.plus);
        plus.setOnClickListener(view12 -> {
            Num++;
            num.setText(String.valueOf(Num));
        });
        ImageButton close = view.findViewById(R.id.close);
        LinearLayout customizedItem = view.findViewById(R.id.customizedItem);
        for (int i = 0; i < storeFeed.getFeeding().size(); i++) {
            TextView title = new TextView(requireContext());
            title.setTextColor(getResources().getColor(R.color.black));
            title.setTextSize(16);
            title.setText(storeFeed.getFeeding().get(i).getFeedingCategoryName() + "/" + storeFeed.getFeeding().get(i).getFeedingStatusName());
            title.setTypeface(Typeface.DEFAULT_BOLD);
            title.setPadding(16, 16, 16, 16);
            customizedItem.addView(title, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            if (storeFeed.getFeeding().get(i).getFeedingStatusName().equals("必選且只能一項")) {
                RadioGroup radioGroup = new RadioGroup(requireContext());
                radioGroup.setOrientation(LinearLayout.VERTICAL);
                customizedItem.addView(radioGroup, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                for (int j = 0; j < storeFeed.getFeeding().get(i).getBlend().size(); j++) {
                    RadioButton radioButton = new RadioButton(requireContext());
                    if (j == 0) {
                        radioButton.setChecked(true);
                    }
                    radioButton.setId(storeFeed.getFeeding().get(i).getBlend().get(j).getFeedingID());
                    radioButton.setTextColor(getResources().getColor(R.color.black));
                    radioButton.setTextSize(16);
                    radioButton.setText(storeFeed.getFeeding().get(i).getBlend().get(j).getNewName());

                    radioButtonlist.add(radioButton);

                    radioGroup.addView(radioButton, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                }
            } else {
                for (int j = 0; j < storeFeed.getFeeding().get(i).getBlend().size(); j++) {
                    RadioButton radioButton = new RadioButton(requireContext());
                    radioButton.setId(storeFeed.getFeeding().get(i).getBlend().get(j).getFeedingID());
                    radioButton.setTextColor(getResources().getColor(R.color.black));
                    radioButton.setTextSize(16);
                    radioButton.setText(storeFeed.getFeeding().get(i).getBlend().get(j).getNewName());

                    radioButton.setOnClickListener(view15 -> {
                        if (!check) {
                            radioButton.setChecked(true);
                            check = true;
                        } else {
                            radioButton.setChecked(false);
                            check = false;
                        }
                    });
                    radioButtonlist.add(radioButton);
                    customizedItem.addView(radioButton, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                }
            }
        }


        AlertDialog alertDialog = builder.create();
        close.setOnClickListener(view14 -> alertDialog.dismiss());
        Button add_shopping_cart = view.findViewById(R.id.add_shopping_cart);
        add_shopping_cart.setOnClickListener(view13 -> {

            Gson gson = new Gson();
            ShoppingCartList sd = GetShoppingCart(requireContext());
            if (sd == null) {
                shoppingCartList.setcName(store_infor.getText().toString());
                shoppingCartList.setAddress(distance.getText().toString());
                ShoppingCart SC = new ShoppingCart();
                SC.mealID = String.valueOf(storeFeed.getMealsID());
                SC.mealName = storeFeed.getName();
                SC.image = storeFeed.getImage();
                SC.amount = Num;
                SC.price = storeFeed.getPrice();
                SC.feeding = new ArrayList<>();
                for (RadioButton RB : radioButtonlist) {
                    if (RB.isChecked()) {
                        Feeding feeding = new Feeding();
                        feeding.id = String.valueOf(RB.getId());
                        feeding.name = RB.getText().toString();
                        if (RB.getText().toString().contains("$")) {
                            feeding.price = Float.parseFloat(RB.getText().toString().substring(RB.getText().toString().indexOf("$")).replace("$", "").replace(")", ""));
                        } else {
                            feeding.price = 0;
                        }
                        SC.feeding.add(feeding);
                    }
                }
                shoppingCartList.cart.add(SC);
                ShoppingCartDB.InsertData(getContext(), gson.toJson(shoppingCartList));
            } else {
                shoppingCartList = sd;
                ShoppingCart SC = new ShoppingCart();
                SC.mealID = String.valueOf(storeFeed.getMealsID());
                SC.mealName = storeFeed.getName();
                SC.image = storeFeed.getImage();
                SC.amount = Num;
                SC.price = storeFeed.getPrice();
                SC.feeding = new ArrayList<>();
                for (RadioButton RB : radioButtonlist) {
                    if (RB.isChecked()) {
                        Feeding feeding = new Feeding();
                        feeding.id = String.valueOf(RB.getId());
                        feeding.name = RB.getText().toString();
                        if (RB.getText().toString().contains("$")) {
                            feeding.price = Float.parseFloat(RB.getText().toString().substring(RB.getText().toString().indexOf("$")).replace("$", "").replace(")", ""));
                        } else {
                            feeding.price = 0;
                        }
                        SC.feeding.add(feeding);
                    }
                }
                shoppingCartList.cart.add(SC);
                ShoppingCartDB.UpdateData(getContext(), gson.toJson(shoppingCartList));
            }
            callback = (OrderCallback) requireContext();
            callback.onOrderCallback();
            alertDialog.dismiss();
        });

        alertDialog.setView(view);
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }
}
