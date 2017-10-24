package com.alg.ailigou.pages.news.inject;

import com.alg.ailigou.pages.news.entrance.NewsActivity;
import com.alg.ailigou.pages.news.entrance.NewsFragment;
import com.alg.ailigou.pages.news.fragments.algmoving.NewsAlgMovingFragment;
import com.alg.ailigou.pages.news.fragments.hotnews.HotNewFragment;
import com.alg.ailigou.pages.news.fragments.industry.IndustryMovingFragment;
import com.alg.ailigou.pages.news.fragments.newrelease.NewRealeseFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * AiligouApp
 * com.alg.ailigou.pages.news.inject
 * Created by Chris Chen on 2017/7/7 15:11.
 * Explain:新闻资讯dagger2注入器
 */
@Singleton
@Component(modules = {NewsMoudule.class})
public interface NewsComponent {
    void inject(NewsFragment newsFragment);

    void inject(NewsAlgMovingFragment newsFragment);

    void inject(IndustryMovingFragment newsFragment);

    void inject(NewRealeseFragment newsFragment);

    void inject(HotNewFragment newsFragment);

    void inject(NewsActivity newsActivity);
}
