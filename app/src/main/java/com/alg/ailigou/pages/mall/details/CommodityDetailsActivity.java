package com.alg.ailigou.pages.mall.details;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.alg.ailigou.R;
import com.alg.ailigou.common.consts.IntentKeys;
import com.alg.ailigou.common.inject.ActivityModule;
import com.alg.ailigou.common.model.CommentDataModel;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.utils.StatusBarUtil;
import com.alg.ailigou.common.widget.CustomScrollView;
import com.alg.ailigou.common.widget.GoodsPopupWindow;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.adapter.BaseRecyclerAdapter;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.ImageUtils;
import com.alg.ailigou.library.utils.MessageUtils;
import com.alg.ailigou.pages.mall.comment.CommentActivity;
import com.alg.ailigou.pages.mall.details.adapter.GoodsAdapter;
import com.alg.ailigou.pages.mall.details.adapter.GoodsCommentAdapter;
import com.alg.ailigou.pages.mall.details.holder.GoodsDetailsUiViewHolder;
import com.alg.ailigou.pages.mall.inject.DaggerMallComponent;
import com.alg.ailigou.pages.mall.utils.MallUtils;
import com.alg.ailigou.selectcity.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.OnClick;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.details
 * Created by Chris Chen on 2017/7/12 17:54.
 * Explain:商品详情页
 */

public class CommodityDetailsActivity extends BaseMvpActivity implements IntentKeys, CommodityDetailsContracts.View {
    @Inject
    CommodityDetailsPresenter presenter;

    private GoodsDetailsUiViewHolder viewHolder;
    private GoodsCommentAdapter commentAdapter;//商品评论列表适配器
    private GoodsAdapter recommentAdapter;//推荐商品列表适配器

    private String goodsDetailHtmlUrl = "http://192.168.1.120/goods/index.html";//商品详情页模版URL
    private long goodsId;//商品id
    private GoodsPopupWindow cartPopupWindow;
    private GoodsPopupWindow buyPopupWindow;
    private CommodityModel goods;

    private List<String> propertys = new ArrayList<>(); //存放商品规格属性

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_mall_goods_details;
    }

//    @Override
//    protected int activityThemeColor() {
//        return getResources().getColor(R.color.alg_city_white);
//    }

    @Override
    protected void initStatueBar() {
        StatusBarUtil.setTranslucentForImageView(this, 0, null);
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerMallComponent.builder().activityModule(new ActivityModule(this)).build().inject(this);
    }

    @Override
    protected void createViewHolder(View view) {
        viewHolder = new GoodsDetailsUiViewHolder(view);
    }

    @Override
    protected void initViewAndListener() {
        //接收商品id
        goodsId = getIntent().getLongExtra(GOODS_ID, -1);
        viewHolder.tbGoodsDetailsToolBar.setAlpha(0f);
        initScrollView();
        initComment();
        initDetails();
        initRecomment();
        presenter.loadGoodsDetailsData(goodsId);
        presenter.loadCommentData(goodsId); //加载评论商品的
        presenter.loadRecommetPage();//加载推荐商品分页
        presenter.loadSpecsData(goodsId);
        cartPopupWindow = new GoodsPopupWindow(this);//加入购物车
        buyPopupWindow = new GoodsPopupWindow(this);//加入收藏夹
    }

    /**
     * 初始化商品评论
     */
    private void initComment() {
        commentAdapter = new GoodsCommentAdapter(this);
        viewHolder.rvComment.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        viewHolder.rvComment.setAdapter(commentAdapter);
    }

    /**
     * 初始化推荐商品列表
     */
    private void initRecomment() {
        recommentAdapter = new GoodsAdapter(this);
        viewHolder.rvRecomment.setLayoutManager(new GridLayoutManager(this, 2));
        viewHolder.rvRecomment.setAdapter(recommentAdapter);
        recommentAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent intent = new Intent(CommodityDetailsActivity.this, CommodityDetailsActivity.class);
                intent.putExtra(GOODS_ID, recommentAdapter.getItem(position).id);
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化整体滚动控件
     */
    private void initScrollView() {
        viewHolder.svGoodsDetailsContainer.setOnScrollChangeListener(new CustomScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                if (t < viewHolder.tbGoodsDetailsToolBar.getHeight()) {
                    viewHolder.tbGoodsDetailsToolBar.setVisibility(View.GONE);
                } else {
                    viewHolder.tbGoodsDetailsToolBar.setVisibility(View.VISIBLE);
                }
                if (t < 500) {
                    viewHolder.tbGoodsDetailsToolBar.setAlpha(t / 500f);
                }
            }
        });
    }

    /**
     * 初始化商品详情
     */
    private void initDetails() {
        WebSettings settings = viewHolder.webGoodsInfo.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);//设置能够解析Javascript
        settings.setDomStorageEnabled(true);//设置适应Html5的一些方法
        viewHolder.webGoodsInfo.setWebViewClient(new WebViewClient());
        viewHolder.webGoodsInfo.loadUrl(goodsDetailHtmlUrl + "?id=" + goodsId);//加载商品详情页
