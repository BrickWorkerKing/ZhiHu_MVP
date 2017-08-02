package com.nn.zhihumvp.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nn.zhihumvp.R;
import com.nn.zhihumvp.base.BaseActivity;
import com.nn.zhihumvp.contract.WelComeContract;
import com.nn.zhihumvp.model.vo.StartImageVO;
import com.nn.zhihumvp.presenter.WelComePresenter;
import com.nn.zhihumvp.util.GlideUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WelComeActivity extends BaseActivity implements WelComeContract.View {

    @Bind(R.id.iv_screen)
    ImageView ivScreen;
    private View mDecorView;

    private WelComePresenter mWelComePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        mDecorView = getWindow().getDecorView();
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        this.mWelComePresenter = new WelComePresenter(this);
        this.mWelComePresenter.loadImage();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        // 全屏显示
        if (hasFocus && mDecorView != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                mDecorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
    }

    @Override
    public void showLoadHint() {

    }

    @Override
    public void hideLoadHint() {

    }

    @Override
    public void showData(@NonNull StartImageVO startImageVO, boolean isRefresh) {
        GlideUtil.loadImage(this, startImageVO.getImg(), ivScreen);
        this.mWelComePresenter.toMainActivity();
    }

    @Override
    public void showError(String error, boolean isRefresh) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        this.mWelComePresenter.toMainActivity();
    }

    @Override
    protected void onDestroy() {
        mWelComePresenter.onDetachView();
        super.onDestroy();
    }

    @Override
    public void toMainActivity() {
        _gotoActivity(MainActivity.class, null);
        finish();
    }
}
