package com.nn.zhihumvp.presenter;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.contract.WelComeContract;
import com.nn.zhihumvp.helper.rx.RxSchedulersHelper;
import com.nn.zhihumvp.helper.rx.RxSubscriptionHelper;
import com.nn.zhihumvp.model.WelComeModel;
import com.nn.zhihumvp.model.vo.StartImageVO;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * @author LiuZongRui  16/11/14
 */

public class WelComePresenter extends RxSubscriptionHelper implements WelComeContract.Presenter {

    private WelComeContract.View mWelcomeView;
    private WelComeModel mWelcomeModel;

    public WelComePresenter(@NonNull WelComeContract.View welcomeView) {
        this.mWelcomeView = welcomeView;
        this.mWelcomeModel = new WelComeModel(this);
    }

    @Override
    public void loadImage() {
        rxAdd(this.mWelcomeModel.loadData());
    }

    @Override
    public void toMainActivity() {
        rxAdd(Observable.timer(2, TimeUnit.SECONDS)
                .compose(RxSchedulersHelper.<Long>io_main())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        mWelcomeView.toMainActivity();
                    }
                }));
    }

    @Override
    public void onLoadDataSuccess(@NonNull StartImageVO startImageVO, boolean isRefresh) {
        this.mWelcomeView.showData(startImageVO, isRefresh);
    }

    @Override
    public void onLoadDataFail(@NonNull String error, boolean isRefresh) {
        this.mWelcomeView.showError(error, isRefresh);
    }

    @Override
    public void onDetachView() {
        this.mWelcomeView = null;
        unSubscribe();
    }

    @Override
    public void onLoadStart() {

    }

    @Override
    public void onLoadEnd() {

    }
}
