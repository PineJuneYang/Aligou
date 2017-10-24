package com.alg.ailigou.pages.cart.entrance;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.inject.FragmentModule;
import com.alg.ailigou.common.model.CartDataModel;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.common.widget.CommonDialog;
import com.alg.ailigou.library.base.fragment.BaseMvpFragment;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.adapter.OnShoppingCartDataChangedListener;
import com.alg.ailigou.pages.cart.entrance.adapter.CartAdapter;
import com.alg.ailigou.pages.cart.entrance.adapter.RecommendAdapter;
import com.alg.ailigou.pages.cart.inject.DaggerCartComponent;
import com.alg.ailigou.selectcity.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 玖泞
 * on 2017/9/18
 * 此类或接口用于
 */

public class AiligouCartFragment extends BaseMvpFragment implements CartContracts.View {

    @Inject
    CartPresenter presenter;
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
    @BindView(R.id.rl_base_title)
    RelativeLayout rlBaseTitle;
    @BindView(R.id.iv_goods)
    ImageView ivGoods;
    @BindView(R.id.recyler_view_cart)
    RecyclerView recylerViewCart;
    @BindView(R.id.tv_recommend_title)
    TextView tvRecommendTitle;
    @BindView(R.id.recyler_view_recommend)
    RecyclerView recylerViewRecommend;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.img_select)
    ImageView imgSelect;
    @BindView(R.id.tv_all_select)
    TextView tvAllSelect;
    @BindView(R.id.ll_all_select)
    LinearLayout llAllSelect;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_price_accounts)
    TextView tvPriceAccounts;
    @BindView(R.id.ll_price_accounts)
    LinearLayout llPriceAccounts;
    @BindView(R.id.tv_move_to_collection)
    TextView tvMoveToCollection;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.ll_move_and_delete)
    LinearLayout llMoveAndDelete;
    @BindView(R.id.ll_bottom)
    RelativeLayout llBottom;
    @BindView(R.id.ll_recommend)
    LinearLayout llRecommend;


    private List<CommodityModel> mCartList = new ArrayList<>();//购物车列表

    private List<CommodityModel> goodsCommends = new ArrayList<>();//商品推荐
    private CartAdapter cartAdapter;
    private boolean hasNext;
    private RecommendAdapter recommendAdapter;
    private int selectCount;
    private CommonDialog commonDialog;

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
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_frg_algcart;
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        llBaseEdit.setVisibility(View.VISIBLE);
        llBaseNotice.setVisibility(View.VISIBLE);
        llBaseBack.setVisibility(View.GONE);
        tvBaseTitle.setText("购物车");
        tvBaseEdit.setText("编辑");
        initCartRecycleView();
        initRecommendRecycleView();

    }

    private void initRecommendRecycleView() {
        //
        recylerViewRecommend.setNestedScrollingEnabled(false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recylerViewRecommend.setLayoutManager(gridLayoutManager);
        recommendAdapter = new RecommendAdapter(goodsCommends, context);
        recylerViewRecommend.setAdapter(recommendAdapter);


        recommendAdapter.setListener(new OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {

            }
        });


    }

    private void initCartRecycleView() {

        //初始化购物车的recycleview
        recylerViewCart.setNestedScrollingEnabled(false);
        recylerViewCart.setLayoutManager(new LinearLayoutManager(context));
        cartAdapter = new CartAdapter(mCartList, context);

        recylerViewCart.setAdapter(cartAdapter);
        cartAdapter.setShoppingCartDataChangedListener(new OnShoppingCartDataChangedListener() {
            @Override
            public void setOnAddGoodsCountListener(View view, int position) {
                mCartList.get(position).cartCount++;
                if (mCartList.get(position).isSelect){
                    countAllPrice();
                }
                cartAdapter.notifyDataSetChanged();
            }

            @Override
            public void setOnDeleteGoodsCountListener(View view, int position) {
                if (mCartList.get(position).cartCount > 0) {
                    mCartList.get(position).cartCount--;
                    cartAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void setOnGoodsSelectListener(View view, int position) {
                mCartList.get(position).isSelect =  !mCartList.get(position).isSelect;
                countAllPrice();
                cartAdapter.notifyDataSetChanged();
            }
        });


    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {


        }
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        presenter.loadCartdata();
        presenter.loadRecommendedList(1, presenter.recommendPage, 20);

    }


    @Override
    public void cartDataNotify(CartDataModel dataModel) {
        if (dataModel != null) {
            if (dataModel.mBannerModel != null) {
                ImageLoadUtils.load(context, dataModel.mBannerModel.image, ivGoods);
            }
            if (dataModel.mCommodityModels != null && dataModel.mCommodityModels.size() > 0) {
                if (mCartList.size() > 0) {
                    mCartList.clear();
                }
                mCartList.addAll(dataModel.mCommodityModels);
                cartAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void recommendListNotify(PageModel<CommodityModel> commodityModelPageModel) {
        if (commodityModelPageModel != null && commodityModelPageModel.dataList.size() > 0) {

            hasNext = commodityModelPageModel.hasNext;
            if (hasNext) {
                presenter.recommendPage = commodityModelPageModel.page + 1;
            }

            goodsCommends.addAll(commodityModelPageModel.dataList);

            recommendAdapter.notifyDataSetChanged();

        }

    }




    @Override
    public void adapterNotif() {
        if (cartAdapter!=null){
            cartAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void successDelete(List<Long> ids) {

        ToastUtils.showToast(context,"删除商品成功");
        if (commonDialog!=null&&commonDialog.isShowing()){
            commonDialog.dismiss();
        }
    }

    @Override
    public void failDelete() {

    }

    @Override
    public void successMove() {
        ToastUtils.showToast(context,"收藏商品成功～");
    }

    @Override
    public void failMove() {
        ToastUtils.showToast(context,"收藏商品失败成功～");
    }


    @OnClick({R.id.ll_base_edit, R.id.ll_all_select, R.id.tv_delete, R.id.tv_move_to_collection})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_base_edit:
                if ("编辑".equals(tvBaseEdit.getText().toString().trim())) {
                    tvBaseEdit.setText("完成");

                    llRecommend.setVisibility(View.GONE);
                    recylerViewRecommend.setVisibility(View.GONE);

                    llPriceAccounts.setVisibility(View.GONE);
                    llMoveAndDelete.setVisibility(View.VISIBLE);
                } else if ("完成".equals(tvBaseEdit.getText().toString().trim())) {
                    tvBaseEdit.setText("编辑");
                    llRecommend.setVisibility(View.VISIBLE);
                    recylerViewRecommend.setVisibility(View.VISIBLE);

                    llPriceAccounts.setVisibility(View.VISIBLE);
                    llMoveAndDelete.setVisibility(View.GONE);

                }

                break;
            case R.id.ll_all_select:
                tvAllSelect.setSelected(!tvAllSelect.isSelected());
                if (tvAllSelect.isSelected()){
                    imgSelect.setImageDrawable(context.getResources().getDrawable(R.drawable.alg_cart_checked));
                }else{
                    imgSelect.setImageDrawable(context.getResources().getDrawable(R.drawable.alg_cart_choose));
                }
                for (CommodityModel commodityModel : mCartList) {
                    commodityModel.isSelect = tvAllSelect.isSelected();

                }
                cartAdapter.notifyDataSetChanged();

                break;

            case R.id.tv_delete:
                if (selectCount>0){
                    showDeleteDialog();
                }else {
                    ToastUtils.showToast(context,"删除商品不能为空");
                }
                break;

            case R.id.tv_move_to_collection:
                if (selectCount>0){
                    presenter.moveGoodsToFav(mCartList);
                }else{
                    ToastUtils.showToast(context,"请选择收藏的商品");
                }
                break;
        }
    }

    private void countAllPrice() {
        double price = 0;
        selectCount = 0;
        for (int i = 0; i < mCartList.size(); i++) {
            if (mCartList.get(i).isSelect) {
                price = price + (Double.parseDouble(mCartList.get(i).price) * mCartList.get(i).cartCount);
                selectCount = selectCount + 1;
            }
        }
        tvPriceAccounts.setText("结算(" + selectCount + ")");
        tvPrice.setText("合计:￥" + price);
    }

    public void showDeleteDialog() {
        commonDialog = new CommonDialog(getBaseContext(), "是否删除这" + selectCount + "个商品么?");
        commonDialog.show();
        commonDialog.getSure().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deleteCart(mCartList);
            }
        });
        commonDialog.getCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commonDialog.dismiss();
            }
        });
    }

}
