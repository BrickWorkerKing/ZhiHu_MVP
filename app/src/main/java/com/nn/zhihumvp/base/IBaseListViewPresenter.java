package com.nn.zhihumvp.base;

import android.support.annotation.NonNull;

/**
 * 列表界面Presenter
 * @author LiuZongRui  16/11/14
 */

public interface IBaseListViewPresenter<T> extends IBasePresenter{

    /**
     * 开始刷新
     */
    void onRefreshStart();

    /**
     * 刷新结束
     */
    void onRefreshEnd();

    /**
     * 加载成功
     *
     * @param t
     */
    void onLoadDataSuccess(@NonNull T t, boolean isRefresh);

    /**
     * 加载失败
     *
     * @param error
     */
    void onLoadDataFail(@NonNull String error, boolean isRefresh);
}
