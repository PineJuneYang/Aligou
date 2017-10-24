package com.alg.ailigou.pages.home.searchgoods;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.widget.SearchView;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.DisplayUtils;
import com.alg.ailigou.library.utils.SpUtils;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.home.inject.DaggerHomeComponent;
import com.alg.ailigou.pages.home.inject.HomeModule;
import com.alg.ailigou.pages.home.search.SearchDetailActivity;
import com.alg.ailigou.pages.home.search.callback.OnLoadMoreDataListener;
import com.alg.ailigou.pages.home.searchgoods.adapter.HistorySearchHotWordsRecycleAdapter;
import com.alg.ailigou.pages.home.widget.YhFlowLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * AiligouApp
 * com.alg.ailigou.pages.home.searchgoods
 * Created by Chris Chen on 2017/7/25 17:19.
 * Explain:搜索商品
 */

public class SearchGoodsActivity extends BaseMvpActivity implements SearchGoodsContracts.View {


    @Inject
    SearchGoodsPresenter presenter;


    @BindView(R.id.flowLayout)
    YhFlowLayout flowLayout;
    @BindView(R.id.recyler_view_search_history)
    RecyclerView recylerViewSearchHistory;
    @BindView(R.id.linear_search_show_hot)
    LinearLayout linearSearchShowHot;
    @BindView(R.id.iv_base_back)
    ImageView ivBaseBack;
    @BindView(R.id.sv_base_searchview)
    SearchView svBaseSearchview;
    @BindView(R.id.tv_base_setting)
    TextView tvBaseSetting;
    @BindView(R.id.ll_base_search_container)
    LinearLayout llBaseSearchContainer;
    @BindView(R.id.iv_home_history_delete)
    ImageView ivHomeHistoryDelete;

    public  List<String>  historyList = new ArrayList<>();
    private List<String> hotGoods = new ArrayList<>();
    public boolean hasHistory = true;
    private View hotWordsSearch;
    private YhFlowLayout yhFlowLayout;

