package com.alg.ailigou.pages.classification.fragments.normal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alg.ailigou.R;
import com.alg.ailigou.common.inject.FragmentModule;
import com.alg.ailigou.common.model.CommodityTypeModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.library.base.fragment.BaseMvpFragment;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.classification.fragments.adapter.NormalAdapter;
import com.alg.ailigou.pages.classification.inject.DaggerClassificationComponent;
import com.alg.ailigou.pages.home.goodslist.GoodsListActivity;
import com.alg.ailigou.pages.home.search.callback.OnLoadMoreDataListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * AiligouApp
 * com.alg.ailigou.pages.classification.fragments.normal
 * Created by Chris Chen on 2017/7/13 09:12.
 * Explain:一般商品分类页面
 */

public class NormalFragment extends BaseMvpFragment implements NormalContracts.View {
    @Inject
    NormalPresenter presenter;

    public static NormalFragment normalFragment;
    @BindView(R.id.recyler_view_fragment_normal)
    RecyclerView mRecycleView;

    private int typeId;
    private NormalAdapter adapter;


    private List<CommodityTypeModel> commodityTypeModels = new ArrayList<>();


    private boolean hasNext;


    public static NormalFragment newInstance(int typeId) {
        normalFragment = new NormalFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("typeId", typeId);
        normalFragment.setArguments(bundle);
        return normalFragment;
    }


    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }


    @Override
    protected void initBase() {
        super.initBase();
        Bundle arguments = getArguments();
        typeId = arguments.getInt("typeId");
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_frg_classification_type_normal;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerClassificationComponent.builder().fragmentModule(new FragmentModule(this)).build().inject(this);
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        presenter.loadClassificationList(typeId);
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(gridLayoutManager);
        adapter = new NormalAdapter(commodityTypeModels, context);
        mRecycleView.setAdapter(adapter);

        adapter.setListener(new OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                Intent  intent = new Intent(context, GoodsListActivity.class);

                intent.putExtra("classification",commodityTypeModels.get(position).title);
                startActivity(intent);

            }
        });

        adapter.setLoadMoreListenter(mRecycleView, new OnLoadMoreDataListener() {
            @Override
            public void onLoadMore() {
                if (hasNext){
                    presenter.loadClassificationList(typeId);
                }
            }
        });


    }

    @Override
    public void updateFashionCommodityData(PageModel<CommodityTypeModel> data) {

        commodityTypeModels.addAll(data.dataList);
        hasNext = data.hasNext;
        presenter.page = data.page + 1;
        adapter.setLoading(false);
        adapter.notifyDataSetChanged();

    }
}






