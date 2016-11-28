package com.nn.zhihumvp.base;

import android.support.annotation.NonNull;

/**
 * @author LiuZongRui  16/11/14
 */

public interface IBasePresenter<T> {

    /**
     * view被销毁
     */
    void onDetachView();

    /**
     * 数据加载开始
     */
    void onLoadStart();

    /**
     * 数据加载结束
     */
    void onLoadEnd();

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
