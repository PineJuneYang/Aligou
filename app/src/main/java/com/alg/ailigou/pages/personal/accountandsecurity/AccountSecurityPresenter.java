package com.alg.ailigou.pages.personal.accountandsecurity;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/8/3
 * 此类或接口用于
 */

public class AccountSecurityPresenter implements AccountSecurityContracts.Presenter {


    private AccountSecurityContracts.View view ;


    @Inject
    public AccountSecurityPresenter(){



    }

    @Override
    public void bindView(AccountSecurityContracts.View view) {
        this.view=view;
    }

    @Override
    public void unbindView() {

    }
}
