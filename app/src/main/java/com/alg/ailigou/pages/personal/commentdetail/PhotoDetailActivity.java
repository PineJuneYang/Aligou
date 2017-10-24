package com.alg.ailigou.pages.personal.commentdetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.alg.ailigou.R;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 玖泞
 * on 2017/8/11
 * 此类或接口用于
 */

public class PhotoDetailActivity extends BaseMvpActivity {

    @BindView(R.id.pv_photo_detail_show)
    PhotoView pvPhotoDetailShow;
    @BindView(R.id.pb_photo_view_progress)
    ProgressBar pbPhotoViewProgress;
    private String uri;

    @Override
    protected void initBase() {
        super.initBase();
        Intent intent = getIntent();
        if (intent != null) {
            uri = intent.getStringExtra("uri");
        }
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_photo_detail;
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        Glide.with(this).load(uri).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                pbPhotoViewProgress.setVisibility(View.GONE);
                return false;
            }
        }).diskCacheStrategy(DiskCacheStrategy.ALL).into(pvPhotoDetailShow);
    }




    @OnClick(R.id.pv_photo_detail_show)
    public void onViewClicked() {
        finish();
    }
}
