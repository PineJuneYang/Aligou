package com.alg.ailigou.pages.personal.mycollection.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.NewsModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.common.utils.RefreshCompleteUtils;
import com.alg.ailigou.library.base.fragment.BaseMvpFragment;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.alg.ailigou.pages.personal.mycollection.adapter.CollectionCommodityAdapter;
import com.alg.ailigou.pages.personal.myfoot.adapter.MyFootNotesAdapter;
import com.alg.ailigou.pages.union.entrance.adapter.UniconAdapter;
import com.alg.ailigou.pages.union.uniondetail.UnionDetailActivity;
import com.alg.ailigou.selectcity.utils.ToastUtils;
import com.bumptech.glide.Glide;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.RefreshFrameLayout;

/**
 * Created by 玖泞
 * on 2017/8/8
 * 此类或接口用于
 */

public class MyCollectionFragment extends BaseMvpFragment implements MyCollectionFragmentContracts.View, OnClickListener {

    public static int COMMODITY_FRAGMENT = 0;
    public static int BUSINESS_FRAGMENT = 1;

    @BindView(R.id.iv_select)
    ImageView ivSelect;
    @BindView(R.id.rl_my_collection_fragment_all_choose_delete)
    RelativeLayout rlMyCollectionFragmentAllChooseDelete;
    @BindView(R.id.tv_my_collection_fragment_delete)
    TextView tvMyCollectionFragmentDelete;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.tv_sure)
    TextView tvSure;


    @BindView(R.id.ll_mycollection_empty)
    LinearLayout llMycollectionEmpty;
    @BindView(R.id.refresh_view)
    RefreshFrameLayout refreshView;


    private List<CommodityModel> commodityModels = new ArrayList<>();
    private List<UnionModel> mUnionModels = new ArrayList<>();
    private List<NewsModel> newsModels = new ArrayList<>();


    //记录需要删除的集合
    private List<Long> ids = new ArrayList<>();
    //记录所有收藏的商家id
    private List<Long> unionIds = new ArrayList<>();
    private List<Integer> integers = new ArrayList<>();

    @Inject
    MyCollectionFragmentPresenter presenter;


    private static MyCollectionFragment myCollectionFragment;
    @BindView(R.id.recyler_view)
    RecyclerView recylerView;

    private int position;
    private UniconAdapter uniconAdapter;
    private CollectionCommodityAdapter commodityAdapter;
    private MyFootNotesAdapter goodsAdapter;//这个是商品收藏列表的adapter(和足迹通用)


    private boolean allChosed = false;

    @Override
    protected void initBase() {
        super.initBase();
        Bundle arguments = getArguments();
        position = arguments.getInt("position");
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerPersonalComponent.builder().personalModule(new PersonalModule(getBaseActivity())).build().inject(this);
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        if (position == COMMODITY_FRAGMENT) {
            presenter.loadCommodity();
//            //这个是左边选择按钮的点击事件
//            goodsAdapter.setOnHeaderClickListener(new OnClickListener() {
//                @Override
//                public void setOnClickListener(View view, int position) {
//
//
//                    //// TODO: 2017/8/9 后面对数据如果是selected,将这数据的id和position进行保存到集合中,方便后面删除请求网络
//                }
//            });
//            //条目点击事件
//            goodsAdapter.setListener(new OnItemClickListener() {
//                @Override
//                public void setOnItemClickListener(View view, int position) {
//                    // TODO: 2017/8/8
//
////                    goodsAdapter.setEdit(true);
//
//                }
//            });

        } else if (position == BUSINESS_FRAGMENT) {
            presenter.loadBusiness("");


        }


        if (position == COMMODITY_FRAGMENT) {//商品列表
            recylerView.setLayoutManager(new LinearLayoutManager(context));
            //设置调用单个条目变化没有动画
            ((SimpleItemAnimator) recylerView.getItemAnimator()).setSupportsChangeAnimations(false);
            recylerView.setHasFixedSize(true);

            goodsAdapter = new MyFootNotesAdapter(commodityModels, context, true);
            recylerView.setAdapter(goodsAdapter);
            if (commodityModels == null || commodityModels.size() == 0) {
                //将recyleView隐藏
                recylerView.setVisibility(View.GONE);
                //将空的商家占位图显示
                llMycollectionEmpty.setVisibility(View.VISIBLE);
                Glide.with(context).load(R.drawable.alg_personal_nocollectionicon).into(ivEmpty);
            }
            goodsAdapter.setOnHeaderClickListener(this);


        } else if (position == BUSINESS_FRAGMENT) {//联盟商家列表
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
            recylerView.setLayoutManager(linearLayoutManager);
            uniconAdapter = new UniconAdapter(mUnionModels, getBaseContext());
            recylerView.setAdapter(uniconAdapter);
            uniconAdapter.setListener(new OnItemClickListener() {
                @Override
                public void setOnItemClickListener(View view, int position) {
                    Intent intent = new Intent(getBaseContext(), UnionDetailActivity.class);
                    startActivity(intent);
                }
            });
            if (mUnionModels == null || mUnionModels.size() == 0) {
                //将recyleView隐藏
                recylerView.setVisibility(View.GONE);
                //将空的商家占位图显示
                llMycollectionEmpty.setVisibility(View.VISIBLE);
                Glide.with(context).load(R.drawable.alg_personal_no_business).into(ivEmpty);
            }

        }
        refreshView.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                if (position == COMMODITY_FRAGMENT){
                    commodityModels.clear();
                    presenter.loadCommodity();
                }else {
                    mUnionModels.clear();
                    presenter.loadBusiness("");
                }
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // 默认实现，根据实际情况做改动
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
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
        return R.layout.alg_frg_personal_my_collection;
    }

    public static MyCollectionFragment newInstance(int position) {
        myCollectionFragment = new MyCollectionFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);

        myCollectionFragment.setArguments(bundle);
        return myCollectionFragment;
    }


    @Override
    public void updateCommodity(PageModel<CommodityModel> data) {
        if (data.dataList.size() > 0) {
            recylerView.setVisibility(View.VISIBLE);
            llMycollectionEmpty.setVisibility(View.GONE);
            commodityModels.addAll(data.dataList);

            goodsAdapter.notifyDataSetChanged();

        }

    }

    @Override
    public void notifyBusiness(PageModel<UnionModel> data) {
        if (data.dataList.size() > 0) {
            recylerView.setVisibility(View.VISIBLE);
            llMycollectionEmpty.setVisibility(View.GONE);
            mUnionModels.addAll(data.dataList);
            uniconAdapter.notifyDataSetChanged();

        }

    }

    @Override
    protected boolean hasBus() {
        return true;
    }

    @Override
    public void notifyContent(PageModel<NewsModel> dataList) {
        if (dataList != null && dataList.dataList.size() > 0) {
            newsModels.addAll(dataList.dataList);
//            newsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void successfulDeleteGoods() {
        ToastUtils.showToast(context,"删除商品成功~");


        Iterator<CommodityModel> iterator = commodityModels.iterator();
        while (iterator.hasNext()){
            CommodityModel model = iterator.next();
            if (model.isSelect){
                iterator.remove();
            }

        }


        goodsAdapter.notifyDataSetChanged();
        integers.clear();
    }

    @Override
    public void failDeleteGoods() {
        ToastUtils.showToast(context,"删除商品失败");
    }

    @Override
    public void successfulDeleteUnions() {

    }

    @Subscribe(
            tags = {
                    @Tag("MyCollectionActivity")
            }

    )
    public void getData(Object status) {
        String s = (String) status;
        if (position == COMMODITY_FRAGMENT) {
            if (s.equals("编辑")) {

                //设置为true 将所有的左侧的可勾选框显示出来
                goodsAdapter.setEdit(true);
                rlMyCollectionFragmentAllChooseDelete.setVisibility(View.VISIBLE);

            } else {
                goodsAdapter.setEdit(false);
                rlMyCollectionFragmentAllChooseDelete.setVisibility(View.GONE);
            }
        } else if (position == BUSINESS_FRAGMENT) {
            if (s.equals("清空")){


                for (UnionModel unionModel : mUnionModels){
                    unionIds.add(unionModel.id);
                }
                if (unionIds.size()>0){

                    presenter.clearCollectionBusiness(unionIds);
                }else{
                    ToastUtils.showToast(context,"收藏的商家为空~");
                }


            }



        }

    }


    @OnClick({R.id.iv_select, R.id.tv_my_collection_fragment_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_select:
                allChosed = !allChosed;

                if (allChosed) {
                    for (CommodityModel commodityModel : commodityModels){
                        commodityModel.isSelect=true;
                    }


                    ivSelect.setBackgroundResource(R.drawable.alg_cart_checked);
                }else {
                    for (CommodityModel commodityModel : commodityModels){
                        commodityModel.isSelect=false;
                    }
                    ivSelect.setBackgroundResource(R.drawable.alg_cart_choose);
                }

                goodsAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_my_collection_fragment_delete:
                //1,请求网络
                //2,成功,删除数据
                //3,更新UI
                //失败啥也不处理
                if (allChosed) {
                    //删除全部数据
                } else {
                    for (int i = 0; i < commodityModels.size(); i++) {
                        if (commodityModels.get(i).isSelect) {
                            ids.add(commodityModels.get(i).id);
                            Integer integer = new Integer(i);
                            integers.add(integer);
                        }
                    }
                    if (ids.size() > 0) {
                        //有删除的数据才删除
                        presenter.deleteCollectionCommidity(ids);
                    }
                }
//                successfulDeleteGoods();

                break;
        }
    }


    @Override
    public void setOnClickListener(View view, int position) {
        switch (view.getId()) {
            case R.id.ll_select:
                commodityModels.get(position).isSelect = !commodityModels.get(position).isSelect;

                if (!commodityModels.get(position).isSelect){
                    ivSelect.setBackgroundResource(R.drawable.alg_cart_choose);
                    goodsAdapter.notifyItemChanged(position);
                    allChosed = false;
                }else{
                    for (CommodityModel commodityModel : commodityModels){
                        if (!commodityModel.isSelect){
                            ivSelect.setBackgroundResource(R.drawable.alg_cart_choose);
                            goodsAdapter.notifyItemChanged(position);
                            allChosed  = false;
                            return;
                        }
                    }
                    allChosed  = true;
                    ivSelect.setBackgroundResource(R.drawable.alg_cart_checked);
                    goodsAdapter.notifyItemChanged(position);
                }
                break;
        }
    }


    @Override
    public void refreshComplete() {
        super.refreshComplete();
        RefreshCompleteUtils.refreshComplete(refreshView);
    }
}
