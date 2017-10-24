package com.alg.ailigou.pages.personal.applyreturnmoney;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alg.ailigou.R;
import com.alg.ailigou.common.consts.FilePathConsts;
import com.alg.ailigou.common.model.OrderDetailsDataModel;
import com.alg.ailigou.common.model.ReturnGoodsData;
import com.alg.ailigou.common.utils.RxUtils;
import com.alg.ailigou.common.widget.IosDialog;
import com.alg.ailigou.library.base.activity.BaseMvpActivity;
import com.alg.ailigou.library.base.mvp.BasePresenter;
import com.alg.ailigou.library.utils.CameraUtil;
import com.alg.ailigou.library.utils.PictureUtils;
import com.alg.ailigou.library.utils.UUIDUtils;
import com.alg.ailigou.pages.cart.adapter.OnClickListener;
import com.alg.ailigou.pages.cart.adapter.OnItemClickListener;
import com.alg.ailigou.pages.personal.commentdetail.PhotoDetailActivity;
import com.alg.ailigou.pages.personal.commentdetail.adapter.PhotoAdapter;
import com.alg.ailigou.pages.personal.commonwebview.CommonWebviewActivity;
import com.alg.ailigou.pages.personal.consts.PersonalConsts;
import com.alg.ailigou.pages.personal.inject.DaggerPersonalComponent;
import com.alg.ailigou.pages.personal.inject.PersonalModule;
import com.alg.ailigou.pages.personal.refundsdetail.RefundsDetailActivity;
import com.alg.ailigou.pages.personal.widget.ReasonDialog;
import com.alg.ailigou.selectcity.utils.ToastUtils;
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
 * Created by 海航
 * on 2017/8/17.
 * 此类或接口用于  申请退款
 */

public class ApplyReturnMoneyActivity extends BaseMvpActivity implements ApplyReturnMoneyContrats.View {
    @BindView(R.id.iv_base_back)
    ImageView mIvBaseBack;
    @BindView(R.id.ll_base_back)
    LinearLayout mLlBaseBack;
    @BindView(R.id.tv_base_title)
    TextView mTvBaseTitle;
    @BindView(R.id.tv_base_edit)
    TextView mTvBaseEdit;
    @BindView(R.id.ll_base_edit)
    LinearLayout mLlBaseEdit;
    @BindView(R.id.iv_base_notice)
    ImageView mIvBaseNotice;
    @BindView(R.id.ll_base_notice)
    LinearLayout mLlBaseNotice;
    @BindView(R.id.et_return_money)
    EditText mEtReturnMoney;
    @BindView(R.id.et_return_desc)
    EditText mEtReturnDesc;
    @BindView(R.id.tv_return_desc)
    TextView mTvReturnDesc;
    @BindView(R.id.tv_return_reason)
    TextView mTvReturnReason;
    @BindView(R.id.recyler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;

    private IosDialog picDialog;
    private ReasonDialog reasonDialog;
    private OrderDetailsDataModel orderData;//上个界面传递的order对象
    private ReturnGoodsData mData;//当前页面的数据
    private List<String> imgs;//图片地址集合

    File cameraFile;
    private Subscription mSubscribe;

    private PhotoAdapter mAdapter;

    @Inject
    ApplyReturnMoneyPresenter mPresenter;

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.alg_act_personal_apply_return_money;
    }

    @Override
    protected void initInjector() {
        super.initInjector();
        DaggerPersonalComponent.builder().personalModule(new PersonalModule(this)).build().inject(this);
    }

    @Override
    protected void initBase() {
        super.initBase();
        imgs = new ArrayList<>();
        imgs.add("000");
        mData = new ReturnGoodsData();
        orderData = (OrderDetailsDataModel) getIntent().getSerializableExtra("data");
    }

