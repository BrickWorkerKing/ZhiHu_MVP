package com.nn.zhihumvp.base;

import android.support.annotation.NonNull;

/**
 * @author LiuZongRui  16/11/14
 */

public interface IBaseView<T> {

    /**
     * 显示加载提示
     */
    void showLoadHint();

    /**
     * 关闭加载提示
     */
    void hideLoadHint();

    /**
     * 显示数据
     *
     * @param t
     */
    void showData(@NonNull T t, boolean isRefresh);

    /**
     * 显示错误数据
     *
     * @param error
     */
    void showError(String error, boolean isRefresh);
}
