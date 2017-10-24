package com.alg.ailigou.pages.personal.commentdetail;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.consts.FilePathConsts;
import com.alg.ailigou.common.utils.RxUtils;
import com.alg.ailigou.common.widget.IosDialog;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.CameraUtil;
import com.alg.ailigou.library.utils.PictureUtils;
import com.alg.ailigou.library.utils.UUIDUtils;
import com.alg.ailigou.library.utils.luban.Luban;
import com.alg.ailigou.library.utils.luban.OnCompressListener;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.personal.commentdetail.adapter.PhotoAdapter;
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.alg.ailigou.selectcity.utils.ToastUtils;
import com.hedgehog.ratingbar.RatingBar;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 玖泞
 * on 2017/8/10
 * 此类或接口用于待评价详情
 */

public class CommentDetailActivity extends BaseMvpActivity implements CommentDetailContracts.View {


    @Inject
    CommentDetailPresenter presenter;
    @BindView(R.id.iv_base_back)
    ImageView ivBaseBack;
    @BindView(R.id.ll_base_back)
    LinearLayout llBaseBack;
    @BindView(R.id.tv_base_title)
    TextView tvBaseTitle;
    @BindView(R.id.tv_base_edit)
    TextView tvBaseEdit;
    @BindView(R.id.ll_base_edit)
    LinearLayout llBaseEdit;
    @BindView(R.id.iv_base_notice)
    ImageView ivBaseNotice;
    @BindView(R.id.ll_base_notice)
    LinearLayout llBaseNotice;
    @BindView(R.id.tv_personal_comment_detail_Integrated_comment)
    TextView tvPersonalCommentDetailIntegratedComment;
    @BindView(R.id.ratingbar)
    RatingBar ratingbar;
    @BindView(R.id.et_personal_comment_write_comment)
    EditText etPersonalCommentWriteComment;

    @BindView(R.id.iv_personal_comment_detail_is_anonymous)
    ImageView ivPersonalCommentDetailIsAnonymous;
    @BindView(R.id.tv_personal_comment_detail_publish)
    TextView tvPersonalCommentDetailPublish;
    @BindView(R.id.tv_comment_detail_desc)
    TextView tvCommentDetailDesc;
    @BindView(R.id.rv_comment_detail_photo)
    RecyclerView rvCommentDetailPhoto;


    private List<String> photoUris = new ArrayList<>();

    private List<File> files = new ArrayList<>();


    File cameraFile;


    private Subscription mSubscribe;
    private PhotoAdapter adapter;

    private int commentLevel = 0;


