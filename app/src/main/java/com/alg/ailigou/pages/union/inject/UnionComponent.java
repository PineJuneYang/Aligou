package com.alg.ailigou.pages.union.inject;

import com.alg.ailigou.pages.union.entrance.UnionActivity;
import com.alg.ailigou.pages.union.entrance.UnionFragment;
import com.alg.ailigou.pages.union.unionsearch.UnionSearchActivity;
import com.alg.ailigou.pages.union.uniondetail.UnionDetailActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 海航
 * on 2017/7/26.
 * 此类或接口用于
 */
@Singleton
@Component(modules = {UnionModule.class})
public interface UnionComponent {
    void inject(UnionFragment homeFragment);//入口

    void inject(UnionActivity unionActivity);



    void inject(UnionDetailActivity unionDetailActivity);

    void inject(UnionSearchActivity unionSearchActivity);//联盟商家搜索

}
