package com.alg.ailigou.pages.cart.entrance;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.inject.FragmentModule;
import com.alg.ailigou.common.model.BannerModel;
import com.alg.ailigou.common.model.CartDataModel;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.widget.CommonDialog;
import com.alg.ailigou.library.base.fragment.BaseMvpFragment;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.adapter.OnShoppingCartDataChangedListener;
import com.alg.ailigou.pages.cart.entrance.adapter.ShoppingCartAdapter;
import com.alg.ailigou.pages.cart.inject.DaggerCartComponent;
import com.alg.ailigou.pages.cart.newpush.NewGoodsPushActivity;
import com.alg.ailigou.pages.mall.details.CommodityDetailsActivity;
import com.alg.ailigou.pages.mall.orderdetails.OrderDetailsActivity;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.alg.ailigou.R.id.tv_all_select;
import static com.alg.ailigou.pages.cart.adapter.BaseVLayoutAdapter.BASE_ITEM_TYPE_CRAT;
import static com.alg.ailigou.pages.cart.adapter.BaseVLayoutAdapter.BASE_ITEM_TYPE_HEADER_BANNER;
import static com.alg.ailigou.pages.cart.adapter.BaseVLayoutAdapter.BASE_ITEM_TYPE_HEADER_TITLE;
import static com.alg.ailigou.pages.cart.adapter.BaseVLayoutAdapter.BASE_ITEM_TYPE_RECOMMEND;

/**
 * AiligouApp
 * com.alg.ailigou.pages.cart.entrance
 * Created by Chris Chen on 2017/7/7 15:18.
 * Explain:购物车页面
 */

