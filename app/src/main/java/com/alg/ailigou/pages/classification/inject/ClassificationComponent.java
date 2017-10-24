package com.alg.ailigou.pages.classification.inject;

import com.alg.ailigou.common.inject.FragmentModule;
import com.alg.ailigou.pages.classification.entrance.ClassificationFragment;
import com.alg.ailigou.pages.classification.fragments.hot.HotFragment;
import com.alg.ailigou.pages.classification.fragments.normal.NormalFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * AiligouApp
 * com.alg.ailigouapp.pages.classification.inject
 * Created by Chris Chen on 2017/7/4 15:03.
 * Explain:分类dagger2注入器
 */
@Singleton
@Component(modules = {FragmentModule.class})
public interface ClassificationComponent {

    void inject(ClassificationFragment classificationFragment);//入口

    void inject(HotFragment hotFragment);//热卖推荐

    void inject(NormalFragment normalFragment);//一般分类

}
