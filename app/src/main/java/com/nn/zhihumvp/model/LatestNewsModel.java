package com.nn.zhihumvp.model;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.contract.LatestNewsContract;
import com.nn.zhihumvp.helper.ApiManager;
import com.nn.zhihumvp.helper.rx.RxSchedulersHelper;
import com.nn.zhihumvp.model.dto.LatestNewsDTO;
import com.nn.zhihumvp.model.vo.LatestNewsVO;

import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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
                .map(new Function<LatestNewsDTO, Map<String, List<LatestNewsVO>>>() {
                    @Override
                    public Map<String, List<LatestNewsVO>> apply(@io.reactivex.annotations.NonNull LatestNewsDTO latestNewsDTO) throws Exception {
                        return latestNewsDTO.transform();
                    }
                })
                .compose(RxSchedulersHelper.<Map<String, List<LatestNewsVO>>>io_main())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Disposable disposable) throws Exception {
                        if (isRefresh) {
                            presenter.onRefresh();
                        } else {
                            presenter.onLoadStart();
                        }
                    }
                })
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (isRefresh) {
                            presenter.onRefreshEnd();
                        } else {
                            presenter.onLoadEnd();
                        }
                    }
                })
                .subscribe(new Consumer<Map<String, List<LatestNewsVO>>>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Map<String, List<LatestNewsVO>> stringListMap) throws Exception {
                        presenter.onLoadDataSuccess(stringListMap, isRefresh);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        presenter.onLoadDataFail(throwable.getMessage(), isRefresh);
                    }
                });
    }


}
