package com.alg.ailigou.pages.main.entrance;

import android.Manifest;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.view.KeyEvent;

import com.alg.ailigou.R;
import com.alg.ailigou.common.base.BaseApp;
import com.alg.ailigou.common.consts.AppConsts;
import com.alg.ailigou.common.event.UnionEvent;
import com.alg.ailigou.common.utils.baidulocation.LocationService;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.manager.WinManager;
import com.alg.ailigou.library.utils.MessageUtils;
import com.alg.ailigou.library.widget.CustomViewPager;
import com.alg.ailigou.pages.main.entrance.adapter.MainFragmentAdapter;
import com.alg.ailigou.pages.main.inject.DaggerMainComponent;
import com.alg.ailigou.pages.main.inject.MainModule;
import com.alg.ailigou.selectcity.utils.ToastUtils;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.umeng.socialize.UMShareAPI;

import javax.inject.Inject;

import butterknife.BindView;
import rx.functions.Action1;

public class MainActivity extends BaseMvpActivity implements AppConsts, MainContracts.View,BDLocationListener {
    @BindView(R.id.tab_main_pages)
    TabLayout tabMainPages;
    @BindView(R.id.vp_main_pages)
    CustomViewPager vpMainPages;
    private LocationService mService;
    @Inject
    MainPresenter presenter;
    @Inject
    MainFragmentAdapter adapter;

    private long backTime;//按下返回键的时间

    @Override
    protected int layoutId() {
        return R.layout.alg_act_main;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerMainComponent.builder().mainModule(new MainModule(this)).build().inject(this);
    }

    @Override
    protected void initViewAndListener() {
        vpMainPages.setAdapter(adapter);
        vpMainPages.setOffscreenPageLimit(4);

        tabMainPages.setupWithViewPager(vpMainPages);
        tabMainPages.removeAllTabs();

        for (int i = 0; i < PAGE_TAB_TITLES.length; i++) {
            TabLayout.Tab tab = tabMainPages.newTab();
            tab.setCustomView(PAGE_TAB_VIEWS[i]);
            tabMainPages.addTab(tab, i, i == 0);
        }
        initLocation();
    }


    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int activityThemeColor() {
        return getResources().getColor(R.color.alg_common_bg);
    }

    @Override
    protected boolean hasBus() {
        return true;//需要RxBus进行通信
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {//如果按下返回键
            if (System.currentTimeMillis() - backTime < 1000 * 2) {//如果两秒之内，则退出
                WinManager.get().exitApp(this);
                return true;
            } else {
                MessageUtils.showRes(R.string.ALG_PROMPT_AGAIN_EXIT_APP);//提示再按一次退出APP
                backTime = System.currentTimeMillis();//记住新的时间
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 首页点击联盟商家之后跳转
     *
     * @param event
     */
    @Subscribe
    public void onUnionClick(UnionEvent event) {
        vpMainPages.setCurrentItem(2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mService != null) {
            mService.stop();
        }
    }

    /**
     * 友盟回调的方法
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 获取定位
     */
    private void initLocation() {
        RxPermissions.getInstance(this).request(Manifest.permission.ACCESS_COARSE_LOCATION).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean) {
                    mService = ((BaseApp) getApplication()).getLocationService();
                    mService.registerListener(MainActivity.this);
                    mService.start();
                }
            }
        });
    }

    /*****
     *
     * 定位结果回调
     *
     */
    @Override
    public void onReceiveLocation(BDLocation location) {
        if (null != location && location.getLocType() != BDLocation.TypeServerError) {
            //StringBuffer sb = new StringBuffer(256);
           if (location.getCity()!=null){
               String city=location.getCity();
               RxBus.get().post("location",city);
           }else {
               ToastUtils.showToast(this,"定位失败,请检查网络!");
           }
        }
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }
}
