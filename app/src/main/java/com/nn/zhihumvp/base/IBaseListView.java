package com.nn.zhihumvp.base;

import android.support.annotation.NonNull;

/**
 * @author LiuZongRui  16/11/14
 */

public interface IBaseListView<T> {

    /**
     * 显示加载提示
     */
    void showRefreshProgress();

    /**
     * 关闭加载提示
     */
    void hideRefreshProgress();

    /**
     * 显示数据
     *
     * @param t
     * @param isRefresh
     */
    void showData(@NonNull T t, boolean isRefresh);

    /**
     * 显示错误数据
     *
     * @param error
     * @param isRefresh
     */
    void showError(@NonNull String error, boolean isRefresh);
}
