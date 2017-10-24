package com.alg.ailigou.pages.main.inject;

import com.alg.ailigou.pages.main.entrance.MainActivity;

import dagger.Component;

/**
 * com.alg.ailigouapp.pages.main.inject
 * AiligouApp
 * Created by Chris Chen on 2017/6/29 11:04
 * Explain:main模块下的dagger2注入器
 */
@Component(modules = {MainModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
