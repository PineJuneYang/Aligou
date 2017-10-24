package com.alg.ailigou.pages.personal.onlineorder;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.OrderDetailsDataModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.widget.PickerUtils;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.TimeUtils;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.alg.ailigou.pages.personal.onlineorder.adapter.OnlineOrderAdapter;
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
 * Created by 玖泞
 * on 2017/8/16
 * 此类或接口用于  线上订单
 */

public class OnLineOrderActivity extends BaseMvpActivity implements OnLineOrderContracts.View, OnClickListener {


    @Inject
    OnLineOrderPresenter presenter;
    @BindView(R.id.iv_base_back)
    ImageView ivBaseBack;
    @BindView(R.id.ll_base_back)
    LinearLayout llBaseBack;
    @BindView(R.id.tv_base_title)
    TextView tvBaseTitle;
    @BindView(R.id.tv_base_edit)
    TextView tvBaseEdit;
    @BindView(R.id.ll_base_edit)
    LinearLayout llBaseEdit;
    @BindView(R.id.iv_base_notice)
    ImageView ivBaseNotice;
    @BindView(R.id.ll_base_notice)
    LinearLayout llBaseNotice;
    @BindView(R.id.tv_time_begin)
    TextView tvTimeBegin;
    @BindView(R.id.tv_time_end)
    TextView tvTimeEnd;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.recyler_view)
    RecyclerView recylerView;
    @BindView(R.id.rl_base_title)
    RelativeLayout mRlBaseTitle;


    private TimePickerView timePickerView;
    private OptionsPickerView mPickerView;

    private List<OrderDetailsDataModel> orderDetailsDataModels = new ArrayList<>();
    private OnlineOrderAdapter adapter;
    private long beginTime;
    private long endTime;
    private boolean hasNext;
    private String status;//状态

    @Override
    protected void initBase() {
        super.initBase();
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerPersonalComponent.builder().personalModule(new PersonalModule(this)).build().inject(this);
    }


    @Override
    protected void afterContentView() {
        super.afterContentView();

        tvBaseTitle.setText("线上订单");
        mRlBaseTitle.setBackgroundResource(R.color.alg_common_bg_white);
        tvBaseTitle.setTextColor(Color.BLACK);
        ivBaseBack.setBackgroundResource(R.drawable.alg_common_black_left_arrow);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recylerView.setLayoutManager(layoutManager);

        adapter = new OnlineOrderAdapter(orderDetailsDataModels, this);
        recylerView.setAdapter(adapter);
        adapter.setOnHeaderClickListener(this);
        adapter.setListener(new OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {

            }
        });
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_online_order;
    }


    @OnClick({R.id.tv_time_begin, R.id.tv_time_end, R.id.tv_search, R.id.ll_base_back, R.id.tv_state})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tv_time_begin:

                timePickerView = PickerUtils.getTimePicker(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        timePickerView.dismiss();
                        beginTime = date.getTime();
                        String format = TimeUtils.format(date, TimeUtils.PATTERN3);
                        tvTimeBegin.setText(format);
                    }
                });
                timePickerView.show();

                break;

            case R.id.tv_time_end:

                timePickerView = PickerUtils.getTimePicker(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        timePickerView.dismiss();
                        endTime = date.getTime();
                        String format = TimeUtils.format(date, TimeUtils.PATTERN3);
                        tvTimeEnd.setText(format);
                    }
                });
                timePickerView.show();


                break;

            case R.id.tv_search:


                if (TextUtils.isEmpty(tvTimeBegin.getText().toString())) {
                    ToastUtils.showToast(this, "请填写查询订单的开始时间");
                    return;
                }
                if (TextUtils.isEmpty(tvTimeEnd.getText().toString())) {
                    ToastUtils.showToast(this, "请填写查询订单的结束时间");
                    return;
                }

                presenter.queryOnlineOrder(presenter.page, presenter.pageSize, beginTime, endTime, status);

                break;

            case R.id.ll_base_back:
                finish();
                break;
            case R.id.tv_state:
                showStatuPicker();
                break;
        }
    }


    @Override
    public void notify(PageModel<OrderDetailsDataModel> data) {
        if (data != null && data.dataList.size() > 0) {
            if (data.hasNext) {
                hasNext = data.hasNext;
            }
            presenter.page = data.page + 1;
            orderDetailsDataModels.addAll(data.dataList);
            adapter.notifyDataSetChanged();
        }

    }

    /**
     * 点击查看按钮,进入订单
     * @param view
     * @param position
     */
    @Override
    public void setOnClickListener(View view, int position) {
        switch (view.getId()) {
            case R.id.tv_online_order_view:

                break;
        }
    }


    private void showStatuPicker() {

        mPickerView = PickerUtils.getCommonPickerView(this, "处理状态", presenter.getTypes(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                status = presenter.getTypes().get(options1);
                mPickerView.dismiss();
            }
        });
        mPickerView.show();
    }
}

