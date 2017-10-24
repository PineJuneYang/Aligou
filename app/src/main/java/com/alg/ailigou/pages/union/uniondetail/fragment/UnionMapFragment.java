package com.alg.ailigou.pages.union.uniondetail.fragment;

import android.util.Log;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.library.base.fragment.BaseMvpFragment;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

/**
 * Created by 海航
 * on 2017/7/27.
 * 此类或接口用于  展示商家的地图位置
 */

public class UnionMapFragment extends BaseMvpFragment {
    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_frg_union_map;
    }
    @Subscribe(
            tags = {
                    @Tag("UnionDetailActivity")
            }
    )
    public void getData(UnionModel mUnionModel) {
        Log.d("dugu", mUnionModel.toString());
    }
}
