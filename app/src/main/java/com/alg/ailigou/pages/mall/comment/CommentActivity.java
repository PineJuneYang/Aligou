package com.alg.ailigou.pages.mall.comment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.api.home.HomeApi;
import com.alg.ailigou.common.consts.IntentKeys;
import com.alg.ailigou.common.model.CommentDataModel;
import com.alg.ailigou.common.model.CommentModel;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;
import com.alg.ailigou.common.widget.CustomRecyclerView;
import com.alg.ailigou.common.widget.CustomScrollView;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.MeasureUtils;
import com.alg.ailigou.pages.home.widget.YhFlowLayout;
import com.alg.ailigou.pages.mall.details.adapter.GoodsCommentAdapter;
import com.alg.ailigou.pages.mall.utils.MallUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * AiligouApp
 * com.alg.ailigou.pages.mall.comment
 * Created by Chris Chen on 2017/8/4 11:44.
 * Explain:商品评价
 */

public class CommentActivity extends BaseMvpActivity implements IntentKeys {
    @BindView(R.id.sv_mall_goods_details_comment_all_container)
    CustomScrollView svCommentContainer;//滚动控件
    @BindView(R.id.tv_mall_goods_details_comment_all_count)
    TextView tvCommentCount;//参与评论的人数
    @BindView(R.id.tv_mall_goods_details_comment_all_good_rate)
    TextView tvCommentGoodRate;//好评率
    @BindView(R.id.flow_mall_goods_details_comment_all_lables)
    YhFlowLayout flowCommentLabels;//评价标签
    @BindView(R.id.rv_mall_goods_details_comment_all)
    CustomRecyclerView rvComment;//所有评论
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

    private int page = 1, pageSize = 20;//分页
    private long goodsId;//商品Id
    private GoodsCommentAdapter goodsCommentAdapter;

    private List<CommentModel> commentModels = new ArrayList<>();

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_inc_mall_goods_comment_all  ;
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        rlBaseTitle.setBackgroundResource(R.color.alg_common_bg_white);

        tvBaseTitle.setText("全部评价");
        tvBaseTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP,17);
        tvBaseTitle.setTextColor(getResources().getColor(R.color.alg_common_bg_dark));
        ivBaseBack.setBackgroundResource(R.drawable.alg_home_commidity_detail_arrow);

    }

    @Override
    protected int activityThemeColor() {
        return getResources().getColor(R.color.alg_common_bg);
    }

    @Override
    protected void initViewAndListener() {
        //监听滚动
        svCommentContainer.setOnScrollToBottomLintener(new CustomScrollView.OnScrollToBottomListener() {
            @Override
            public void onScrollBottomListener(boolean isBottom) {
                if (isBottom) {
                    page++;
                    loadCommentPage();
                }
            }
        });
        goodsId = getIntent().getLongExtra(GOODS_ID, 2);
        goodsCommentAdapter = new GoodsCommentAdapter(this);
        rvComment.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvComment.setAdapter(goodsCommentAdapter);
        loadData();
        loadCommentPage();

    }

    /**
     * 加载评论数据
     */
    private void loadCommentPage() {
        NetCallback<PageModel<CommentModel>> callback = new NetCallback<PageModel<CommentModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<CommentModel>> netResponse) {
                if (netResponse.isSuccess) {
                    goodsCommentAdapter.loadMore(netResponse.data.dataList);
                }
            }
        };
        HomeApi.getCommentPage(goodsId, page, pageSize, callback);
    }

    /**
     * 加载数据
     */
    private void loadData() {
        NetCallback<CommentDataModel> callback = new NetCallback<CommentDataModel>() {
            @Override
            protected void onComplete(NetResponse<CommentDataModel> netResponse) {
                if (netResponse.isSuccess) {
                    updateData(netResponse.data);
                }
            }
        };
        HomeApi.getCommentData(goodsId, callback);
    }

    private void updateData(CommentDataModel commentData) {
        tvCommentCount.setText("共" + commentData.people + "人参与评论");
        tvCommentGoodRate.setText("好评率:" + commentData.goodRate * 100 + "%");
        MallUtils.setupFlowLayout(this, commentData.commentLabelList, flowCommentLabels, R.color.alg_city_white ,8,18);
        goodsCommentAdapter.updateData(commentData.commentPage.dataList);
    }


}
