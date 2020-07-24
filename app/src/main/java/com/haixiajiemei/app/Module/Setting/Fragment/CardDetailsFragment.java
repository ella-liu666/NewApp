package com.haixiajiemei.app.Module.Setting.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haixiajiemei.app.Helper.GlideApp;
import com.haixiajiemei.app.Module.Setting.Contract.BuyCardContract;
import com.haixiajiemei.app.Module.Setting.Contract.CardDetailContract;
import com.haixiajiemei.app.Module.Setting.Contract.VIPDetailContract;
import com.haixiajiemei.app.Module.Setting.Model.CardDetail;
import com.haixiajiemei.app.Module.Setting.Present.BuyCardPresenter;
import com.haixiajiemei.app.Module.Setting.Present.CardDetailPresenter;
import com.haixiajiemei.app.Module.Setting.Present.VIPDetailPresenter;
import com.haixiajiemei.app.R;

import static com.haixiajiemei.app.Util.FunTools.CreateAlertDialogTool;

public class CardDetailsFragment extends Fragment implements CardDetailContract.ViewAction, VIPDetailContract.ViewAction, BuyCardContract.ViewAction {
    @BindView(R.id.CardName)
    TextView CardName;
    @BindView(R.id.CardInfo)
    TextView CardInfo;
    @BindView(R.id.CardMsg)
    TextView CardMsg;
    @BindView(R.id.img_card)
    ImageView img_card;
    @BindView(R.id.welfare)
    LinearLayout welfare;
    @BindView(R.id.btn_buy)
    Button btn_buy;

