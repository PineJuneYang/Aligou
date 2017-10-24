package com.alg.ailigou.pages.home.ligouoverage;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.LigouOverageModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.widget.PickerUtils;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.TimeUtils;
import com.alg.ailigou.mock.personal.PersonMockData;
import com.alg.ailigou.pages.home.inject.DaggerHomeComponent;
import com.alg.ailigou.pages.home.inject.HomeModule;
import com.alg.ailigou.pages.home.ligouchangenotes.adapter.LigouChangeNotesAdapter;
import com.alg.ailigou.pages.home.search.callback.OnLoadMoreDataListener;
import com.bigkoo.pickerview.TimePickerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/7/28.
 * 此类或接口用于 利购券兑换记录
 */

public class LigouOverageActivity extends BaseMvpActivity implements LigouOverageContracts.View {
    @Inject
    LigouOveragePresenter mPresenter;
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
    @BindView(R.id.tv_ligou_count)
    TextView mTvLigouCount;
    @BindView(R.id.tv_time_begin)
    TextView mTvTimeBegin;
    @BindView(R.id.tv_time_end)
    TextView mTvTimeEnd;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.recyler_view)
    RecyclerView mRecylerView;


    private LigouChangeNotesAdapter mAdapter;
    private long startTime;
    private long endTime;
    private boolean hasNext;
    private List<LigouOverageModel> mModels;

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_home_ligou_change_overage;
    }


    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        mTvBaseTitle.setText("利购券余额");
        mAdapter = new LigouChangeNotesAdapter(PersonMockData.getLigouOverageModels(), this, LigouChangeNotesAdapter.TYPE_OVERAGE);
        mRecylerView.setHasFixedSize(true);
        mRecylerView.setLayoutManager(new LinearLayoutManager(this));
        mRecylerView.setAdapter(mAdapter);
        mAdapter.setLoadMoreListenter(mRecylerView,new OnLoadMoreDataListener() {
                                          @Override
                                          public void onLoadMore() {

                                          }
                                      }
        );
    }

    TimePickerView timePickerView;

    @OnClick({R.id.ll_base_back, R.id.tv_time_begin, R.id.tv_time_end, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_base_back:
                finish();
                break;
            case R.id.tv_time_begin:
                timePickerView = PickerUtils.getTimePicker(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        timePickerView.dismiss();
                        String format = TimeUtils.format(date, TimeUtils.PATTERN3);
                        mTvTimeBegin.setText(format);
                        startTime = date.getTime();
                    }
                });
                timePickerView.show();
                break;
            case R.id.tv_time_end:
                timePickerView = PickerUtils.getTimePicker(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        timePickerView.dismiss();
                        String format = TimeUtils.format(date, TimeUtils.PATTERN3);
                        mTvTimeEnd.setText(format);
                        endTime = date.getTime();
                    }
                });
                timePickerView.show();
                break;
            case R.id.tv_search:
                mPresenter.loadData(startTime, endTime, pager, 20);
                break;
        }
    }


    @Override
    public void notify(PageModel<LigouOverageModel> data) {
        if (data.dataList != null) {
            hasNext = data.hasNext;
            if (mModels == null) {
                mModels = new ArrayList<>();
            }
            if (pager == 1) {
                mModels.clear();
            }
            pager = pager + 1;
            mModels.addAll(data.dataList);
        }
    }
}
