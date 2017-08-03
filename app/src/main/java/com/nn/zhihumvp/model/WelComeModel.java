package com.nn.zhihumvp.model;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.contract.WelComeContract;
import com.nn.zhihumvp.helper.ApiManager;
import com.nn.zhihumvp.helper.rx.RxException;
import com.nn.zhihumvp.helper.rx.RxUtils;
import com.nn.zhihumvp.model.dto.StartImageDTO;
import com.nn.zhihumvp.model.vo.StartImageVO;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * @author LiuZongRui  16/11/14
 *         model需要将数据传递给Presenter
 */

public class WelComeModel {

    @NonNull
    private WelComeContract.Presenter presenter;

    public WelComeModel(@NonNull WelComeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public Disposable loadData() {
        return ApiManager.getInstance().getApiService().getWelComePic()
                .compose(RxUtils.<StartImageDTO, StartImageVO>transform_data())
                .compose(RxUtils.<StartImageVO>io_main())
                .subscribe(new Consumer<StartImageVO>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull StartImageVO startImageVO) throws Exception {
                        presenter.onLoadDataSuccess(startImageVO);
                    }
                }, new RxException<>(new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        presenter.onLoadDataFail(throwable.getMessage());
                    }
                }));
    }
}
