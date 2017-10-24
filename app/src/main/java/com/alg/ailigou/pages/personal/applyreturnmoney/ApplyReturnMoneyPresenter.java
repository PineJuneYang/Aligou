package com.alg.ailigou.pages.personal.applyreturnmoney;

import android.content.Context;
import android.util.Log;

import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.model.ReturnGoodsData;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;
import com.alg.ailigou.library.utils.luban.Luban;
import com.alg.ailigou.library.utils.luban.OnCompressListener;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by 海航
 * on 2017/8/18.
 * 此类或接口用于
 */

public class ApplyReturnMoneyPresenter implements ApplyReturnMoneyContrats.Presenter {
    @Inject
    public ApplyReturnMoneyPresenter() {
    }

    ApplyReturnMoneyContrats.View view;

    @Override
    public void bindView(ApplyReturnMoneyContrats.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }


//    public long orderId;//订单id
//    public long goodsId;//商品id
//    public double returnMoney;//退款金额
//    public int reasonCode;//退货原因
//    public String reasonDesc;//退货说明
//    public List<CommodityModel> goods;//退款的商品
//    public List<String> imgs;//退款的上传照片
//    public int  returnType;//退款类型  1表示仅退款  2:退货退款
    @Override
    public void submitData(ReturnGoodsData data) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderId",data.orderId);
        map.put("returnMoney",data.returnMoney);
        map.put("reasonCode",data.reasonCode);
        map.put("reasonDesc",data.reasonDesc);
        map.put("imgs",data.imgs);
        map.put("returnType",data.returnType);
        UserApi.returnGoods(map, new NetCallback<ReturnGoodsData>() {

            @Override
            protected void onComplete(NetResponse<ReturnGoodsData> netResponse) {
                if (netResponse.isSuccess){
                    view.toRefundDetails(netResponse.data);
                }
            }
        });

    }

    @Override
    public void compresPic(Context context, final List<String> imgs, File imgFile) {
        Luban.with(context)
                .load(imgFile)                     //传人要压缩的图片
                .setCompressListener(new OnCompressListener() { //设置回调
                    @Override
                    public void onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                    }

                    @Override
                    public void onSuccess(File file) {
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                        Log.d("dugu",file.getAbsolutePath());
                        if (imgs.size() == 6) {
                            if ("000".equals(imgs.get(0))) {
                                imgs.remove(0);
                            }
                        }
                        imgs.add(file.getAbsolutePath());
                        view.imgAdapterNotify();
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO 当压缩过程出现问题时调用
                    }
                }).launch();    //启动压缩
    }


}
