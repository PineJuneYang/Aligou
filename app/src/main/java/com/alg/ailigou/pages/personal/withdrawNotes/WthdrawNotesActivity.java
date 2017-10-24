package com.alg.ailigou.pages.personal.withdrawNotes;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.widget.PickerUtils;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.TimeUtils;
import com.alg.ailigou.pages.home.ligouchangenotes.adapter.LigouChangeNotesAdapter;
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.bigkoo.pickerview.TimePickerView;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/8/7.
 * 此类或接口用于  提现记录
 */

public class WthdrawNotesActivity extends BaseMvpActivity {
    @BindView(R.id.iv_base_back)
    ImageView mIvBaseBack;
    @BindView(R.id.ll_base_back)
    LinearLayout mLlBaseBack;
    @BindView(R.id.tv_base_title)
    TextView mTvBaseTitle;
    @BindView(R.id.tv_base_edit)
    TextView mTvBaseEdit;
    @BindView(R.id.ll_base_edit)
    LinearLayout mLlBaseEdit;
    @BindView(R.id.iv_base_notice)
    ImageView mIvBaseNotice;
    @BindView(R.id.ll_base_notice)
    LinearLayout mLlBaseNotice;
    @BindView(R.id.tv_ligou_count)
    TextView mTvLigouCount;
    @BindView(R.id.tv_time_begin)
    TextView mTvTimeBegin;
    @BindView(R.id.tv_time_end)
    TextView mTvTimeEnd;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.tv_description)
    TextView mTvDescription;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.tv2)
    TextView mTv2;
    @BindView(R.id.tv3)
    TextView mTv3;
    @BindView(R.id.tv4)
    TextView mTv4;
    @BindView(R.id.recyler_view)
    RecyclerView mRecylerView;
    private LigouChangeNotesAdapter mAdapter;
    private List<String> datas;
    @Inject
    WthdrawNotesPresenter mPresenter;

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_home_ligou_change_overage;
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        mTv1.setText("申请时间");
        mTv2.setText("金额");
        mTv3.setText("手续费");
        mTv4.setText("提现状态");
        mTvBaseTitle.setText("提现记录");
        mTvLigouCount.setVisibility(View.GONE);
        mTvDescription.setVisibility(View.VISIBLE);
        initRecylerView();
    }

    private void initRecylerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecylerView.setLayoutManager(manager);
        mAdapter = new LigouChangeNotesAdapter(datas, this);
        mRecylerView.setAdapter(mAdapter);

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
                    }
                });
                timePickerView.show();
                break;
            case R.id.tv_search:

                break;
        }
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerPersonalComponent.builder().personalModule(new PersonalModule(this)).build().inject(this);
    }
}
