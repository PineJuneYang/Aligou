package com.alg.ailigou.pages.cart.newpush;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.inject.FragmentModule;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.inject.DaggerCartComponent;
import com.alg.ailigou.pages.cart.newpush.adapter.NewPushAdapter;
import com.alg.ailigou.pages.home.search.callback.OnLoadMoreDataListener;
import com.alg.ailigou.selectcity.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/8/1
 * 此类或接口用于 新品推送的activity
 */

public class NewGoodsPushActivity extends BaseMvpActivity implements NewGoodsPushContracts.View, OnClickListener {


    @Inject
    NewGoodsPushPresenter presenter;
    @BindView(R.id.iv_base_back)
    ImageView ivBaseBack;
    @BindView(R.id.ll_base_back)
    LinearLayout llBaseBack;
    @BindView(R.id.tv_base_title)
    TextView tvBaseTitle;
    @BindView(R.id.iv_base_notice)
    ImageView ivBaseNotice;
    @BindView(R.id.ll_base_notice)
    LinearLayout llBaseNotice;
    @BindView(R.id.tv_base_edit)
    TextView tvBaseEdit;
    @BindView(R.id.ll_base_edit)
    LinearLayout llBaseEdit;
    @BindView(R.id.recyler_view_new_push)
    RecyclerView recylerViewNewPush;
    private boolean hasNext = false;

    private List<CommodityModel> commodityModels = new ArrayList<>();
    private NewPushAdapter adapter;

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void initBase() {
        super.initBase();
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerCartComponent.builder().fragmentModule(new FragmentModule(this)).build().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_cart_new_push;
    }


    @Override
    protected void afterContentView() {
        super.afterContentView();

        presenter.loadNewGoodsPushData();
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        tvBaseTitle.setText("新品推送");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recylerViewNewPush.setLayoutManager(linearLayoutManager);

        adapter = new NewPushAdapter(commodityModels, this);
        recylerViewNewPush.setAdapter(adapter);

        adapter.setOnClickListener(this);
        adapter.setLoadMoreListenter(recylerViewNewPush, new OnLoadMoreDataListener() {
            @Override
            public void onLoadMore() {
                if (hasNext) {
                    presenter.loadNewGoodsPushData();

                }
            }
        });

    }


    @Override
    public void update(PageModel<CommodityModel> data) {

        if (data != null && data.dataList.size() != 0) {
            //有数据
            commodityModels.addAll(data.dataList);
            adapter.notifyDataSetChanged();
            hasNext = data.hasNext;
            presenter.page = data.page + 1;

        }

        adapter.setLoading(false);


    }

    @Override
    public void setOnClickListener(View view, int position) {
        switch (view.getId()) {
            case R.id.tv_item_new_push_instant_buy:

                //点击立即购买的后续操作
                ToastUtils.showToast(this, "去购买");
                break;


        }
    }

    @Override
    protected int activityThemeColor() {
        return getResources().getColor(R.color.alg_common_bg);
    }



    @OnClick(R.id.ll_base_back)
    public void onViewClicked(View view) {
        switch (view.getId()){

            case R.id.ll_base_back:
                finish();
                break;

        }
    }


}