    @Override
    protected void initViewAndListener() {
        super.initViewAndListener();
        mTvBaseTitle.setText("申请退款");
        mAdapter = new PhotoAdapter(imgs, this);
        mRecyclerView.setHasFixedSize(true);
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnHeaderClickListener(new OnClickListener() {
            @Override
            public void setOnClickListener(View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_item_comment_detail_delete_photo:
                        //点击的是删除按钮
                        if (imgs.size() == 6) {
                            if (imgs.contains("000")) {
                                imgs.remove(position);
                            } else {
                                imgs.remove(position);
                                imgs.add(0, "000");
                            }
                        } else {
                            imgs.remove(position);
                        }
                        imgAdapterNotify();
                        break;
                    case R.id.iv_item_comment_detail_photo:
                        //点击的photoView
                        if (position == 0 && "000".equals(imgs.get(position))) {
                            //弹出对话框
                            showPicDiaog();
                        } else {
                            Intent intent = new Intent(ApplyReturnMoneyActivity.this, PhotoDetailActivity.class);
                            intent.putExtra("uri", imgs.get(position));
                            startActivity(intent);
                        }
                        break;
                }
            }
        });
    }

    @OnClick({R.id.ll_base_back, R.id.tv_return_desc, R.id.tv_submit, R.id.tv_return_reason})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_base_back:
                finish();
                break;
            case R.id.tv_return_desc:
                Intent intent = new Intent(this, CommonWebviewActivity.class);
                intent.putExtra("type", PersonalConsts.REFUNDS_DESC);
                startActivity(intent);
                break;
            case R.id.tv_return_reason:
                showReasonDialog();
                break;
            case R.id.tv_submit:
                if (mData != null) {
                    mData.orderId = orderData.orderId;
                    mData.reasonDesc = mEtReturnDesc.getText().toString();
                    mData.returnMoney = Double.parseDouble(mEtReturnMoney.getText().toString());
                    mPresenter.submitData(mData);
                } else {
                    ToastUtils.showToast(this, "请填写内容");
                }
                break;
        }
    }


    @Override
    public void dismissPicDiaog() {
        if (picDialog != null && picDialog.isShowing()) {
            picDialog.dismiss();
        }
    }

    @Override
    public void showReasonDialog() {
        reasonDialog = new ReasonDialog(this);
        reasonDialog.show();
        if (mData.reasonCode != -1) {
            reasonDialog.getAdapter().setSelectPosition(mData.reasonCode);
        }
        reasonDialog.getAdapter().setListener(new OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                dismissReasonDialog();
                mData.reasonCode = position;
                mTvReturnReason.setText("退款原因:  " + PersonalConsts.getRefundsReasonList().get(position));
            }
        });

    }

    @Override
    public void dismissReasonDialog() {
        if (reasonDialog != null && reasonDialog.isShowing()) {
            reasonDialog.dismiss();
        }
    }

    @Override
    public void showPicDiaog() {

        picDialog = new IosDialog(this);
        picDialog.setContent("拍照上传");
        picDialog.setSureText("相册选择");
        picDialog.setCancelText("取消");
        picDialog.show();
        picDialog.setIosDialogCallBack(new IosDialog.IosDialogCallBack() {
            @Override
            public void onSure() {
                RxPermissions.getInstance(ApplyReturnMoneyActivity.this).request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
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
                picDialog.dismiss();
            }
        });
        picDialog.setDialogContentCallBack(new IosDialog.IosDialogContentCallBack() {
            @Override
            public void onContent() {
                RxPermissions.getInstance(ApplyReturnMoneyActivity.this).request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            //拍照
                            File file = new File(FilePathConsts.PIC_PATH);
                            if (!file.exists()) {
                                file.mkdirs();
                            }
                            cameraFile = new File(file, UUIDUtils.getUUID() + ".jpg");
                            CameraUtil.openCamera(ApplyReturnMoneyActivity.this, cameraFile);
                        }
                    }
                });
            }
        });
    }

    @Override
    public void toRefundDetails(ReturnGoodsData data) {
        Intent intent=new Intent(this,RefundsDetailActivity.class);
        intent.putExtra("orderId",data.orderId);
        startActivity(intent);
    }

    @Override
    public void imgAdapterNotify() {
        mAdapter.notifyItemRangeChanged(0, imgs.size());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CameraUtil.PHOTO_REQUEST_CAREMA) {
            if (cameraFile != null && cameraFile.length() > 0) {
                mPresenter.compresPic(ApplyReturnMoneyActivity.this, imgs, cameraFile);
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
                        mPresenter.compresPic(ApplyReturnMoneyActivity.this, imgs, file);
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
            mPresenter.compresPic(this, imgs, new File(path));
        }
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
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscribe != null && !mSubscribe.isUnsubscribed()) {
            mSubscribe.unsubscribe();
        }
    }
}
