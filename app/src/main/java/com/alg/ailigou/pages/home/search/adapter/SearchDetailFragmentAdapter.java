package com.alg.ailigou.pages.home.search.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.widget.AutoImageView;
import com.alg.ailigou.pages.cart.adapter.BaseHeadAndFootAdapter;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.home.search.callback.OnLoadMoreDataListener;
import com.alg.ailigou.pages.home.search.holder.SearchDetailHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 玖泞
 * on 2017/7/26
 * 此类或接口用于
 */

public class SearchDetailFragmentAdapter extends BaseHeadAndFootAdapter {



    private Context mContext;

    private List<CommodityModel> commodityModels = new ArrayList<>();
    private LayoutInflater inflater;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;






    public SearchDetailFragmentAdapter(Context context, List<CommodityModel> mCommodityModels) {
        super(mCommodityModels, context);
        this.mContext = context;
        this.commodityModels = mCommodityModels;
        inflater = LayoutInflater.from(context);


//        initRecycleViewLoadMore();

    }


//    private void initRecycleViewLoadMore() {
//        layoutManager = mRecyclerView.getLayoutManager();
//        itemCount = layoutManager.getItemCount();
//        if (layoutManager instanceof GridLayoutManager) {
//
//            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//
//                @Override
//                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                    super.onScrolled(recyclerView, dx, dy);
//                    lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
//                    if (!isLoading && itemCount <= (lastVisibleItemPosition + visibleThreshold)) {
//                        //不是刷新中
//
//                        if (mMoreDataListener != null) {
//                            mMoreDataListener.onLoadMore();
//                        }
//                        isLoading = true;
//
//                    }
//                }
//            });
//        }
//
//    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }



    @Override
    public int getItemCount() {
        return commodityModels.size();
    }

    @Override
    protected MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        return new SearchDetailHolder(inflater.inflate(R.layout.alg_item_search_detail, parent, false), listener, getOnClickListener() , mContext);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SearchDetailHolder) {
            SearchDetailHolder searchDetailHolder = (SearchDetailHolder) holder;
            searchDetailHolder.setData(commodityModels.get(position));
        }
    }


}
