package com.alg.ailigou.pages.personal.ligoudetails;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.model.LigouDetailDataModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;
import com.alg.ailigou.common.utils.StatusBarUtil;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.pages.home.ligouchangenotes.LigouChangeNotesActivity;
import com.alg.ailigou.pages.home.ligouoverage.LigouOverageActivity;
import com.alg.ailigou.pages.personal.commonwebview.CommonWebviewActivity;
import com.alg.ailigou.pages.personal.consts.PersonalConsts;
import com.alg.ailigou.pages.personal.withdraw.WthdrawActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 海航
 * on 2017/8/4.
 * 此类或接口用于  首页个人中心利购券 点进去之后进入的界面
 */

public class LigouDetailsActivity extends BaseMvpActivity {
    @BindView(R.id.tv_ligou_count)
    TextView mTvLigouCount;
    @BindView(R.id.tv_gift_count)
    TextView mTvGiftCount;
    @BindView(R.id.relative_withdraw)
    RelativeLayout mRelativeWithdraw;
    @BindView(R.id.relative_gift_notes)
    RelativeLayout mRelativeGiftNotes;
    @BindView(R.id.relative_ligou_exchange)
    RelativeLayout mRelativeLigouExchange;
    @BindView(R.id.relative_ligou_exchange_rule)
    RelativeLayout mRelativeLigouExchangeRule;
    private double withdrawOverage;

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_ligou_details;
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        getData();
    }

    @OnClick({R.id.relative_withdraw, R.id.relative_gift_notes, R.id.relative_ligou_exchange, R.id.relative_ligou_exchange_rule})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.relative_withdraw:
                startActivity(WthdrawActivity.class);
                break;
            case R.id.relative_ligou_exchange_rule://兑换规则
                Intent intent = new Intent(this, CommonWebviewActivity.class);
                intent.putExtra("type", PersonalConsts.CHANGE_RULE);
                startActivity(intent);
                break;
            case R.id.relative_gift_notes:
                startActivity(LigouOverageActivity.class);
                break;
            case R.id.relative_ligou_exchange:
                startActivity(LigouChangeNotesActivity.class);
                break;
        }
    }


    @OnClick()
    public void onViewClicked() {
    }

    @Override
    protected void initStatueBar() {
        StatusBarUtil.setTranslucentForImageView(this,0,null);
    }

    /**
     * 拿数据
     */
    public void getData() {
        UserApi.getLigouDetailsData(new NetCallback<LigouDetailDataModel>() {
            @Override
            protected void onComplete(NetResponse<LigouDetailDataModel> netResponse) {
                if (netResponse.isSuccess) {
                    //withdrawOverage = netResponse.data.withdrawOverage;
                }
            }

        });
    }
}
