package com.alg.ailigou.pages.cart.adapter;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alg.ailigou.R;
import com.alg.ailigou.pages.cart.holder.MyBaseViewHolder;
import com.alg.ailigou.pages.home.search.callback.OnLoadMoreDataListener;

import java.util.List;

/**
 * Created by 海航
 * on 2017/7/11.
 * 此类或接口用于 给recylerview 添加头布局脚布局
 */

public abstract class BaseHeadAndFootAdapter<T> extends RecyclerView.Adapter<MyBaseViewHolder> {
    protected static final int BASE_ITEM_TYPE_HEADER = 0x111;
    protected static final int BASE_ITEM_TYPE_FOOTER = 0x222;
    protected static final int BASE_ITEM_TYPE_NORMAL = 0x333;
    private static final int LOAD_MORE = 0X444;
    protected List<T> datas;
    protected Context mContext;
    protected SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    protected SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();
    private RecyclerView.LayoutManager layoutManager;
    private int itemCount;

    public boolean isLoading;

    private int visibleThreshold = 1;

    public OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private OnItemClickListener listener;

    private OnClickListener mOnClickListener;


    private RecyclerView mRecyclerView;

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    private OnLoadMoreDataListener mMoreDataListener;

    public void setLoadMoreListenter(RecyclerView mRecyclerView, OnLoadMoreDataListener mMoreDataListener) {
        this.mRecyclerView = mRecyclerView;
        this.mMoreDataListener = mMoreDataListener;

        initRecycleViewLoadMore();

    }


    public BaseHeadAndFootAdapter(List<T> datas, Context context) {
        this.datas = datas;
        mContext = context;

    }

    private void initRecycleViewLoadMore() {
        if (mRecyclerView != null) {
            layoutManager = mRecyclerView.getLayoutManager();


            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                private int lastVisibleItemPosition;

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    itemCount = layoutManager.getItemCount();
//                    if (dy>0){
                    if (layoutManager instanceof GridLayoutManager) {
                        lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();

                    } else if (layoutManager instanceof LinearLayoutManager) {
                        lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    }
                    if (!isLoading && itemCount <= (lastVisibleItemPosition + visibleThreshold)) {
                        //不是刷新中
                        isLoading = true;
                        if (mMoreDataListener != null) {
                            if (mFootViews.size() == 0 || mFootViews.get(LOAD_MORE) == null) {
                                addFootView(LOAD_MORE, LayoutInflater.from(mContext).inflate(R.layout.alg_item_common_footview, recyclerView, false));
                            }
                            mMoreDataListener.onLoadMore();
                        }


                    }
                }
//                    }

            });

        }

    }


    @Override
    public MyBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyBaseViewHolder viewHolder = null;
        switch (viewType) {
            case BASE_ITEM_TYPE_NORMAL:
                viewHolder = onNormalViewHolder(parent, viewType, getListener());
                break;
            case BASE_ITEM_TYPE_HEADER:
                viewHolder = onHeadViewHolder(parent, viewType, getListener());
                break;
            case BASE_ITEM_TYPE_FOOTER:
                viewHolder = onFootViewHolder(parent, viewType, getListener());
                break;
        }
        return viewHolder;
    }

    /**
     * 一下三个生成的ViewHolder 都是根绝不同的type生成的
     *
     * @param parent
     * @param type
     * @param listener
     * @return
     */
    protected MyBaseViewHolder onFootViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        return new MyBaseViewHolder(mFootViews.get(LOAD_MORE), null);
    }

    /**
     * 头布局 处理
     *
     * @param parent
     * @param type
     * @param listener
     * @return
     */
    protected MyBaseViewHolder onHeadViewHolder(ViewGroup parent, int type, OnItemClickListener listener) {
        if (getHeadersCount() == 1) {
            return new MyBaseViewHolder(mHeaderViews.get(BASE_ITEM_TYPE_HEADER), null);
        } else {
            return null;
        }

    }

    /**
     * 注意这个只是正常情况下的adapter  如果添加头布局,需要重写onHeadViewHolder
     *
     * @param parent
     * @param type
     * @param listener
     * @return
     */
    protected abstract MyBaseViewHolder onNormalViewHolder(ViewGroup parent, int type, OnItemClickListener listener);


    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            return BASE_ITEM_TYPE_HEADER;
        } else if (isFooterViewPos(position)) {
            return BASE_ITEM_TYPE_FOOTER;
        } else {
            return BASE_ITEM_TYPE_NORMAL;
        }

    }


    protected int getRealItemCount() {
        return datas == null ? 0 : datas.size();
    }


    @Override
    public int getItemCount() {
        return getHeadersCount() + getFootersCount() + getRealItemCount();
    }


    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    private boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + getRealItemCount();
    }


    public void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
        notifyDataSetChanged();
    }

    public void addFootView(View view) {
        addFootView(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, view);

    }

    public void addFootView(int key, View view) {
        mFootViews.put(key, view);
        notifyDataSetChanged();
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFootViews.size();
    }

    @Override
    public void onViewAttachedToWindow(MyBaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (getItemViewType(position) == BASE_ITEM_TYPE_HEADER || getItemViewType(position) == BASE_ITEM_TYPE_FOOTER) {
                        return gridManager.getSpanCount();
                    } else {
                        return 1;
                    }

                }
            });
        }
    }

    /**
     * 取消加载更多的底部布局
     */
    public void setLoadMoreFinsih() {
        isLoading = false;
        if (getFootersCount() > 0) {
            if (mFootViews.get(LOAD_MORE) != null) {
                mFootViews.delete(LOAD_MORE);
            }
        }
    }

    public void setOnHeaderClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public OnClickListener getOnClickListener() {
        return mOnClickListener;
    }


}

