package com.alg.ailigou.selectcity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.selectcity.adapter.CityListAdapter;
import com.alg.ailigou.selectcity.adapter.ResultListAdapter;
import com.alg.ailigou.selectcity.bean.City;
import com.alg.ailigou.selectcity.db.DBManager;
import com.alg.ailigou.selectcity.utils.ToastUtils;
import com.alg.ailigou.selectcity.view.SlideBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/7/26.
 * 此类或接口用于 城市选择
 */

public class CitySelectActivity extends BaseMvpActivity {

    @BindView(R.id.iv_base_back)
    ImageView mIvBaseBack;
    @BindView(R.id.ll_base_back)
    LinearLayout mLlBaseBack;
    @BindView(R.id.tv_base_title)
    TextView mTvBaseTitle;
    @BindView(R.id.iv_base_notice)
    ImageView mIvBaseNotice;
    @BindView(R.id.ll_base_notice)
    LinearLayout mLlBaseNotice;
    @BindView(R.id.tv_base_edit)
    TextView mTvBaseEdit;
    @BindView(R.id.ll_base_edit)
    LinearLayout mLlBaseEdit;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.iv_search_clear)
    ImageView mIvSearchClear;
    @BindView(R.id.listview_all_city)
    ListView mListviewAllCity;
    @BindView(R.id.tv_letter_overlay)
    TextView mTvLetterOverlay;
    @BindView(R.id.side_letter_bar)
    SlideBar mSideLetterBar;
    @BindView(R.id.listview_search_result)
    ListView mListviewSearchResult;
    @BindView(R.id.empty_view)
    LinearLayout mEmptyView;
    @BindView(R.id.activity_main)
    LinearLayout mActivityMain;
    @BindView(R.id.rl_base_title)
    RelativeLayout rlBaseTitle;

    private CityListAdapter mCityAdapter;
    private ResultListAdapter mResultAdapter;
    private List<City> mAllCities;
    private DBManager dbManager;

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_home_city_select;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();


        rlBaseTitle.setBackgroundResource(R.color.alg_common_bg_white);

        mTvBaseTitle.setText("选择城市");
        mTvBaseTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        mTvBaseTitle.setTextColor(getResources().getColor(R.color.alg_common_bg_dark));
        mIvBaseBack.setBackgroundResource(R.drawable.alg_home_commidity_detail_arrow);


        dbManager = new DBManager(this);
        dbManager.copyDBFile();
        mAllCities = dbManager.getAllCities();
        mLlBaseNotice.setVisibility(View.GONE);
        mLlBaseEdit.setVisibility(View.GONE);
        mSideLetterBar.setOverlay(mTvLetterOverlay);
        mCityAdapter = new CityListAdapter(this, mAllCities);
        mResultAdapter = new ResultListAdapter(this, mAllCities);
        mListviewAllCity.setAdapter(mCityAdapter);
        mListviewSearchResult.setAdapter(mResultAdapter);
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        mSideLetterBar.setOnLetterChangedListener(new SlideBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                int position = mCityAdapter.getLetterPosition(letter);
                mListviewAllCity.setSelection(position);
            }
        });
        //返回结果后的listview点击事件
        mListviewSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                back(mResultAdapter.getItem(position).getName());
            }
        });
        //默认城市包括上面热门城市的显示的点击事件
        mCityAdapter.setOnCityClickListener(new CityListAdapter.OnCityClickListener() {
            @Override
            public void onCityClick(String name) {
                back(name);
            }

            @Override
            public void onLocateClick() {

            }
        });
        //搜索
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String keyword = s.toString();
                if (TextUtils.isEmpty(keyword)) {
                    mIvSearchClear.setVisibility(View.GONE);
                    mEmptyView.setVisibility(View.GONE);
                    mListviewSearchResult.setVisibility(View.GONE);
                } else {
                    mIvSearchClear.setVisibility(View.GONE);
                    mListviewSearchResult.setVisibility(View.VISIBLE);
                    List<City> result = dbManager.searchCity(keyword);
                    if (result == null || result.size() == 0) {
                        mEmptyView.setVisibility(View.GONE);
                    } else {
                        mEmptyView.setVisibility(View.GONE);
                        mResultAdapter.changeData(result);
                    }
                }
            }
        });
    }

    @OnClick({R.id.ll_base_back, R.id.iv_search_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_base_back:
                finish();
                break;
            case R.id.iv_search_clear:
                mEtSearch.setText("");
                break;
        }
    }

    private void back(String city) {
        ToastUtils.showToast(this, "点击的城市：" + city);
    }


}
