package com.nn.zhihumvp.presenter;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.contract.WelComeContract;
import com.nn.zhihumvp.helper.rx.RxUtils;
import com.nn.zhihumvp.helper.rx.RxDisposableManager;
import com.nn.zhihumvp.model.WelComeModel;
import com.nn.zhihumvp.model.vo.StartImageVO;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * @author LiuZongRui  16/11/14
 */

public class WelComePresenter extends RxDisposableManager implements WelComeContract.Presenter {

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
                .compose(RxUtils.<Long>io_main())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Long aLong) throws Exception {
                        mWelcomeView.toMainActivity();
                    }
                }));
    }

    @Override
    public void onLoadDataSuccess(@NonNull StartImageVO startImageVO) {
        this.mWelcomeView.showData(startImageVO);
    }

    @Override
    public void onLoadDataFail(@NonNull String error) {
        this.mWelcomeView.showError(error);
    }

    @Override
    public void onDetachView() {
        this.mWelcomeView = null;
        disposableAll();
    }

    @Override
    public void onLoadStart() {

    }

    @Override
    public void onLoadEnd() {

    }
}
