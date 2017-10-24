package com.alg.ailigou.pages.classification.entrance;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.inject.FragmentModule;
import com.alg.ailigou.common.model.CommodityTypeModel;
import com.alg.ailigou.library.base.adapter.BaseRecyclerAdapter;
import com.alg.ailigou.library.base.fragment.BaseMvpFragment;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.classification.entrance.adapter.CommodityTypeAdapter;
import com.alg.ailigou.pages.classification.fragments.hot.HotFragment;
import com.alg.ailigou.pages.classification.fragments.normal.NormalFragment;
import com.alg.ailigou.pages.classification.inject.DaggerClassificationComponent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * AiligouApp
 * com.alg.ailigouapp.pages.classification.entrance
 * Created by Chris Chen on 2017/7/4 15:04.
 * Explain:分类
 */

public class ClassificationFragment extends BaseMvpFragment implements ClassificationContracts.View{
    @BindView(R.id.rv_classic_type_list)
    public RecyclerView rvClassicTypeList;
    @BindView(R.id.fl_commodity_container)
    public FrameLayout flCommodity;

    @Inject
    ClassificationPresenter presenter;

    private CommodityTypeAdapter commodityTypeAdapter;

    private List<CommodityTypeModel> commodityTypeList ;

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_frg_classification;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerClassificationComponent.builder().fragmentModule(new FragmentModule(this)).build().inject(this);
    }

    @Override
    protected void initViewAndListener() {
        initCommodityType();
    }

    /**
     * 初始化商品分类列表
     */
    private void initCommodityType() {
        rvClassicTypeList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        commodityTypeAdapter = new CommodityTypeAdapter(getContext());
        rvClassicTypeList.setAdapter(commodityTypeAdapter);
//        View headView = LayoutInflater.from(context).inflate(R.layout.alg_item_classification_commoditi_type,rvClassicTypeList,false);


        commodityTypeAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                if (position == 0) {
                    getFragmentManager().beginTransaction().replace(R.id.fl_commodity_container, new HotFragment()).commit();
                } else {
                    getFragmentManager().beginTransaction().replace(R.id.fl_commodity_container, NormalFragment.newInstance(commodityTypeList.get(position).id)).commit();
//                    getFragmentManager().beginTransaction().replace(R.id.fl_commodity_container, new NormalFragment()).commit();

                }
                for (int i=0;i<rvClassicTypeList.getChildCount();i++){
                    rvClassicTypeList.getChildAt(i).setBackgroundResource(R.color.alg_classification_commodity_type_bg_normal);
                    ((TextView)rvClassicTypeList.getChildAt(i).findViewById(R.id.tv_commodity_type_title)).setTextColor(Color.BLACK);
                }
                itemView.setBackgroundResource(R.color.alg_classification_commodity_type_bg_selected);
                ((TextView)itemView.findViewById(R.id.tv_commodity_type_title)).setTextColor(Color.WHITE);
            }
        });

        //(todo:test)需要判断创建
        getFragmentManager().beginTransaction().replace(R.id.fl_commodity_container, new HotFragment()).commit();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()){
            presenter.loadClassicList();
        }
    }

    @Override
    public void updateClassicList(List<CommodityTypeModel> commodityTypeList) {
        this.commodityTypeList = commodityTypeList;
        commodityTypeAdapter.updateData(commodityTypeList);
    }
}
