package com.alg.ailigou.common.inject;

import android.content.Context;

import com.alg.ailigou.common.model.UserModel;
import com.alg.ailigou.library.base.activity.BaseActivity;

import dagger.Module;
import dagger.Provides;

/**
 * com.alg.ailigouapp.common.inject
 * AiligouApp
 * Created by Chris Chen on 2017/6/29 10:54
 * Explain:基本的Activity的注入module
 */
@Module
public class ActivityModule {
    private BaseActivity baseActivity;

    /**
     * 构造方法
     *
     * @param baseActivity
     */
    public ActivityModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Provides
    Context proContext(){
        return baseActivity.getBaseContext();
    }

    /**
     * 示例：用dagger2创建对象
     * @return
     */
    @Provides
    public UserModel proUserModel(){
        UserModel userModel=new UserModel();
        userModel.name="testName";
        userModel.age=39;
        return userModel;
    }

}
