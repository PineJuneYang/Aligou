package com.alg.ailigou.pages.supper.inject;

import com.alg.ailigou.common.inject.FragmentModule;
import com.alg.ailigou.pages.supper.entrance.SupperFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * AiligouApp
 * com.alg.ailigouapp.pages.news.inject
 * Created by Chris Chen on 2017/7/4 17:34.
 * Explain:
 */
@Singleton
@Component(modules = {FragmentModule.class})
public interface SupperComponent {
    void inject(SupperFragment supperFragment);
}
