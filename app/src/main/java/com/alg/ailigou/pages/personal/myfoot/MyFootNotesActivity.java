package com.alg.ailigou.pages.personal.myfoot;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.widget.CommonDialog;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.alg.ailigou.pages.personal.myfoot.adapter.MyFootNotesAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/8/8.
 * 此类或接口用于  我的足迹
 */

public class MyFootNotesActivity extends BaseMvpActivity implements MyFootNotesContracts.View {
    @Inject
    MyFootNotesPresenter mPresenter;


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
    @BindView(R.id.recyler_view)
    RecyclerView mRecylerView;
    @BindView(R.id.iv_empty)
    ImageView mIvEmpty;
    @BindView(R.id.tv_desc)
    TextView mTvDesc;
    @BindView(R.id.tv_sure)
    TextView mTvSure;
    @BindView(R.id.ll_mycollection_empty)
    LinearLayout mLlEmpty;
    private CommonDialog mDialog;

    private List<CommodityModel> datas;
    private MyFootNotesAdapter mAdapter;

    @Override
    protected void initBase() {
        super.initBase();
        datas = new ArrayList<>();
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerPersonalComponent.builder().personalModule(new PersonalModule(this)).build().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_my_foot;
    }

    @Override
    protected void initViewAndListener() {
        mTvBaseTitle.setText("我的足迹");
        mTvBaseEdit.setText("清空");
        mLlBaseEdit.setVisibility(View.VISIBLE);
        super.initViewAndListener();
        mAdapter = new MyFootNotesAdapter(datas, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecylerView.setHasFixedSize(true);
        mRecylerView.setLayoutManager(manager);
        mRecylerView.setAdapter(mAdapter);
        mPresenter.getListData(pager, 20);
    }

    @OnClick({R.id.ll_base_back, R.id.ll_base_edit, R.id.tv_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_base_back:
                finish();
                break;
            case R.id.ll_base_edit:
                mDialog = new CommonDialog(this, "确定要清空历史浏览足迹吗?");
                mDialog.show();
                mDialog.getSure().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                        mPresenter.deleteAllData();
                    }
                });
                mDialog.getCancel().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });
                break;
            case R.id.tv_sure:
                break;
        }
    }

    @Override
    public void notify(PageModel<CommodityModel> pageModel) {
        mRecylerView.setVisibility(View.VISIBLE);
        mLlEmpty.setVisibility(View.GONE);
        if (pageModel.page==1){
            datas.clear();
        }
        pager=pageModel.page+1;
        datas.addAll(pageModel.dataList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setViewForEmpty() {
        datas.clear();
        mAdapter.notifyDataSetChanged();
        mRecylerView.setVisibility(View.GONE);
        mLlEmpty.setVisibility(View.VISIBLE);
    }


}
