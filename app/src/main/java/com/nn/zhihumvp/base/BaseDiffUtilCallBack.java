package com.nn.zhihumvp.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * @author LiuZongRui  16/11/18
 */

public abstract class BaseDiffUtilCallBack<M> extends DiffUtil.Callback {

    protected List<M> mOldItems, mNewItems;
    private int viewTypeCount = 1;

    public BaseDiffUtilCallBack(@NonNull List<M> oldItems, @NonNull List<M> newItems, int viewTypeCount) {
        this.mOldItems = oldItems;
        this.mNewItems = newItems;
        this.viewTypeCount = viewTypeCount;
    }

    public BaseDiffUtilCallBack(@NonNull List<M> oldItems, @NonNull List<M> newItems) {
        this.mOldItems = oldItems;
        this.mNewItems = newItems;
    }

    @Override
    public int getOldListSize() {
        return this.mOldItems != null ? this.mOldItems.size() + viewTypeCount - 1 : viewTypeCount - 1;
    }

    @Override
    public int getNewListSize() {
        return this.mNewItems != null ? this.mNewItems.size() + viewTypeCount - 1 : viewTypeCount - 1;
    }

    /**
     * 判断对象是否相等
     *
     * @param oldItemPosition
     * @param newItemPosition
     * @return
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return this._areItemsTheSame(oldItemPosition, newItemPosition);
    }

    /**
     * 判断内容是否相等, 不同返回false,相同返回true
     * areItemsTheSame为true这边才会调用
     *
     * @param oldItemPosition
     * @param newItemPosition
     * @return
     */
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return this._areContentsTheSame(oldItemPosition, newItemPosition);
    }

    /**
     * areContentsTheSame 返回false才会调用这个方法
     * 去得到这个Item（有哪些）改变的payload
     *
     * @param oldItemPosition
     * @param newItemPosition
     * @return
     */
    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        super.getChangePayload(oldItemPosition, newItemPosition);
        return this._getChangePayload(oldItemPosition, newItemPosition);
    }

    protected abstract boolean _areItemsTheSame(int oldItemPosition, int newItemPosition);

    protected abstract boolean _areContentsTheSame(int oldItemPosition, int newItemPosition);

    protected abstract Object _getChangePayload(int oldItemPosition, int newItemPosition);
}
