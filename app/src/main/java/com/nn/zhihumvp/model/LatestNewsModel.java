package com.nn.zhihumvp.model;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.contract.LatestNewsContract;
import com.nn.zhihumvp.helper.ApiManager;
import com.nn.zhihumvp.helper.rx.RxSchedulersHelper;
import com.nn.zhihumvp.helper.rx.RxSubscriber;
import com.nn.zhihumvp.model.dto.LatestNewsDTO;
import com.nn.zhihumvp.model.vo.LatestNewsVO;

import java.util.List;
import java.util.Map;

import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Func1;

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

    public Subscription loadLatestNews(final boolean isRefresh) {
        return ApiManager.getInstance().getApiService().getLatestNews()
                .map(new Func1<LatestNewsDTO, Map<String, List<LatestNewsVO>>>() {
                    @Override
                    public Map<String, List<LatestNewsVO>> call(LatestNewsDTO latestNewsDTO) {
                        return latestNewsDTO.transform();
                    }
                })
                .compose(RxSchedulersHelper.<Map<String, List<LatestNewsVO>>>io_main())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if (isRefresh) {
                            presenter.onRefresh();
                        } else {
                            presenter.onLoadStart();
                        }
                    }
                })
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        if (isRefresh) {
                            presenter.onRefreshEnd();
                        } else {
                            presenter.onLoadEnd();
                        }
                    }
                })
                .subscribe(new RxSubscriber<Map<String, List<LatestNewsVO>>>() {
                    @Override
                    public void _onNext(Map<String, List<LatestNewsVO>> stringListMap) {
                        presenter.onLoadDataSuccess(stringListMap, isRefresh);
                    }

                    @Override
                    public void _onError(String msg) {
                        presenter.onLoadDataFail(msg, isRefresh);
                    }
                });
    }


}
