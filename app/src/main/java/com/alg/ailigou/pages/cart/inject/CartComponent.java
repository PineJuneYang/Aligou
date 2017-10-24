package com.alg.ailigou.pages.cart.inject;

import com.alg.ailigou.common.inject.FragmentModule;
import com.alg.ailigou.pages.cart.entrance.AiligouCartFragment;
import com.alg.ailigou.pages.cart.entrance.CartFragment;
import com.alg.ailigou.pages.cart.newpush.NewGoodsPushActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * AiligouApp
 * com.alg.ailigouapp.pages.cart.inject
 * Created by Chris Chen on 2017/7/4 14:17.
 * Explain:购物车dagger2注入器
 */
@Singleton
@Component(modules = {FragmentModule.class})
public interface CartComponent {
    void inject(CartFragment cartFragment);

    void inject(NewGoodsPushActivity newGoodsPushActivity);

    void inject(AiligouCartFragment ailigouCartFragment);
}
