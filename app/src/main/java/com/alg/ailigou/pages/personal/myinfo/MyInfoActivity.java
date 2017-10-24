package com.alg.ailigou.pages.personal.myinfo;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.api.user.UserApi;
import com.alg.ailigou.common.consts.FilePathConsts;
import com.alg.ailigou.common.model.UserModel;
import com.alg.ailigou.common.net.NetCallback;
import com.alg.ailigou.common.net.NetResponse;
import com.alg.ailigou.common.utils.ImageLoadUtils;
import com.alg.ailigou.common.utils.RxUtils;
import com.alg.ailigou.common.widget.IosDialog;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.CameraUtil;
import com.alg.ailigou.library.utils.PictureUtils;
import com.alg.ailigou.library.utils.UUIDUtils;
import com.alg.ailigou.library.utils.luban.Luban;
import com.alg.ailigou.library.utils.luban.OnCompressListener;
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.alg.ailigou.pages.personal.myqrcode.MyQRcodeActivity;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

import static com.alg.ailigou.R.id.iv_my_info_avatar;
import static com.alg.ailigou.R.id.iv_user_icon;


/**
 * Created by
 * on 2017/8/4
 * 此类或接口用于 我的资料
 */

public class MyInfoActivity extends BaseMvpActivity implements MyInfoContracts.View {

    @Inject
    MyInfoPresenter presenter;
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
    @BindView(iv_my_info_avatar)
    ImageView ivMyInfoAvatar;
    @BindView(iv_user_icon)
    ImageView ivUserIcon;
    @BindView(R.id.relative_my_info_avatar)
    RelativeLayout relativeMyInfoAvatar;
    @BindView(R.id.iv_my_info_username)
    TextView tvMyInfoUsername;
    @BindView(R.id.relative_my_info_username)
    RelativeLayout relativeMyInfoUsername;
    @BindView(R.id.iv_my_info_sex)
    TextView tvMyInfoSex;
    @BindView(R.id.relative_my_info_sex)
    RelativeLayout relativeMyInfoSex;
    @BindView(R.id.iv_my_info_qrcode)
    ImageView ivMyInfoQrcode;
    @BindView(R.id.relative_my_info_qrcode)
    RelativeLayout relativeMyInfoQrcode;
    @BindView(R.id.rl_base_title)
    RelativeLayout rlBase;
    File cameraFile;
    private UserModel user;
    private Subscription mSubscribe;
    private int sex;//0保密,1男  2,女
    private String name = "";
    private File iconFile;

    @Override
    public void beforeContentView() {
        super.beforeContentView();
    }

    @Override
    protected void initBase() {
        super.initBase();
        user = (UserModel) getIntent().getSerializableExtra("user");
    }

    @Override
    protected void initInjector() {
        DaggerPersonalComponent.builder().personalModule(new PersonalModule(this)).build().inject(this);
    }

    @Override
    protected void afterContentView() {
        super.afterContentView();
        tvBaseTitle.setText("我的资料");
        rlBase.setBackgroundResource(R.color.alg_common_bg_white);
        tvBaseTitle.setTextColor(Color.BLACK);
        ivBaseBack.setBackgroundResource(R.drawable.alg_common_black_left_arrow);
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        if (user != null) {
            setData();
        }

    }

    private void setData() {
        setImageCircle(user.imageUrl);
        tvMyInfoUsername.setText(user.name);
        sex = user.sex;
        name = user.name;
        switch (user.sex) {
            case 0:
                tvMyInfoSex.setText("保密");
                break;
            case 1:
                tvMyInfoSex.setText("男");
                break;
            case 2:
                tvMyInfoSex.setText("女");
                break;
        }

    }


    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_my_info;
    }


    @OnClick({R.id.relative_my_info_avatar, R.id.relative_my_info_username, R.id.relative_my_info_sex, R.id.relative_my_info_qrcode, R.id.ll_base_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.relative_my_info_avatar:
                showChangeIconDialog();
                break;
            case R.id.relative_my_info_username:

                break;
            case R.id.relative_my_info_sex:
                showSexDialog();
                break;
            case R.id.relative_my_info_qrcode:
                startActivity(MyQRcodeActivity.class);
                break;

            case R.id.ll_base_back:

                finish();
                break;
        }
    }

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
                RxPermissions.getInstance(MyInfoActivity.this).request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
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
                RxPermissions.getInstance(MyInfoActivity.this).request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            //拍照
                            File file = new File(FilePathConsts.PIC_PATH);
                            if (!file.exists()) {
                                file.mkdirs();
                            }
                            cameraFile = new File(file, UUIDUtils.getUUID() + ".jpg");
                            CameraUtil.openCamera(MyInfoActivity.this, cameraFile);
                        }
                    }
                });
            }
        });
    }

    /**
     * 弹出修改性别的dialog
     */
    private void showSexDialog() {
        final IosDialog dialog = new IosDialog(this);
        dialog.setContent("男");
        dialog.setSureText("女");
        dialog.setCancelText("取消");
        dialog.show();
        dialog.setIosDialogCallBack(new IosDialog.IosDialogCallBack() {
            @Override
            public void onSure() {
                tvMyInfoSex.setText("女");
                sex = 2;
                dialog.dismiss();
            }

            @Override
            public void onCancle() {
                dialog.dismiss();
            }
        });
        dialog.setDialogContentCallBack(new IosDialog.IosDialogContentCallBack() {
            @Override
            public void onContent() {
                tvMyInfoSex.setText("男");
                sex = 1;
                dialog.dismiss();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CameraUtil.PHOTO_REQUEST_CAREMA) {
            if (cameraFile != null && cameraFile.length() > 0) {
                compresPic(cameraFile);
                setImageCircle(cameraFile.getAbsolutePath());
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
                        setImageCircle(file.getAbsolutePath());
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
            setImageCircle(path);
        }
    }

    private void setImageCircle(String path) {
        ImageLoadUtils.setImageCircle(this, path, ivUserIcon);
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
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                    }

                    @Override
                    public void onSuccess(File file) {
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                        iconFile = file;
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO 当压缩过程出现问题时调用
                    }
                }).launch();    //启动压缩
    }

    /**
     * 获取相册中的图片
     */
    public void getImageFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, 2);
    }

    /**
     * 在退出的时候请求网络
     */
    @Override
    protected void onDestroy() {
        if (iconFile != null) {
            ArrayList<File> files = new ArrayList<>();
            files.add(iconFile);
            UserApi.submitFiles(files, new NetCallback<Object>() {
                @Override
                protected void onComplete(NetResponse<Object> netResponse) {
                    if (netResponse.isSuccess) {
                        presenter.updataUserInfo(user.sex, user.imageUrl, user.name);
                    }
                }
            });
        } else if (sex != user.sex || !name.equals(user.name)) {
            presenter.updataUserInfo(sex, user.imageUrl, name);
        }
        super.onDestroy();
        if (mSubscribe != null && mSubscribe.isUnsubscribed()) {
            mSubscribe.unsubscribe();
        }

    }


}
