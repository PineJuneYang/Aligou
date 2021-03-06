package com.alg.ailigou.common.utils.baidulocation;

import android.content.Context;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

/**
 * @author 海航
 */
// MD5: 27:99:A7:64:88:7D:2C:42:D1:0D:D7:E0:00:ED:73:2E
//SHA1: 66:F6:4E:46:21:B8:0D:07:ED:22:61:19:C6:E3:49:13:C3:64:B0:B1
//SHA256: F0:66:C9:CC:2B:9F:5B:F3:F2:B7:97:C2:1B:B0:58:06:E3:46:8D:96:7D:97:A6:68:94:AD:54:26:DB:3D:47:0F

public class LocationService {
    public final static String CoorType_GCJ02 = "gcj02";
    public final static String CoorType_BD09LL = "bd09ll";
    public final static String CoorType_BD09MC = "bd09";
    /***
     *61 ： GPS定位结果，GPS定位成功。
     *62 ： 无法获取有效定位依据，定位失败，请检查运营商网络或者wifi网络是否正常开启，尝试重新请求定位。
     *63 ： 网络异常，没有成功向服务器发起请求，请确认当前测试手机网络是否通畅，尝试重新请求定位。
     *65 ： 定位缓存的结果。
     *66 ： 离线定位结果。通过requestOfflineLocaiton调用时对应的返回结果。
     *67 ： 离线定位失败。通过requestOfflineLocaiton调用时对应的返回结果。
     *68 ： 网络连接失败时，查找本地离线定位时对应的返回结果。
     *161： 网络定位结果，网络定位定位成功。
     *162： 请求串密文解析失败。
     *167： 服务端定位失败，请您检查是否禁用获取位置信息权限，尝试重新请求定位。
     *502： key参数错误，请按照说明文档重新申请KEY。
     *505： key不存在或者非法，请按照说明文档重新申请KEY。
     *601： key服务被开发者自己禁用，请按照说明文档重新申请KEY。
     *602： key mcode不匹配，您的ak配置过程中安全码设置有问题，请确保：sha1正确，“;”分号是英文状态；且包名是您当前运行应用的包名，请按照说明文档重新申请KEY。
     *501～700：key验证失败，请按照说明文档重新申请KEY。
     */

    public static float[] EARTH_WEIGHT = {0.1f, 0.2f, 0.4f, 0.6f, 0.8f}; // 推算计算权重_地球
    //public static float[] MOON_WEIGHT = {0.0167f,0.033f,0.067f,0.1f,0.133f};
    //public static float[] MARS_WEIGHT = {0.034f,0.068f,0.152f,0.228f,0.304f};

    private LocationClient client = null;
    private LocationClientOption mOption, DIYoption;
    private Object objLock = new Object();

    /***
     *
     * @param locationContext
     */
    public LocationService(Context locationContext) {
        synchronized (objLock) {
            if (client == null) {
                client = new LocationClient(locationContext);
                client.setLocOption(getDefaultLocationClientOption());
            }
        }
    }

    /***
     *
     * @param listener
     * @return
     */

    public boolean registerListener(BDLocationListener listener) {
        boolean isSuccess = false;
        if (listener != null) {
            client.registerLocationListener(listener);
            isSuccess = true;
        }
        return isSuccess;
    }

    public void unregisterListener(BDLocationListener listener) {
        if (listener != null) {
            client.unRegisterLocationListener(listener);
        }
    }

    /***
     *
     * @param option
     * @return isSuccessSetOption
     */
    public boolean setLocationOption(LocationClientOption option) {
        boolean isSuccess = false;
        if (option != null) {
            if (client.isStarted())
                client.stop();
            DIYoption = option;
            client.setLocOption(option);
        }
        return isSuccess;
    }

    public LocationClientOption getOption() {
        return DIYoption;
    }