public class CartFragment extends BaseMvpFragment implements CartContracts.View {
    @Inject
    CartPresenter cartPresenter;
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
    @BindView(R.id.recyler_view)
    RecyclerView mRecylerView;
    @BindView(R.id.img_select)
    ImageView mImgSelect;
    @BindView(R.id.tv_all_select)
    TextView mTvAllSelect;
    @BindView(R.id.ll_all_select)
    LinearLayout mLlAllSelect;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_price_accounts)
    TextView mTvPriceAccounts;
    @BindView(R.id.ll_price_accounts)
    LinearLayout mLlPriceAccounts;
    @BindView(R.id.tv_move_to_collection)
    TextView mTvMoveToCollection;
    @BindView(R.id.tv_delete)
    TextView mTvDelete;
    @BindView(R.id.ll_move_and_delete)
    LinearLayout mLlMoveAndDelete;
    @BindView(R.id.ll_bottom)
    LinearLayout mLlBottom;
    private int page = 1;

    private ShoppingCartAdapter adapterBanner;//第一个轮播图的 adapter
    private ShoppingCartAdapter adapterShoppingCart;//购物车 子条目的adapter
    private ShoppingCartAdapter adapterTitle;//精品推荐
    private ShoppingCartAdapter adapterCommend;//推荐列表
    private List<BannerModel> bnnerUrls = new ArrayList<>();//bnner 的图片路径
    private List<CommodityModel> mCartList = new ArrayList<>();//购物车列表
    private List<String> titles = new ArrayList<>();//推荐标头
    private List<CommodityModel> goodsCommends = new ArrayList<>();//商品推荐
    private VirtualLayoutManager layoutManager;
    private boolean hasNext;

    @Override
    protected BasePresenter getPresenter() {
        return cartPresenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_frg_cart;
    }

    @Override
    protected void initBase() {
        super.initBase();
        mLlBaseEdit.setVisibility(View.VISIBLE);
        mLlBaseNotice.setVisibility(View.VISIBLE);
        mLlBaseBack.setVisibility(View.GONE);
        mTvBaseEdit.setText("编辑");
        titles.add("");//仅仅为了显示推荐的东西
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        initRecylerView();
        initRecylerViewListener();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            cartPresenter.loadCartdata();
            cartPresenter.loadRecommendedList(1, page, 20);
        }
    }

    /**
     * 一些recylerView的监听
     */
    private void initRecylerViewListener() {
        adapterShoppingCart.setShoppingListener(new OnShoppingCartDataChangedListener() {
            @Override
            public void setOnAddGoodsCountListener(View view, int position) {
                mCartList.get(position).cartCount++;
                adapterShoppingCart.notifyItemChanged(position);
                countAllPrice();
            }

            @Override
            public void setOnDeleteGoodsCountListener(View view, int position) {
                if (mCartList.get(position).cartCount > 0) {
                    mCartList.get(position).cartCount--;
                    adapterShoppingCart.notifyItemChanged(position);
                    countAllPrice();
                }
            }

            @Override
            public void setOnGoodsSelectListener(View view, int position) {
                mCartList.get(position).isSelect = !mCartList.get(position).isSelect;
                countAllPrice();
                adapterShoppingCart.notifyItemChanged(position);
            }
        });
        adapterCommend.setListener(new OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                Intent intent = new Intent(getBaseContext(), CommodityDetailsActivity.class);
                intent.putExtra("id", goodsCommends.get(position).id);
                startActivity(intent);
            }
        });
        adapterShoppingCart.setListener(new OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                Intent intent = new Intent(getBaseContext(), CommodityDetailsActivity.class);
                intent.putExtra("id", mCartList.get(position).id);
                startActivity(intent);
            }
        });
        adapterBanner.setListener(new OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                Intent intent = new Intent(getBaseContext(), NewGoodsPushActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 计算总价格的
     */
    private int selectCount = 0;

    private void countAllPrice() {
        double price = 0;
        selectCount = 0;
        for (int i = 0; i < mCartList.size(); i++) {
            if (mCartList.get(i).isSelect) {
                price = price + (Double.parseDouble(mCartList.get(i).price) * mCartList.get(i).cartCount);
                selectCount = selectCount + 1;
            }
        }
        mTvPriceAccounts.setText("结算(" + selectCount + ")");
        mTvPrice.setText("合计:￥" + price);
    }

    private void initRecylerView() {
        //设置调用单个条目变化没有动画
        ((SimpleItemAnimator) mRecylerView.getItemAnimator()).setSupportsChangeAnimations(false);

        layoutManager = new VirtualLayoutManager(getActivity());

        // 同时内部会创建一个LayoutHelperFinder对象，用来后续的LayoutHelper查找
        mRecylerView.setLayoutManager(layoutManager);
        // 将VirtualLayoutManager绑定到recyclerView
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRecylerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 15);
        // 1. 设置Adapter列表（同时也是设置LayoutHelper列表）
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        // 2. 将上述创建的Adapter对象放入到DelegateAdapter.Adapter列表里
        adapterBanner = new ShoppingCartAdapter(bnnerUrls, getActivity(), null, BASE_ITEM_TYPE_HEADER_BANNER);
        adapterShoppingCart = new ShoppingCartAdapter(mCartList, getActivity(), null, BASE_ITEM_TYPE_CRAT);
        adapterTitle = new ShoppingCartAdapter(titles, getActivity(), null, BASE_ITEM_TYPE_HEADER_TITLE);
        GridLayoutHelper helper = new GridLayoutHelper(2);
        helper.setAutoExpand(false);
        adapterCommend = new ShoppingCartAdapter(goodsCommends, getActivity(), helper, BASE_ITEM_TYPE_RECOMMEND);


        adapters.add(adapterBanner);
        adapters.add(adapterShoppingCart);
        adapters.add(adapterTitle);
        adapters.add(adapterCommend);
        // 3. 创建DelegateAdapter对象 & 将layoutManager绑定到DelegateAdapter
        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager);

        // 4. 将DelegateAdapter.Adapter列表绑定到DelegateAdapter
        delegateAdapter.setAdapters(adapters);

        // 5. 将delegateAdapter绑定到recyclerView
        mRecylerView.setAdapter(delegateAdapter);

    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerCartComponent.builder().fragmentModule(new FragmentModule(this)).build().inject(this);
    }


    @OnClick({R.id.tv_base_title, R.id.ll_base_notice, R.id.ll_base_edit, R.id.ll_all_select, tv_all_select, R.id.tv_price, R.id.tv_price_accounts, R.id.tv_move_to_collection, R.id.tv_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_base_title:
                break;
            case R.id.ll_base_notice:
                break;
            case R.id.ll_base_edit:
                if ("编辑".equals(mTvBaseEdit.getText())) {
                    mTvBaseEdit.setText("完成");
                    mLlMoveAndDelete.setVisibility(View.VISIBLE);
                    mLlPriceAccounts.setVisibility(View.GONE);
                } else if ("完成".equals(mTvBaseEdit.getText())) {
                    mTvBaseEdit.setText("编辑");
                    mLlMoveAndDelete.setVisibility(View.GONE);
                    mLlPriceAccounts.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.ll_all_select://全选按钮
                mTvAllSelect.setSelected(!mTvAllSelect.isSelected());
                for (int i = 0; i < mCartList.size(); i++) {
                    mCartList.get(i).isSelect = mTvAllSelect.isSelected();
                }
                adapterShoppingCart.notifyDataSetChanged();
                break;

            case R.id.tv_price_accounts:
                startActivity(OrderDetailsActivity.class);
                break;
            case R.id.tv_move_to_collection:
                if (selectCount > 0) {
                    showDeleteDialog();
                }
                break;
            case R.id.tv_delete:
                if (selectCount > 0) {
                    cartPresenter.deleteCart(mCartList);
                }
                break;
        }
    }

    CommonDialog deleteDialog;

    public void showDeleteDialog() {
        deleteDialog = new CommonDialog(getBaseContext(), "是否删除这" + selectCount + "个商品么?");
        deleteDialog.show();
        deleteDialog.getSure().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartPresenter.moveGoodsToFav(mCartList);
            }
        });
        deleteDialog.getCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog.dismiss();
            }
        });
    }



    @Override
    public void adapterNotif() {
        if (adapterShoppingCart != null) {
            adapterShoppingCart.notifyDataSetChanged();
        }
    }

    @Override
    public void successDelete(List<Long> ids) {

    }

    @Override
    public void failDelete() {

    }

    @Override
    public void successMove() {

    }

    @Override
    public void failMove() {

    }


    @Override
    public void cartDataNotify(CartDataModel dataModel) {
        if (dataModel.mBannerModel != null) {
            bnnerUrls.clear();
            bnnerUrls.add(dataModel.mBannerModel);
            adapterBanner.notifyDataSetChanged();
        }
        if (dataModel.mCommodityModels != null) {
            if (dataModel.mCommodityModels.size() > 0) {//当购物车里面有东西时候,显示下面结算
                mLlBottom.setVisibility(View.VISIBLE);
            } else {
                mLlBottom.setVisibility(View.GONE);
            }
            mCartList.clear();
            mCartList.addAll(dataModel.mCommodityModels);
            adapterShoppingCart.notifyDataSetChanged();
        }
    }

    @Override
    public void recommendListNotify(PageModel<CommodityModel> model) {
        if (model.dataList != null) {
            if (page==1){
                goodsCommends.clear();
            }
            hasNext = model.hasNext;


            goodsCommends.addAll(model.dataList);
            adapterCommend.notifyDataSetChanged();
        }
    }


}
