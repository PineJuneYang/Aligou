package com.alg.ailigou.pages.personal.withdrawNotes;

import javax.inject.Inject;

/**
 * Created by 海航
 * on 2017/8/7.
 * 此类或接口用于
 */

public class WthdrawNotesPresenter implements WthdrawNotesConstyacts.Presenter {
    @Inject
    public WthdrawNotesPresenter() {
    }

    WthdrawNotesConstyacts.View view;

    @Override
    public void bindView(WthdrawNotesConstyacts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void loadData(long timeFrom, long timeTo) {
        view.notify("");
    }
}