    /***
     *
     * @return DefaultLocationClientOption
     */
    public LocationClientOption getDefaultLocationClientOption() {
        if (mOption == null) {
            mOption = new LocationClientOption();
            mOption.setLocationMode(LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
            mOption.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
            mOption.setScanSpan(0);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
            mOption.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
            mOption.setIsNeedLocationDescribe(true);//可选，设置是否需要地址描述
            mOption.setNeedDeviceDirect(false);//可选，设置是否需要设备方向结果
            mOption.setLocationNotify(false);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
            mOption.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
            mOption.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
            mOption.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
            mOption.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集

            mOption.setIsNeedAltitude(false);//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用

        }
        return mOption;
    }

    public void start() {
        synchronized (objLock) {
            if (client != null && !client.isStarted()) {
                client.start();
            }
        }
    }

    public void stop() {
        synchronized (objLock) {
            if (client != null && client.isStarted()) {
                client.stop();
            }
        }
    }

    public boolean requestHotSpotState() {

        return client.requestHotSpotState();

    }
//------------------------------------这个是定位之后的返回值--------------------------------------------------
//         sb.append("time : ");
//    /**
//     * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
//     * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
//     */
//            sb.append(location.getTime());
//            sb.append("\nlocType : ");// 定位类型
//            sb.append(location.getLocType());
//            sb.append("\nlocType description : ");// *****对应的定位类型说明*****
//            sb.append(location.getLocTypeDescription());
//            sb.append("\nlatitude : ");// 纬度
//            sb.append(location.getLatitude());
//            sb.append("\nlontitude : ");// 经度
//            sb.append(location.getLongitude());
//            sb.append("\nradius : ");// 半径
//            sb.append(location.getRadius());
//            sb.append("\nCountryCode : ");// 国家码
//            sb.append(location.getCountryCode());
//            sb.append("\nCountry : ");// 国家名称
//            sb.append(location.getCountry());
//            sb.append("\ncitycode : ");// 城市编码
//            sb.append(location.getCityCode());
//            sb.append("\ncity : ");// 城市
//            sb.append(location.getCity());
//            sb.append("\nDistrict : ");// 区
//            sb.append(location.getDistrict());
//            sb.append("\nStreet : ");// 街道
//            sb.append(location.getStreet());
//            sb.append("\naddr : ");// 地址信息
//            sb.append(location.getAddrStr());
//            sb.append("\nUserIndoorState: ");// *****返回用户室内外判断结果*****
//            sb.append(location.getUserIndoorState());
//            sb.append("\nDirection(not all devices have value): ");
//            sb.append(location.getDirection());// 方向
//            sb.append("\nlocationdescribe: ");
//            sb.append(location.getLocationDescribe());// 位置语义化信息
//            sb.append("\nPoi: ");// POI信息
//            if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
//        for (int i = 0; i < location.getPoiList().size(); i++) {
//            Poi poi = (Poi) location.getPoiList().get(i);
//            sb.append(poi.getName() + ";");
//        }
//    }
//            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
//        sb.append("\nspeed : ");
//        sb.append(location.getSpeed());// 速度 单位：km/h
//        sb.append("\nsatellite : ");
//        sb.append(location.getSatelliteNumber());// 卫星数目
//        sb.append("\nheight : ");
//        sb.append(location.getAltitude());// 海拔高度 单位：米
//        sb.append("\ngps status : ");
//        sb.append(location.getGpsAccuracyStatus());// *****gps质量判断*****
//        sb.append("\ndescribe : ");
//        sb.append("gps定位成功");
//    } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
//        // 运营商信息
//        if (location.hasAltitude()) {// *****如果有海拔高度*****
//            sb.append("\nheight : ");
//            sb.append(location.getAltitude());// 单位：米
//        }
//        sb.append("\noperationers : ");// 运营商信息
//        sb.append(location.getOperators());
//        sb.append("\ndescribe : ");
//        sb.append("网络定位成功");
//    } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
//        sb.append("\ndescribe : ");
//        sb.append("离线定位成功，离线定位结果也是有效的");
//    } else if (location.getLocType() == BDLocation.TypeServerError) {
//        sb.append("\ndescribe : ");
//        sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
//    } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
//        sb.append("\ndescribe : ");
//        sb.append("网络异常导致定位失败，请检查网络是否通畅");
//    } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
//        sb.append("describe : ");
//        sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
//    }

}