    @Override
    protected void initBase() {
        super.initBase();
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerPersonalComponent.builder().personalModule(new PersonalModule(this)).build().inject(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_comment_detail;
    }


    @Override
    protected void afterContentView() {
        super.afterContentView();


        tvBaseTitle.setText("发表评价");
        ratingbar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                commentLevel = (int) RatingCount;

                switch ((int) RatingCount) {
                    case 0:

                        break;

                    case 1:

                        tvCommentDetailDesc.setText("非常差");
                        break;

                    case 2:

                        tvCommentDetailDesc.setText("差");

                        break;

                    case 3:

                        tvCommentDetailDesc.setText("一般");
                        break;

                    case 4:

                        tvCommentDetailDesc.setText("好");
                        break;

                    case 5:

                        tvCommentDetailDesc.setText("非常好");
                        break;


                }
            }
        });

        etPersonalCommentWriteComment.addTextChangedListener(mTextWatcher);
        photoUris.add("000");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rvCommentDetailPhoto.setLayoutManager(gridLayoutManager);

        adapter = new PhotoAdapter(photoUris, this);
        rvCommentDetailPhoto.setAdapter(adapter);


        adapter.setOnHeaderClickListener(new OnClickListener() {
            @Override
            public void setOnClickListener(View view, int position) {


                switch (view.getId()) {

                    case R.id.iv_item_comment_detail_delete_photo:
                        //点击的是删除按钮

                        if (photoUris.size() == 6) {
                            if (photoUris.contains("000")) {
                                photoUris.remove(position);
                            } else {
                                photoUris.remove(position);
                                photoUris.add(0, "000");
                            }

                        } else {
                            photoUris.remove(position);
                        }

                        adapter.notifyDataSetChanged();
                        break;


                    case R.id.iv_item_comment_detail_photo:
                        //点击的photoView
                        if ("000".equals(photoUris.get(position))) {
                            //弹出对话框
                            showChangeIconDialog();
                        } else {


                            Intent intent = new Intent(CommentDetailActivity.this, PhotoDetailActivity.class);
                            intent.putExtra("uri", photoUris.get(position));
                            startActivity(intent);

                        }


                        break;


                }


            }
        });
    }


    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();

        rvCommentDetailPhoto.setNestedScrollingEnabled(false);
        //设置调用单个条目变化没有动画
        ((SimpleItemAnimator) rvCommentDetailPhoto.getItemAnimator()).setSupportsChangeAnimations(false);
    }


    @OnClick({R.id.tv_personal_comment_detail_publish, R.id.ll_base_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tv_personal_comment_detail_publish:
                if (commentLevel == 0) {
                    ToastUtils.showToast(this, "亲,你还没写评价等级呢!");
                    return;
                }
                if (TextUtils.isEmpty(etPersonalCommentWriteComment.getText().toString().trim())) {
                    ToastUtils.showToast(this, "亲,评论不能为空!");
                    return;
                }

                break;

            case R.id.ll_base_back:
                finish();
                break;

        }
    }




    TextWatcher mTextWatcher = new TextWatcher() {
        private CharSequence temp;
        private int editStart;
        private int editEnd;

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO Auto-generated method stub
            temp = s;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
//          mTextView.setText(s);//将输入的内容实时显示
        }

        @Override
        public void afterTextChanged(Editable s) {

            if (temp.length() > 290) {

                ToastUtils.showToast(CommentDetailActivity.this, "你输入的字数已经快超过了限制！");

            }
        }
    };

    /**
     * 弹出拍照 去相册的dialog
     */
    private void showChangeIconDialog() {
        final IosDialog dialog = new IosDialog(this);
        dialog.setContent("拍照上传");
        dialog.setSureText("相册选择");
        dialog.setCancelText("取消");
        dialog.show();
        dialog.setIosDialogCallBack(new IosDialog.IosDialogCallBack() {
            @Override
            public void onSure() {
                RxPermissions.getInstance(CommentDetailActivity.this).request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            //从相册拿
                            getImageFromAlbum();
                        }
                    }
                });
            }

            @Override
            public void onCancle() {
                dialog.dismiss();
            }
        });
        dialog.setDialogContentCallBack(new IosDialog.IosDialogContentCallBack() {
            @Override
            public void onContent() {
                RxPermissions.getInstance(CommentDetailActivity.this).request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            //拍照
                            File file = new File(FilePathConsts.PIC_PATH);
                            if (!file.exists()) {
                                file.mkdirs();
                            }
                            cameraFile = new File(file, UUIDUtils.getUUID());
                            CameraUtil.openCamera(CommentDetailActivity.this, cameraFile);
                        }
                    }
                });
            }
        });
    }

    /**
     * 获取相册中的图片
     */
    public void getImageFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, 2);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CameraUtil.PHOTO_REQUEST_CAREMA) {
            if (cameraFile != null && cameraFile.length() > 0) {
                compresPic(cameraFile);

            }
            if (cameraFile != null && cameraFile.length() == 0) {//在个别手机上 会出现执行该方法但是图片的大小为0,所以开线程等一等
                mSubscribe = Observable.create(new Observable.OnSubscribe<File>() {
                    @Override
                    public void call(Subscriber<? super File> subscriber) {
                        //这是在子线程中 死循环 写图片完成时 到主线程 更新ui
                        File file = cameraFile;
                        while (file.length() == 0) {
                            SystemClock.sleep(10);
                        }
                        subscriber.onNext(file);
                        subscriber.onCompleted();
                    }
                }).compose(RxUtils.<File>schedulerHelper()).subscribe(new Action1<File>() {
                    @Override
                    public void call(File file) {
                        //这是主线程
                        compresPic(file);



                    }
                });
            }

        } else if (requestCode == 2) {//从相册拿图片
            if (data == null)
                return;
            Uri uri = data.getData();
            String path;
            int sdkVersion = Integer.valueOf(Build.VERSION.SDK);
            if (sdkVersion >= 19) {
                path = uri.getPath();
                path = PictureUtils.getPath_above19(this, uri);
            } else {
                path = PictureUtils.getFilePath_below19(this, uri);
            }
            compresPic(new File(path));

        }
    }

    /**
     * 图片压缩
     *
     * @param file
     */
    private void compresPic(File file) {
        Luban.with(this)
                .load(file)                     //传人要压缩的图片
                .setCompressListener(new OnCompressListener() { //设置回调
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(File file) {
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                        if (photoUris.size() == 6) {
                            photoUris.remove(0);
                        }

                        photoUris.add(file.getAbsolutePath());
                        adapter.notifyDataSetChanged();

                        files.add(file);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                }).launch();    //启动压缩
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscribe != null && !mSubscribe.isUnsubscribed()) {
            mSubscribe.unsubscribe();
        }
    }
}