//        webGoodsInfo.loadUrl("http://m.baidu.com/?from=1010888p");
    }

    @Override
    public void updateGoodsDetailsData(CommodityModel goodsModel) {
        ImageUtils.load(this, goodsModel.imageUrl, viewHolder.ivGoodsImage);
        viewHolder.tvGoodsTitle.setText(goodsModel.title);
        viewHolder.tvGoodsOldPrice.setText("￥" + goodsModel.oldPrice);
        viewHolder.tvCurrentPrice.setText("￥"+goodsModel.price);
//        viewHolder.tvGoodsCheapTicket.setText("￥" + "不知道");
        viewHolder.tvGoodsCourier.setText("快递: " + goodsModel.courier+"元");
        viewHolder.tvGoodsSoldCount.setText("已售出" + goodsModel.soldCount + "件");
        viewHolder.tvGoodsGoodRate.setText(goodsModel.goodRate + "%好评率");
        viewHolder.tvCommentCount.setText("商品评价(" + goodsModel.commentCount + ")");
        this.goods = goodsModel;


    }

    @Override
    public void updateRecommetPage(PageModel<CommodityModel> goodsPage) {
        if (goodsPage.dataList != null && goodsPage.dataList.size() != 0) {
            if (presenter.page == 1) {
                recommentAdapter.updateData(goodsPage.dataList);
            } else {
                recommentAdapter.loadMore(goodsPage.dataList);
            }
        }
    }

    @Override
    public void updateSpecsData(List<String> property) {
        //
        if (property!=null&&property.size()!=0){
            propertys.addAll(property);
        }
    }


    @Override
    public void updateCommentLabelData(CommentDataModel data) {
        if (data!=null&&data.commentLabelList!=null){
            MallUtils.setupFlowLayout(this, data.commentLabelList, viewHolder.flowCommentLabels, R.color.alg_city_white, 8,18);
        }
        //此处设计只显示一条
        if (data != null && data.commentPage.dataList.size() != 0) {
            commentAdapter.updateData(data.commentPage.dataList.subList(0, 1));
        }
    }

    @Override
    public void successCollection() {
        ToastUtils.showToast(this,"添加购物车成功");
    }

    @OnClick({R.id.tv_goods_details_goods_info,
            R.id.tv_goods_details_goods_details,
            R.id.tv_goods_details_goods_comment,
            R.id.tv_goods_details_goods_recomment,
            R.id.iv_goods_details_tab_ask,
            R.id.iv_goods_details_tab_fav,
            R.id.tv_goods_details_tab_add_cart,
            R.id.tv_goods_details_tab_buy,
            R.id.tv_mall_goods_details_view_all_comment
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_goods_details_goods_info://顶部 商品基本信息
                uiScrollTo(1);
                break;
            case R.id.tv_goods_details_goods_details://商品详情
                uiScrollTo(2);
                break;
            case R.id.tv_goods_details_goods_comment://评论
                uiScrollTo(3);
                break;
            case R.id.tv_goods_details_goods_recomment://推荐
                uiScrollTo(4);
                break;
            case R.id.tv_mall_goods_details_view_all_comment://全部评价
                Intent intent = new Intent(this, CommentActivity.class);
                intent.putExtra(GOODS_ID, 2);
                startActivity(intent);
                break;
            case R.id.iv_goods_details_tab_ask://在线咨询
                MessageUtils.debug("在线咨询");
                break;
            case R.id.iv_goods_details_tab_fav://收藏
                presenter.addGoodsToFav(goodsId);
                MessageUtils.debug("收藏");
                break;
            case R.id.tv_goods_details_tab_add_cart://加入购物车
                MessageUtils.debug("加入购物车");
//                presenter.addGoodsToCart(goods);
                cartPopupWindow.show(getContentView(), goods,propertys);
                break;
            case R.id.tv_goods_details_tab_buy://立即购买
                MessageUtils.debug("立即购买");
                buyPopupWindow.show(getContentView(), goods, propertys);
                break;

            default:
                break;
        }
    }

    /**
     * 滚动到板块位置
     *
     * @param position
     */
    private void uiScrollTo(int position) {
        switch (position) {
            case 1://顶部 商品基本信息
                viewHolder.svGoodsDetailsContainer.smoothScrollTo(0, 0);//滚动到顶部：商品信息部分,同时让工具条背景透明
                viewHolder.tbGoodsDetailsToolBar.setVisibility(View.GONE);
                viewHolder.tbGoodsDetailsToolBar.setAlpha(0f);
                break;
            case 2://详情
                viewHolder.svGoodsDetailsContainer.smoothScrollTo(0, viewHolder.ivGroupDetails.getTop() - viewHolder.tbGoodsDetailsToolBar.getHeight());//滚动到详情位置
                viewHolder.tbGoodsDetailsToolBar.setVisibility(View.VISIBLE);
                viewHolder.tbGoodsDetailsToolBar.setAlpha(1f);
                break;
            case 3://评论
                viewHolder.svGoodsDetailsContainer.smoothScrollTo(0, viewHolder.ivGroupComment.getTop() - viewHolder.tbGoodsDetailsToolBar.getHeight());//滚动到评论位置
                viewHolder.tbGoodsDetailsToolBar.setVisibility(View.VISIBLE);
                viewHolder.tbGoodsDetailsToolBar.setAlpha(1f);
                break;
            case 4://推荐
                viewHolder.svGoodsDetailsContainer.smoothScrollTo(0, viewHolder.ivGroupRecomment.getTop() - viewHolder.tbGoodsDetailsToolBar.getHeight());//滚动到推荐位置
                viewHolder.tbGoodsDetailsToolBar.setVisibility(View.VISIBLE);
                viewHolder.tbGoodsDetailsToolBar.setAlpha(1f);
                break;
        }
    }


}
