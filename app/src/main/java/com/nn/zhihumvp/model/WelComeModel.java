package com.nn.zhihumvp.model;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.contract.WelComeContract;
import com.nn.zhihumvp.helper.ApiManager;
import com.nn.zhihumvp.helper.rx.RxSchedulersHelper;
import com.nn.zhihumvp.helper.rx.RxSubscriber;
import com.nn.zhihumvp.model.dto.StartImageDTO;
import com.nn.zhihumvp.model.vo.StartImageVO;

import org.reactivestreams.Subscription;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;


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
         ApiManager.getInstance().getApiService().getWelComePic()
                .map(new Function<StartImageDTO, StartImageVO>() {
                    @Override
                    public StartImageVO apply(@io.reactivex.annotations.NonNull StartImageDTO startImageDTO) throws Exception {
                        return startImageDTO.transform();
                    }
                })
                .compose(RxSchedulersHelper.<StartImageVO>io_main())
                .subscribe(new Observer<StartImageVO>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull StartImageVO startImageVO) {

                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return null;




//                .subscribe(new RxSubscriber<StartImageVO>() {
//                    @Override
//                    public void onSubscribe(Subscription s) {
//
//                    }
//
//                    @Override
//                    public void _onNext(StartImageVO startImageVO) {
//                        presenter.onLoadDataSuccess(startImageVO, false);
//                    }
//
//                    @Override
//                    public void _onError(String msg) {
//                        presenter.onLoadDataFail(msg, false);
//                    }
//                });
    }
}
