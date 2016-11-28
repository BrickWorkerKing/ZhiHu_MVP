package com.nn.zhihumvp.presenter;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.contract.NewsContentContract;
import com.nn.zhihumvp.helper.rx.RxSubscriptionHelper;
import com.nn.zhihumvp.model.NewsContentModel;
import com.nn.zhihumvp.model.vo.NewsContentVO;

/**
 * @author LiuZongRui  16/11/21
 */

public class NewsContentPresenter extends RxSubscriptionHelper implements NewsContentContract.Presenter {

    private NewsContentContract.View contentView;
    private NewsContentModel contentModel;

    public NewsContentPresenter(@NonNull NewsContentContract.View view) {
        this.contentView = view;
        this.contentModel = new NewsContentModel(this);
    }

    @Override
    public void loadNewsContent(@NonNull String id) {
        rxAdd(this.contentModel.loadNewsContent(id));
    }

    @Override
    public void onDetachView() {
        unSubscribe();
        this.contentView = null;
    }

    @Override
    public void onLoadStart() {
        this.contentView.showLoadHint();
    }

    @Override
    public void onLoadEnd() {
        this.contentView.hideLoadHint();
    }

    @Override
    public void onLoadDataSuccess(@NonNull NewsContentVO newsContent, boolean isRefresh) {
        this.contentView.showData(newsContent, isRefresh);
    }

    @Override
    public void onLoadDataFail(@NonNull String error, boolean isRefresh) {
        this.contentView.showError(error, isRefresh);
    }
}
