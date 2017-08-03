package com.nn.zhihumvp.model;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.contract.NewsContentContract;
import com.nn.zhihumvp.helper.ApiManager;
import com.nn.zhihumvp.helper.rx.RxSchedulersHelper;
import com.nn.zhihumvp.helper.rx.RxSubscriber;
import com.nn.zhihumvp.model.dto.NewsContentDTO;
import com.nn.zhihumvp.model.vo.NewsContentVO;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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

    public Disposable loadNewsContent(String id) {
        return ApiManager.getInstance().getApiService().getNewsContent(id)
                .map(new Function<NewsContentDTO, NewsContentVO>() {
                    @Override
                    public NewsContentVO apply(@io.reactivex.annotations.NonNull NewsContentDTO newsContentDTO) throws Exception {
                        return newsContentDTO.transform();
                    }
                })
                .compose(RxSchedulersHelper.<NewsContentVO>io_main())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Disposable disposable) throws Exception {
                        presenter.onLoadStart();
                    }
                })
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        presenter.onLoadEnd();
                    }
                })
                .subscribe(new Consumer<NewsContentVO>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull NewsContentVO newsContentVO) throws Exception {
                        presenter.onLoadDataSuccess(newsContentVO, false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        presenter.onLoadDataFail(throwable.getMessage(), false);
                    }
                });
    }

}