    private CardDetailPresenter presenter;
    private VIPDetailPresenter vipDetailPresenter;
    private BuyCardPresenter buyCardPresenter;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private CardDetail mcardDetail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_details, container, false);
        ButterKnife.bind(this, view);

        if (getArguments().getString("Tag").equals("VIP")) {
            vipDetailPresenter = new VIPDetailPresenter(this, requireContext(), Integer.parseInt(getArguments().getString("cardID")));
            vipDetailPresenter.doVIPDetail();
        } else {
            presenter = new CardDetailPresenter(this, requireContext(), Integer.parseInt(getArguments().getString("cardID")));
            presenter.doCardDetail();
        }

        return view;
    }

    @OnClick(R.id.btn_buy)
    public void onClick(View view) {
        switch (mcardDetail.getBuyStatus().getType()) {
            case 1:
                CreateAlertDialogTool(requireContext(), R.string.remind, mcardDetail.getBuyStatus().getMsg());
                break;
            case 2:
            case 3:
                Log.e("333====","gg ="+ mcardDetail.getCardPrice());
                AlertDialog(requireContext(), R.string.remind, mcardDetail.getBuyStatus().getMsg(), mcardDetail.getCardID(),
                        mcardDetail.getCardCategoryID(), mcardDetail.getBuyStatus().getType(), mcardDetail.getCardName(), mcardDetail.getCardPrice()
                        , mcardDetail.getUpgradeCardPrice(), mcardDetail.getCardCurrentAmount());
                break;
        }

    }

    public void AlertDialog(Context context, int Title, String Message, int cardID, int cardCategoryID, int type, String cardName, float cardPrice, float upgradeCardPrice, int cardCurrentAmount) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.setPositiveButton(R.string.confirm, (dialog, which) -> {
            buyCardPresenter = new BuyCardPresenter(this, requireContext(), cardID, cardCategoryID, type, cardName, String.valueOf(cardPrice), upgradeCardPrice, cardCurrentAmount);
            buyCardPresenter.doBuyCard();
        });
        builder.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void CardDetailSuccess(CardDetail cardDetail) {
        mHandler.postDelayed(() -> {
            mcardDetail = cardDetail;

            CardName.setText(cardDetail.getCardName());
            GlideApp.with(requireContext())
                    .load(cardDetail.getCardImg())
                    .fitCenter()
                    .into(img_card);
            for (int i = 0; i < cardDetail.getCardBev().size(); i++) {
                TextView textView = new TextView(requireContext());
                Drawable drawable = getResources().getDrawable(R.drawable.baseline_star_border_black_18dp);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                textView.setCompoundDrawables(drawable, null, null, null);
                textView.setText(cardDetail.getCardBev().get(i).getName());
                textView.setTextSize(14);
                textView.setTextColor(getResources().getColor(R.color.txt_color));
                textView.setGravity(Gravity.CENTER_VERTICAL);
                welfare.addView(textView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            }
            LinearLayout line = new LinearLayout(requireContext());
            line.setBackground(getResources().getDrawable(R.drawable.shape_dash_line));
            welfare.addView(line, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 10));

            LinearLayout CardPriceFrame = new LinearLayout(requireContext());
            CardPriceFrame.setOrientation(LinearLayout.HORIZONTAL);
            CardPriceFrame.setPadding(0, 32, 0, 0);
            welfare.addView(CardPriceFrame, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            LinearLayout CardPriceFrameLeft = new LinearLayout(requireContext());
            CardPriceFrameLeft.setGravity(Gravity.LEFT);
            CardPriceFrame.addView(CardPriceFrameLeft, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

            TextView txt_cardPrice = new TextView(requireContext());
            txt_cardPrice.setTextColor(getResources().getColor(R.color.txt_color));
            txt_cardPrice.setTextSize(16);
            txt_cardPrice.setText(getString(R.string.txt_buyCard, cardDetail.getCardName()));
            CardPriceFrameLeft.addView(txt_cardPrice, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            LinearLayout CardPriceFrameRight = new LinearLayout(requireContext());
            CardPriceFrameRight.setGravity(Gravity.RIGHT);
            CardPriceFrame.addView(CardPriceFrameRight, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

            TextView cardPrice = new TextView(requireContext());
            cardPrice.setText("¥" + String.valueOf(cardDetail.getCardPrice()));
            cardPrice.setTextColor(getResources().getColor(R.color.black));
            cardPrice.setTypeface(Typeface.DEFAULT_BOLD);
            cardPrice.setTextSize(18);
            CardPriceFrameRight.addView(cardPrice, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            CardInfo.setText(cardDetail.getCardInfo());
            CardMsg.setText(cardDetail.getCardMsg());

            switch (cardDetail.getBuyStatus().getType()) {
                case 0:
                    btn_buy.setVisibility(View.GONE);
                    break;
                case 1:
                case 2:
                    btn_buy.setVisibility(View.VISIBLE);
                    btn_buy.setText(R.string.ToPay);
                    break;
                case 3:
                    btn_buy.setVisibility(View.VISIBLE);
                    btn_buy.setText(R.string.ToUpgrade);
                    break;
            }
        }, 1);
    }

    @Override
    public void VIPDetailSuccess(CardDetail cardDetail) {
        mHandler.postDelayed(() -> {
            mcardDetail = cardDetail;

            CardName.setText(cardDetail.getCardName());
            GlideApp.with(requireContext())
                    .load(cardDetail.getCardImg())
                    .fitCenter()
                    .into(img_card);
            for (int i = 0; i < cardDetail.getCardBev().size(); i++) {
                TextView textView = new TextView(requireContext());
                Drawable drawable = getResources().getDrawable(R.drawable.baseline_star_border_black_18dp);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//這句一定要加
                textView.setCompoundDrawables(drawable, null, null, null);
                textView.setText(cardDetail.getCardBev().get(i).getName());
                textView.setTextSize(14);
                textView.setTextColor(getResources().getColor(R.color.txt_color));
                textView.setGravity(Gravity.CENTER_VERTICAL);
                welfare.addView(textView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            }
            LinearLayout line = new LinearLayout(requireContext());
            line.setBackground(getResources().getDrawable(R.drawable.shape_dash_line));
            welfare.addView(line, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 10));

            LinearLayout CardPriceFrame = new LinearLayout(requireContext());
            CardPriceFrame.setOrientation(LinearLayout.HORIZONTAL);
            CardPriceFrame.setPadding(0, 32, 0, 0);
            welfare.addView(CardPriceFrame, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            LinearLayout CardPriceFrameLeft = new LinearLayout(requireContext());
            CardPriceFrameLeft.setGravity(Gravity.LEFT);
            CardPriceFrame.addView(CardPriceFrameLeft, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

            TextView txt_cardPrice = new TextView(requireContext());
            txt_cardPrice.setTextColor(getResources().getColor(R.color.txt_color));
            txt_cardPrice.setTextSize(16);
            txt_cardPrice.setText(getString(R.string.txt_buyCard, cardDetail.getCardName()));
            CardPriceFrameLeft.addView(txt_cardPrice, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            LinearLayout CardPriceFrameRight = new LinearLayout(requireContext());
            CardPriceFrameRight.setGravity(Gravity.RIGHT);
            CardPriceFrame.addView(CardPriceFrameRight, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

            TextView cardPrice = new TextView(requireContext());
            cardPrice.setText("¥" + String.valueOf(cardDetail.getCardPrice()));
            cardPrice.setTextColor(getResources().getColor(R.color.black));
            cardPrice.setTypeface(Typeface.DEFAULT_BOLD);
            cardPrice.setTextSize(18);
            CardPriceFrameRight.addView(cardPrice, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            CardInfo.setText(cardDetail.getCardInfo());
            CardMsg.setText(cardDetail.getCardMsg());

            switch (cardDetail.getBuyStatus().getType()) {
                case 0:
                    btn_buy.setVisibility(View.GONE);
                    break;
                case 1:
                case 2:
                    btn_buy.setVisibility(View.VISIBLE);
                    btn_buy.setText(R.string.ToPay);
                    break;
                case 3:
                    btn_buy.setVisibility(View.VISIBLE);
                    btn_buy.setText(R.string.ToUpgrade);
                    break;
            }
        }, 1);
    }

    @Override
    public void BuyCardSuccess() {
        mHandler.postDelayed(() -> {
            getActivity().onBackPressed();
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