    private List<String> historyWords = new ArrayList<>();
    private HistorySearchHotWordsRecycleAdapter adapter;
    private Gson gson;


    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_home_search_goods;
    }


    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    public void loadHotGoods(List<String> mHotGoods) {
        if (mHotGoods != null && mHotGoods.size() > 0) {
            hotGoods.addAll(mHotGoods);
            displayUI(hotGoods, flowLayout);
        }
    }

    @Override
    public void updateHistoryWords(List<String> historyWords) {
        if (historyWords != null && historyWords.size() > 0) {
            this.historyWords.addAll(historyWords);
            this.historyList = historyWords;
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        ivBaseBack.setVisibility(View.GONE);
        tvBaseSetting.setText("搜索");

        gson = new Gson();

        //初始化流式布局控件
        flowLayout.setSpace(DisplayUtils.dp2Px(this, 15), DisplayUtils.dp2Px(this, 10));
        flowLayout.setPadding(DisplayUtils.dp2Px(this, 13), DisplayUtils.dp2Px(this, 5),
                DisplayUtils.dp2Px(this, 5), DisplayUtils.dp2Px(this, 5));

    }


    @Override
    protected void initViewAndListener() {

        svBaseSearchview.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if ((actionId == 0 || actionId == 3)) {
//                    LogUtil.E(et_input_password.getText().toString().trim());//写你要做的事情
                    if (!TextUtils.isEmpty(svBaseSearchview.getText())) {
                        Intent intent = new Intent(SearchGoodsActivity.this, SearchDetailActivity.class);
                        intent.putExtra("SearchHotWords", svBaseSearchview.getText().toString().trim());
                        startActivity(intent);

                        //将搜索的词汇作为历史词汇进行存储
                        if (!historyList.contains(svBaseSearchview.getText().toString().trim())){
                            historyList.add(svBaseSearchview.getText().toString().trim());
                            String s = gson.toJson(historyList);
                            SpUtils.save("HISTORY_WORDS",s);
                        }

                    }
                    return true;
                }
                return false;

            }

        });




        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recylerViewSearchHistory.setLayoutManager(linearLayoutManager);

        adapter = new HistorySearchHotWordsRecycleAdapter(historyWords, this, hotGoods);
        recylerViewSearchHistory.setAdapter(adapter);

        adapter.setListener(new OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                svBaseSearchview.setText(historyWords.get(position));
                Intent intent = new Intent(SearchGoodsActivity.this, SearchDetailActivity.class);
                intent.putExtra("SearchHotWords", historyWords.get(position));
                startActivity(intent);
            }
        });


        presenter.getHotGoods();
    }

    private void displayUI(List<String> hotGoods, YhFlowLayout flowLayout) {
        for (int i = 0; i < hotGoods.size(); i++) {
            final String data = hotGoods.get(i);
            TextView tv = new TextView(this);
            tv.setText(data);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            tv.setGravity(Gravity.CENTER);
            int paddingy = DisplayUtils.dp2Px(this, 7);
            int paddingx = DisplayUtils.dp2Px(this, 6);
            tv.setPadding(paddingx, paddingy, paddingx, paddingy);
            tv.setClickable(false);

//            int shape = GradientDrawable.RECTANGLE;
//            int radius = DisplayUtils.dp2Px(this, 4);
//            int strokeWeight = DisplayUtils.dp2Px(this, 1);
//            int stokeColor = getResources().getColor(R.color.alg_cart_price_accounts);
//            int stokeColor2 = getResources().getColor(R.color.alg_cart_price_accounts);

//            GradientDrawable normalBg = DrawableUtils.getShape(shape, radius, strokeWeight, stokeColor, Color.WHITE);
//            GradientDrawable pressedBg = DrawableUtils.getShape(shape, radius, strokeWeight, stokeColor2, getResources().getColor(R.color.alg_cart_price_accounts));
//            StateListDrawable selector = DrawableUtils.getSelector(normalBg, pressedBg);
//            tv.setBackgroundDrawable(selector);
//            ColorStateList colorStateList = DrawableUtils.getColorSelector(getResources().getColor(R.color.alg_cart_price_accounts), getResources().getColor(R.color.alg_city_common_bg));
//            tv.setTextColor(colorStateList);
            tv.setTextColor(getResources().getColor(R.color.alg_city_gray));
            tv.setBackground(getResources().getDrawable(R.drawable.alg_common_shape_rectangle_grey));
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    svBaseSearchview.setText(data);
                    Intent intent = new Intent(SearchGoodsActivity.this, SearchDetailActivity.class);
                    intent.putExtra("SearchHotWords", data);
                    startActivity(intent);

                    //将搜索的词汇作为历史词汇进行存储
                    if (!historyList.contains(svBaseSearchview.getText().toString().trim())){
                        historyList.add(svBaseSearchview.getText().toString().trim());
                        String s = gson.toJson(historyList);
                        SpUtils.save("HISTORY_WORDS",s);
                    }

                }
            });
            flowLayout.addView(tv);
        }
    }


    @Override
    protected int activityThemeColor() {
        return getResources().getColor(R.color.alg_common_bg);
    }


    @OnClick({R.id.iv_base_back, R.id.tv_base_setting, R.id.iv_home_history_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_base_back:
                finish();
                break;
            case R.id.tv_base_setting:
                if (!TextUtils.isEmpty(svBaseSearchview.getText())) {
                    Intent intent = new Intent(SearchGoodsActivity.this, SearchDetailActivity.class);
                    intent.putExtra("SearchHotWords", svBaseSearchview.getText().toString().trim());
                    startActivity(intent);

                    //将搜索的词汇作为历史词汇进行存储
                    if (!historyList.contains(svBaseSearchview.getText().toString().trim())){
                        historyList.add(svBaseSearchview.getText().toString().trim());
                        String s = gson.toJson(historyList);
                        SpUtils.save("HISTORY_WORDS",s);
                    }

                }

                break;
            case R.id.iv_home_history_delete:

                SpUtils.remove("HISTORY_WORDS");
                historyWords.clear();
                historyList.clear();
                adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //将历史集合清空
        historyWords.clear();
        presenter.getHistoryGoods();

    }
}
