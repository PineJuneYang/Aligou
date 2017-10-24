package com.alg.ailigou.common.base;

import com.alg.ailigou.common.utils.AppUtils;
import com.alg.ailigou.common.utils.baidulocation.LocationService;
import com.alg.ailigou.library.base.application.BaseApplication;
import com.alg.ailigou.library.manager.UtilsManager;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.utils.Log;

/**
 * com.alg.ailigouapp.common.base
 * AiligouApp
 * Created by Chris Chen on 2017/6/29 10:27
 * Explain:
 */

public class BaseApp extends BaseApplication {

    @Override
    protected void init() {
        AppUtils.init(getApplicationContext());
        UtilsManager.init(getApplicationContext());
        locationService = new LocationService(getApplicationContext());
        initUM();
    }

    private void initUM() {
        //开启debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        Config.DEBUG = true;
        QueuedWork.isUseThreadPool = false;
        UMShareAPI.get(this);
        Config.isJumptoAppStore = true;//没有下载微信  qq  直接去商店
        UMShareConfig config = new UMShareConfig();
        config.setSinaAuthType(UMShareConfig.AUTH_TYPE_SSO);
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(this).setShareConfig(config);
        Log.LOG = false;  //生产环境为  false
    }

    public LocationService getLocationService() {
        return locationService;
    }

    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    private LocationService locationService;
    //各个平台的配置，建议放在全局Application或者程序入口
    {
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");

    }
}
