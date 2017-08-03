package com.nn.zhihumvp.presenter;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.contract.LatestNewsContract;
import com.nn.zhihumvp.helper.rx.RxDisposableManager;
import com.nn.zhihumvp.model.LatestNewsModel;
import com.nn.zhihumvp.model.vo.LatestNewsVO;

import java.util.List;
import java.util.Map;

/**
 * @author LiuZongRui  16/11/16
 */

public class LatestNewsPresenter extends RxDisposableManager implements LatestNewsContract.Presenter {

    private LatestNewsContract.View mView;
    private LatestNewsModel mModel;

    public LatestNewsPresenter(LatestNewsContract.View view) {
        this.mView = view;
        mModel = new LatestNewsModel(this);
    }

    @Override
    public void loadNewsList(boolean isRefresh) {
        rxAdd(mModel.loadLatestNews(isRefresh));
    }

    @Override
    public void onRefresh() {
        mView.showRefreshProgress();
    }

    @Override
    public void onRefreshEnd() {
        mView.hideRefreshProgress();
    }

    @Override
    public void onDetachView() {
        mView = null;
        disposableAll();
    }

    @Override
    public void onLoadStart() {
        mView.showLoadHint();
    }

    @Override
    public void onLoadEnd() {
        mView.hideLoadHint();
    }

    @Override
    public void onLoadDataSuccess(@NonNull Map<String, List<LatestNewsVO>> latestNewsMap, boolean isRefresh) {
        mView.showData(latestNewsMap, isRefresh);
    }

    @Override
    public void onLoadDataFail(@NonNull String error, boolean isRefresh) {
        mView.showError(error, isRefresh);
    }
}
