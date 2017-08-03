package com.nn.zhihumvp.model;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.contract.LatestNewsContract;
import com.nn.zhihumvp.helper.ApiManager;
import com.nn.zhihumvp.helper.rx.RxException;
import com.nn.zhihumvp.helper.rx.RxUtils;
import com.nn.zhihumvp.model.dto.LatestNewsDTO;
import com.nn.zhihumvp.model.vo.LatestNewsVO;

import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 最新新闻
 *
 * @author LiuZongRui  16/11/16
 */

public class LatestNewsModel {

    private LatestNewsContract.Presenter presenter;

    public LatestNewsModel(@NonNull LatestNewsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public Disposable loadLatestNews(final boolean isRefresh) {
        return ApiManager.getInstance().getApiService().getLatestNews()
                .compose(RxUtils.<LatestNewsDTO, Map<String, List<LatestNewsVO>>>transform_data())
                .compose(RxUtils.<Map<String, List<LatestNewsVO>>>io_main())
                .compose(RxUtils.<Map<String, List<LatestNewsVO>>>load_list_start_end(presenter))
                .subscribe(new Consumer<Map<String, List<LatestNewsVO>>>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Map<String, List<LatestNewsVO>> stringListMap) throws Exception {
                        presenter.onLoadDataSuccess(stringListMap, isRefresh);
                    }
                }, new RxException<>(new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        presenter.onLoadDataFail(throwable.getMessage(), isRefresh);
                    }
                }));
    }


}
