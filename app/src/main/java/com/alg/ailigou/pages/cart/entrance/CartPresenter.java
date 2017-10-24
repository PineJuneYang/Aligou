package com.alg.ailigou.pages.cart.entrance;

import com.alg.ailigou.common.api.ailigou.AiligouApi;
import com.alg.ailigou.common.api.cart.CartApi;
import com.alg.ailigou.common.model.CartDataModel;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.PageModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

/**
 * AiligouApp
 * com.alg.ailigou.pages.cart.entrance
 * Created by Chris Chen on 2017/7/7 15:18.
 * Explain:购物车逻辑处理
 */

public class CartPresenter implements CartContracts.Presenter {
    private CartContracts.View view;

    public int  recommendPage =1;

    @Inject
    public CartPresenter() {
    }

    @Override
    public void bindView(CartContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }


    @Override
    public void loadCartdata() {
        CartApi.getCartInfoList(new NetCallback<CartDataModel>() {
            @Override
            protected void onComplete(NetResponse<CartDataModel> netResponse) {
                if (netResponse.isSuccess) {
                    view.cartDataNotify(netResponse.data);
                }
            }
        });
    }

    @Override
    public void loadRecommendedList(int type, int page, int pageSize) {
        AiligouApi.getRecommendList(type, page, pageSize, new NetCallback<PageModel<CommodityModel>>() {
            @Override
            protected void onComplete(NetResponse<PageModel<CommodityModel>> netResponse) {
                if (netResponse.isSuccess) {
                    view.recommendListNotify(netResponse.data);
                }
            }
        });
    }

    @Override
    public void moveGoodsToFav(final List<CommodityModel> goods) {
        final List<Long> ids = new ArrayList<>();
        for (int i = 0; i < goods.size(); i++) {
            if (goods.get(i).isSelect) {
                ids.add(goods.get(i).id);
            }
        }
        CartApi.moveGoodsToFav(ids, new NetCallback<Object>() {

            @Override
            protected void onComplete(NetResponse<Object> netResponse) {
                if (netResponse.isSuccess) {
                    view.successMove();
                    Iterator<CommodityModel> it = goods.iterator();
                    while (it.hasNext()) {
                        CommodityModel x = it.next();
                        if (x.isSelect) {
                            it.remove();
                        }
                    }
                    view.adapterNotif();
                } else {
                    view.failMove();
                }
            }
        });
    }


    @Override
    public void deleteCart(final List<CommodityModel> goods) {
        final List<Long> ids = new ArrayList<>();
        for (int i = 0; i < goods.size(); i++) {
            if (goods.get(i).isSelect) {
                ids.add(goods.get(i).id);
            }
        }
        CartApi.deleteCart(ids, new NetCallback<Object>() {

            @Override
            protected void onComplete(NetResponse<Object> netResponse) {
                if (netResponse.isSuccess) {
                    view.successDelete(ids);
                    Iterator<CommodityModel> it = goods.iterator();
                    while (it.hasNext()) {
                        CommodityModel x = it.next();
                        if (x.isSelect) {
                            it.remove();
                        }
                    }
                    view.adapterNotif();
                } else {
                    view.failDelete();
                }
            }
        });
    }
}
