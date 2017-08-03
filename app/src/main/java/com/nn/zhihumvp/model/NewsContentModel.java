package com.nn.zhihumvp.model;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.contract.NewsContentContract;
import com.nn.zhihumvp.helper.ApiManager;
import com.nn.zhihumvp.helper.rx.RxException;
import com.nn.zhihumvp.helper.rx.RxUtils;
import com.nn.zhihumvp.model.dto.NewsContentDTO;
import com.nn.zhihumvp.model.vo.NewsContentVO;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

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
                .compose(RxUtils.<NewsContentDTO, NewsContentVO>transform_data())
                .compose(RxUtils.<NewsContentVO>io_main())
                .compose(RxUtils.<NewsContentVO>load_start_end(presenter))
                .subscribe(new Consumer<NewsContentVO>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull NewsContentVO newsContentVO) throws Exception {
                        presenter.onLoadDataSuccess(newsContentVO);
                    }
                }, new RxException<>(new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        presenter.onLoadDataFail(throwable.getMessage());
                    }
                }));
    }

}
