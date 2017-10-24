package com.alg.ailigou.mock.cart;

import com.alg.ailigou.R;
import com.alg.ailigou.common.model.BannerModel;
import com.alg.ailigou.common.model.CityModel;
import com.alg.ailigou.common.model.CommodityModel;
import com.alg.ailigou.common.model.CommodityTypeModel;
import com.alg.ailigou.common.model.HomeCommodityTypeModel;
import com.alg.ailigou.common.model.OrderDetailsDataModel;
import com.alg.ailigou.common.model.ReturnGoodsData;
import com.alg.ailigou.common.model.ShareModel;
import com.alg.ailigou.common.model.ShippingAddressModel;
import com.alg.ailigou.common.model.UnionModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 海航
 * on 2017/7/17.
 * 此类或接口用于
 */

public class CartList {
    public static List<CommodityModel> getGoods() {
        List<CommodityModel> goods = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            CommodityModel model = new CommodityModel();
            model.cartCount = (int) (i + (1 + Math.random() * 10));
            model.describe = "华为+" + i;
            model.price = "200" + i;
            model.imageUrl = "http://img3.yxlady.com/yl/UploadFiles_5361/2015116/20151106182541111.jpg";
            model.imageUrlBig = "http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=8464f30f0be93901420f857d13853e92/203fb80e7bec54e7ce6c5131b3389b504fc26a19.jpg";
            model.isSelect = false;
            model.title = "手机";
            model.brand = "华为";
            model.copy = "买三送一";
            goods.add(model);
        }
        return goods;
    }

    /**
     * 排序样板
     */
    private void listTest() {
        List<CommodityModel> datas = CartList.getGoods();
        Collections.sort(datas, new Comparator<CommodityModel>() {
            @Override
            public int compare(CommodityModel s1, CommodityModel s2) {
                int result = (s1.cartCount > s2.cartCount) ? 1 : ((s1.cartCount == s2.cartCount) ? 0 : -1);
                if (0 == result) {
                    result = s1.title.compareTo(s2.title);
                }
                return result;
            }
        });

    }

    public static List<CommodityModel> getGood2s() {
        List<CommodityModel> goods = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            CommodityModel model = new CommodityModel();
            model.cartCount = i;
            model.buyCount = i;
            model.describe = "华为+" + i;
            model.imageUrl = "http://img3.yxlady.com/yl/UploadFiles_5361/2015116/20151106182541111.jpg";
            model.imageUrlBig = "http://img3.yxlady.com/yl/UploadFiles_5361/2015116/20151106182541111.jpg";
            model.price = "200" + i;
            model.isSelect = false;
            model.title = "手机";
            goods.add(model);
        }
        return goods;
    }

    public static List<BannerModel> bannerModels() {
        List<BannerModel> goods = new ArrayList<>();
        BannerModel model = new BannerModel();
        model.image = "http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=8464f30f0be93901420f857d13853e92/203fb80e7bec54e7ce6c5131b3389b504fc26a19.jpg";
        goods.add(model);
        BannerModel model2 = new BannerModel();
        model2.image = "http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=899aec235382b2b7b392318759c4a19a/d1a20cf431adcbef5ed70a21a6af2edda3cc9f31.jpg";
        goods.add(model2);
        BannerModel model3 = new BannerModel();
        model3.image = "http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=489281e5888ba61ecbe3c06c295dfd7f/a9d3fd1f4134970ac9e997009fcad1c8a7865d03.jpg";
        goods.add(model3);
        return goods;
    }

    public static List<CommodityTypeModel> getCommodityTypeModel() {
        List<CommodityTypeModel> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CommodityTypeModel model = new CommodityTypeModel();
            model.imageUrl = "http://img3.yxlady.com/yl/UploadFiles_5361/2015116/20151106182541111.jpg";
            model.title = "华为p" + (6 + i);
            list.add(model);
        }
        return list;
    }

    public static List<HomeCommodityTypeModel> getHomeCommodityTypeModel() {
        List<HomeCommodityTypeModel> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            HomeCommodityTypeModel model = new HomeCommodityTypeModel();
            model.imageUrl = "http://img3.yxlady.com/yl/UploadFiles_5361/2015116/20151106182541111.jpg";
            model.title = "华为p" + (6 + i);
            list.add(model);
        }
        return list;
    }


    public static List<OrderDetailsDataModel> getOrderDataList(int position) {


        List<OrderDetailsDataModel> orderDetailsDataModels = new ArrayList<>();
        for (int j = 0;j<10;j++){
            OrderDetailsDataModel orderDetailsDataModel = new OrderDetailsDataModel();
            orderDetailsDataModel.goods = getGoods();
            orderDetailsDataModel.allPrice = 999+"";
            orderDetailsDataModel.expressFee = 10+"";
            orderDetailsDataModel.ligouForMoney=5+"";
            orderDetailsDataModel.orderId=1000;
            orderDetailsDataModel.orderId=1023434;
            orderDetailsDataModel.orderTime=1344556;
            switch (position){
                case 0:
                    orderDetailsDataModel.orderState = 0;
                    break;
                case 1:
                    orderDetailsDataModel.orderState = 2;
                    break;
                case 2:
                    orderDetailsDataModel.orderState = 3;
                    break;
                case 3:
                    orderDetailsDataModel.orderState = 4;
                    break;

                case 4:
                    orderDetailsDataModel.orderState = 5;
                    break;

            }

            orderDetailsDataModels.add(orderDetailsDataModel);

        }

        return orderDetailsDataModels;
    }


    public static UnionModel getUnionModel() {
        UnionModel unionModel = new UnionModel();
        unionModel.address = "河南";
        unionModel.businessScope = "华北";
        unionModel.storeName = "都市丽人";
        unionModel.imgUrl = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1501156636&di=a2ba7aca1eb6fbdff5f2c394d90855ca&src=http://photocdn.sohu.com/20160609/Img453732914.jpg";
        return unionModel;
    }

    public static List<CityModel> getCityModels1() {
        List<CityModel> cityModels = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            CityModel city1 = new CityModel();
            city1.areaName = "河南";
            city1.areaId = 1;
            cityModels.add(city1);
            CityModel city2 = new CityModel();
            city2.areaName = "郑州";
            city1.areaId = 2;
            cityModels.add(city2);
            CityModel city3 = new CityModel();
            city3.areaName = "北京";
            city1.areaId = 3;
            cityModels.add(city3);
        }
        return cityModels;
    }

    public static List<CityModel> getCityModels2() {
        List<CityModel> cityModels = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            CityModel city1 = new CityModel();
            city1.areaName = "河南";
            city1.areaId = 11;
            city1.parentId = 1;
            cityModels.add(city1);
            CityModel city2 = new CityModel();
            city2.areaName = "郑州";
            city2.parentId = 2;
            city1.areaId = 22;
            cityModels.add(city2);
            CityModel city3 = new CityModel();
            city3.areaName = "北京";
            city3.areaId = 33;
            city3.parentId = 3;
            cityModels.add(city3);
        }
        return cityModels;
    }

    public static List<CityModel> getCityModels3() {
        List<CityModel> cityModels = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            CityModel city1 = new CityModel();
            city1.areaName = "河南";
            city1.areaId = 111;
            city1.parentId = 11;
            cityModels.add(city1);
            CityModel city2 = new CityModel();
            city2.areaName = "郑州";
            city2.parentId = 22;
            city1.areaId = 222;
            cityModels.add(city2);
            CityModel city3 = new CityModel();
            city3.areaName = "北京";
            city3.areaId = 333;
            city3.parentId = 33;
            cityModels.add(city3);
        }
        return cityModels;
    }

    public static OrderDetailsDataModel getOrderModel() {
        OrderDetailsDataModel order = new OrderDetailsDataModel();
        order.goods = getGood2s();
        order.expressFee = 8+"";
        order.ligouForMoney = 66+"";
        order.orderNumber = "45432485454";
        order.allPrice = 888+"";
        order.mAddressModel = getAddressModel();
        return order;
    }

    public static ShippingAddressModel getAddressModel() {
        ShippingAddressModel addressModel = new ShippingAddressModel();
        addressModel.address = "china";
        addressModel.isDefault = false;
        addressModel.name = "独孤航海";
        addressModel.telNumber = "123456";
        return addressModel;
    }

    public static ShareModel getShareModel() {
        ShareModel model = new ShareModel();
        model.desc = "你爱的独孤航海";
        model.imgID = R.drawable.alg_debug_icon_qq_70_70;
        model.url = "https://www.baidu.com";
        model.title = "爱利购";
        return model;
    }

    public static String getLogisticsData() {
        String datas = "{\n" +
                "    \"EBusinessID\" : \"1109259\",\n" +
                "    \"OrderCode\" : \"\",\n" +
                "    \"ShipperCode\" : \"SF\",\n" +
                "    \"LogisticCode\" : \"118461988807\",\n" +
                "    \"Success\" : true,\n" +
                "    \"State\" : 3,\n" +
                "    \"Reason\" : null,\n" +
                "    \"traces\" : [{\n" +
                "            \"AcceptTime\" : \"2014/06/25 08:05:37\",\n" +
                "            \"AcceptStation\" : \"正在派件..(派件人:邓裕富,电话:18718866310)[深圳 市]\",\n" +
                "            \"Remark\" : null\n" +
                "        }, {\n" +
                "            \"AcceptTime\" : \"2014/06/25 04:01:28\",\n" +
                "            \"AcceptStation\" : \"快件在 深圳集散中心 ,准备送往下一站 深圳 [深圳市]\",\n" +
                "            \"Remark\" : null\n" +
                "        }, {\n" +
                "            \"AcceptTime\" : \"2014/06/25 01:41:06\",\n" +
                "            \"AcceptStation\" : \"快件在 深圳集散中心 [深圳市]\",\n" +
                "            \"Remark\" : null\n" +
                "        }, {\n" +
                "            \"AcceptTime\" : \"2014/06/24 20:18:58\",\n" +
                "            \"AcceptStation\" : \"已收件[深圳市]\",\n" +
                "            \"Remark\" : null\n" +
                "        }, {\n" +
                "            \"AcceptTime\" : \"2014/06/24 20:55:28\",\n" +
                "            \"AcceptStation\" : \"快件在 深圳 ,准备送往下一站 深圳集散中心 [深圳市]\",\n" +
                "            \"Remark\" : null\n" +
                "        }, {\n" +
                "            \"AcceptTime\" : \"2014/06/25 10:23:03\",\n" +
                "            \"AcceptStation\" : \"派件已签收[深圳市]\",\n" +
                "            \"Remark\" : null\n" +
                "        }, {\n" +
                "            \"AcceptTime\" : \"2014/06/25 10:23:03\",\n" +
                "            \"AcceptStation\" : \"签收人是：已签收[深圳市]\",\n" +
                "            \"Remark\" : null\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        return datas;
    }


    public static List<ReturnGoodsData> getReturnGoodsDataList() {
        List<ReturnGoodsData> returnGoodsDatas = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ReturnGoodsData returnGoodsData = new ReturnGoodsData();
            returnGoodsData.applyTime = 14809889;
            returnGoodsData.goodsId = 25456;
            returnGoodsData.orderId = 5454112;
            returnGoodsData.returnMoney = 199;
            returnGoodsData.returnNumber = "2113563655";
            List<CommodityModel> commodityModels = new ArrayList<>();
            if (i == 0 || i == 1) {

                for (int j = 0; j < 5; j++) {
                    CommodityModel model = new CommodityModel();
                    model.cartCount = i;
                    model.buyCount = i;
                    model.describe = "华为+" + i;
                    model.imageUrl = "http://img3.yxlady.com/yl/UploadFiles_5361/2015116/20151106182541111.jpg";
                    model.imageUrlBig = "http://img3.yxlady.com/yl/UploadFiles_5361/2015116/20151106182541111.jpg";
                    model.price = "200" + i;
                    model.isSelect = false;
                    model.title = "手机";
                    commodityModels.add(model);
                }
            } else {
                CommodityModel model = new CommodityModel();
                model.cartCount = i;
                model.buyCount = i;
                model.describe = "华为+" + i;
                model.imageUrl = "http://img3.yxlady.com/yl/UploadFiles_5361/2015116/20151106182541111.jpg";
                model.imageUrlBig = "http://img3.yxlady.com/yl/UploadFiles_5361/2015116/20151106182541111.jpg";
                model.price = "200" + i;
                model.isSelect = false;
                model.title = "手机";
                commodityModels.add(model);
            }

            returnGoodsData.goods = commodityModels;
            returnGoodsDatas.add(returnGoodsData);
        }

        return returnGoodsDatas;
    }
}
