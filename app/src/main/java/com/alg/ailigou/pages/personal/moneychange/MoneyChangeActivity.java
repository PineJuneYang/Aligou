package com.alg.ailigou.pages.personal.moneychange;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommonTypeModel;
import com.alg.ailigou.common.model.MoneyChangeModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.widget.PickerUtils;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.TimeUtils;
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.alg.ailigou.pages.personal.moneychange.adapter.MoneyChangeAdapter;
import com.alg.ailigou.selectcity.utils.ToastUtils;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/8/29.
 * 此类或接口用于资金变动记录      这个布局可以直接使用  利购券兑换记录的
 */

public class MoneyChangeActivity extends BaseMvpActivity implements MoneyChangeContracts.View {
    @Inject
    MoneyChangePresenter mPresenter;
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
    @BindView(R.id.rl_base_title)
    RelativeLayout mRlBaseTitle;
    @BindView(R.id.textView3)
    TextView mTextView3;
    @BindView(R.id.tv_time_begin)
    TextView mTvTimeBegin;
    @BindView(R.id.tv_time_end)
    TextView mTvTimeEnd;
    @BindView(R.id.tv_state)
    TextView mTvState;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.tv1)
    TextView mTv1;
    @BindView(R.id.tv2)
    TextView mTv2;
    @BindView(R.id.tv3)
    TextView mTv3;
    @BindView(R.id.tv4)
    TextView mTv4;
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.recyler_view)
    RecyclerView mRecylerView;
    List<MoneyChangeModel> datas=new ArrayList<>();
    private MoneyChangeAdapter mAdapter;
    private long timeStart;
    private long timeEnd;

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void initInjector() {
        DaggerPersonalComponent.builder().personalModule(new PersonalModule(this)).build().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_online_order;
    }

    @Override
    public void notify(PageModel<MoneyChangeModel> datas) {
         if (datas.dataList!=null&&datas.dataList.size()>0){
             this.datas.addAll(datas.dataList);
             mAdapter.notifyDataSetChanged();
         }
    }


    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        initView();
        mAdapter = new MoneyChangeAdapter(datas, this);
        mRecylerView.setLayoutManager(new LinearLayoutManager(this));
        mRecylerView.setAdapter(mAdapter);
    }

    private void initView() {
        mRlBaseTitle.setBackgroundResource(R.color.alg_common_bg_white);
        mTvBaseTitle.setText("资金变动记录");
        mTvBaseTitle.setTextColor(Color.BLACK);
        mIvBaseBack.setBackgroundResource(R.drawable.alg_common_black_left_arrow);
    }

    TimePickerView timePickerView;

    @OnClick({R.id.ll_base_back, R.id.tv_time_begin, R.id.tv_time_end, R.id.tv_state, R.id.tv_search})
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
                        timeStart = date.getTime();
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
                        timeEnd = date.getTime();
                    }
                });
                timePickerView.show();
                break;
            case R.id.tv_state:
                mPresenter.getStatuList();
                break;
            case R.id.tv_search:
                if (timeEnd == 0 || timeStart == 0) {
                    ToastUtils.showToast(this, "请选择时间");
                    return;
                }
                if (timeEnd < timeStart) {
                    ToastUtils.showToast(this, "请正确选择时间");
                    return;
                }
                mPresenter.loadData(pager,20,timeStart,timeEnd,status);
                break;
        }
    }

    OptionsPickerView mPickerView;
    private int status;//处理状态

    public void showStatuPicker(final List<CommonTypeModel> list) {
        mPickerView = PickerUtils.getCommonPickerView(this, "处理状态", list, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                status = list.get(options1).type;
                mTvState.setText( list.get(options1).content);
                mPickerView.dismiss();
            }
        });
        mPickerView.show();
    }
}
