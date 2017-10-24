package com.alg.ailigou.pages.home.ligouchangenotes;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.LigouExchangeModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.widget.PickerUtils;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.TimeUtils;
import com.alg.ailigou.pages.home.inject.DaggerHomeComponent;
import com.alg.ailigou.pages.home.inject.HomeModule;
import com.alg.ailigou.pages.home.ligouchangenotes.adapter.LigouChangeNotesAdapter;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.alg.ailigou.mock.personal.PersonMockData.getLigouExchangeModels;

/**
 * Created by 海航
 * on 2017/7/28.
 * 此类或接口用于 利购券兑换记录
 */

public class LigouChangeNotesActivity extends BaseMvpActivity implements LigouChangeNotesContracts.View {
    @Inject
    LigouChangeNotesPresenter mPresenter;
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
    @BindView(R.id.tv_time_begin)
    TextView mTvTimeBegin;
    @BindView(R.id.tv_time_end)
    TextView mTvTimeEnd;
    @BindView(R.id.tv_state)
    TextView mTvState;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.recyler_view)
    RecyclerView mRecylerView;
    private long timeStart;
    private long timeEnd;
    private int status;
    private List<LigouExchangeModel> models;
    private LigouChangeNotesAdapter adapter;


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
        return R.layout.alg_act_home_ligou_change_notes;
    }




    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        mTvBaseTitle.setText("利购券记录");
        adapter = new LigouChangeNotesAdapter(getLigouExchangeModels(), this);
        mRecylerView.setLayoutManager(new LinearLayoutManager(this));
        mRecylerView.setHasFixedSize(true);
        mRecylerView.setAdapter(adapter);
    }

    boolean hasNext;

    @Override
    public void notify(PageModel<LigouExchangeModel> pageModel) {
        if (pageModel.dataList != null) {
            hasNext = pageModel.hasNext;
            if (models == null) {
                models = new ArrayList<>();
            }
            if (pager == 1 && models.size() > 0) {
                models.clear();
            }
            models.addAll(pageModel.dataList);
            pager = pageModel.page + 1;
            adapter.notifyDataSetChanged();
        }
    }

    TimePickerView timePickerView;


    @OnClick({R.id.ll_base_back, R.id.tv_time_begin, R.id.tv_time_end, R.id.tv_state, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_base_back:
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
                showStatuPicker();
                break;
            case R.id.tv_search:
                mPresenter.loadData(timeStart, timeEnd, status, pager, 20);
                break;
        }
    }
    OptionsPickerView mPickerView;
    private void showStatuPicker() {
        mPickerView = PickerUtils.getCommonPickerView(this, "处理状态", mPresenter.getStatuList(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                status = options1;
                mPickerView.dismiss();
            }
        });
        mPickerView.show();
    }
}
