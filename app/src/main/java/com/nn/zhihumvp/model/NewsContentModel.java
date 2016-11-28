package com.nn.zhihumvp.model;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.contract.NewsContentContract;
import com.nn.zhihumvp.helper.ApiManager;
import com.nn.zhihumvp.helper.rx.RxSchedulersHelper;
import com.nn.zhihumvp.helper.rx.RxSubscriber;
import com.nn.zhihumvp.model.dto.NewsContentDTO;
import com.nn.zhihumvp.model.vo.NewsContentVO;

import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 * 请求新闻内容
 *
 * @author LiuZongRui  16/11/21
 */

public class NewsContentModel {

    private NewsContentContract.Presenter presenter;

    public NewsContentModel(@NonNull NewsContentContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public Subscription loadNewsContent(String id) {
        return ApiManager.getInstance().getApiService().getNewsContent(id)
                .map(new Func1<NewsContentDTO, NewsContentVO>() {
                    @Override
                    public NewsContentVO call(NewsContentDTO newsContentDTO) {
                        return newsContentDTO.transform();
                    }
                })
                .compose(RxSchedulersHelper.<NewsContentVO>io_main())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        presenter.onLoadStart();
                    }
                })
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        presenter.onLoadEnd();
                    }
                })
                .subscribe(new RxSubscriber<NewsContentVO>() {
                    @Override
                    public void _onNext(NewsContentVO o) {
                        presenter.onLoadDataSuccess(o, false);
                    }

                    @Override
                    public void _onError(String msg) {
                        presenter.onLoadDataFail(msg, false);
                    }
                });

    }

}
