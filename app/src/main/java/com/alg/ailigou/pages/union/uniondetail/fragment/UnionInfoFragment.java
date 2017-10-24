package com.alg.ailigou.pages.union.uniondetail.fragment;

import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.UnionModel;
import com.alg.ailigou.library.base.fragment.BaseMvpFragment;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import butterknife.BindView;

/**
 * Created by 海航
 * on 2017/7/27.
 * 此类或接口用于  商户信息
 */

public class UnionInfoFragment extends BaseMvpFragment {
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_telnumber)
    TextView mTvTelnumber;
    @BindView(R.id.alg_business_scope)
    TextView mAlgBusinessScope;
    @BindView(R.id.tv_user_address)
    TextView mTvUserAddress;
    @BindView(R.id.tv_belong_address)
    TextView mTvBelongAddress;

    private UnionModel mUnionModel;

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_frg_union_info;
    }

    @Override
    protected boolean hasBus() {
        return true;
    }

    @Subscribe(
            tags = {
                    @Tag("UnionDetailActivity")
            }
    )
    public void getData(UnionModel mUnionModel) {
        mTvUserName.setText(mUnionModel.person);
        mTvTelnumber.setText(mUnionModel.telNumber);
        mTvUserAddress.setText(mUnionModel.address);
        mTvBelongAddress.setText(mUnionModel.belongAddress);
        mAlgBusinessScope.setText(mUnionModel.businessScope);
    }

}
