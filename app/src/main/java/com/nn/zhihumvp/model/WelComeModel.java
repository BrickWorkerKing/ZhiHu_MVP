package com.nn.zhihumvp.model;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.contract.WelComeContract;
import com.nn.zhihumvp.helper.ApiManager;
import com.nn.zhihumvp.helper.rx.RxSchedulersHelper;
import com.nn.zhihumvp.helper.rx.RxSubscriber;
import com.nn.zhihumvp.model.dto.StartImageDTO;
import com.nn.zhihumvp.model.vo.StartImageVO;

import rx.Subscription;
import rx.functions.Func1;

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

    public Subscription loadData() {
        return ApiManager.getInstance().getApiService().getWelComePic()
                .map(new Func1<StartImageDTO, StartImageVO>() {
                    @Override
                    public StartImageVO call(StartImageDTO startImageDTO) {
                        return startImageDTO.transform();
                    }
                })
                .compose(RxSchedulersHelper.<StartImageVO>io_main())
                .subscribe(new RxSubscriber<StartImageVO>() {
                    @Override
                    public void _onNext(StartImageVO startImageVO) {
                        presenter.onLoadDataSuccess(startImageVO, false);
                    }

                    @Override
                    public void _onError(String msg) {
                        presenter.onLoadDataFail(msg, false);
                    }
                });
    }
}
