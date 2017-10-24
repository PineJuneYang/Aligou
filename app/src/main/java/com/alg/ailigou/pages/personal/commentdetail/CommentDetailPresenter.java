package com.alg.ailigou.pages.personal.commentdetail;

import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by 玖泞
 * on 2017/8/10
 * 此类或接口用于
 */

public class CommentDetailPresenter implements CommentDetailContracts.Presenter {

    private CommentDetailContracts.View view;

    @Inject
    public CommentDetailPresenter() {
    }

    @Override
    public void bindView(CommentDetailContracts.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void submitImgs(List<File> files) {

    }

    @Override
    public void submitData( Map<String, Object> map) {
        UserApi.sendCommentData(map, new NetCallback<Object>() {
            @Override
            protected void onComplete(NetResponse<Object> netResponse) {
                if (netResponse.isSuccess) {
                    // TODO: 2017/8/29
                }
            }
        });
    }
}
