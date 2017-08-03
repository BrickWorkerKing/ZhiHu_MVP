package com.nn.zhihumvp.base;

import android.support.annotation.NonNull;

/**
 * 非列表界面Presenter
 * @author LiuZongRui  16/11/14
 */
 public interface IBaseViewPresenter<T> extends IBasePresenter {

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
    void onLoadDataSuccess(@NonNull T t);

    /**
     * 加载失败
     *
     * @param error
     */
    void onLoadDataFail(@NonNull String error);
}
