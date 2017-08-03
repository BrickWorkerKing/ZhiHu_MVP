package com.nn.zhihumvp.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * @author LiuZongRui  16/11/18
 */

public abstract class BaseAdapter<M> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    protected List<M> mListItem;
    protected Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private int mViewTypeCount = 1;

    public BaseAdapter(@NonNull Context context) {
        this.mContext = context;
    }

    public BaseAdapter(@NonNull Context context, int viewTypeCount) {
        this.mContext = context;
        this.mViewTypeCount = viewTypeCount;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemVew = LayoutInflater.from(mContext).inflate(_bindLayout(viewType), parent, false);
        RecyclerView.ViewHolder holder = _createViewHolder(itemVew, viewType);
        _initListener(holder, itemVew, viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        _onBindViewHolder(holder, position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        if (payloads == null || payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            this._onBindViewHolder(holder, position, payloads);
        }
    }

    @Override
    public int getItemCount() {
        return mListItem != null ? mListItem.size() + mViewTypeCount - 1 : mViewTypeCount - 1;
    }

    protected void _initListener(final RecyclerView.ViewHolder viewHolder, final View itemView, int viewType) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(position, itemView);
                }
            }
        });
    }

    public void _setItems(@NonNull List<M> list, boolean isRefresh) {
        this.mListItem = list;
        if (!isRefresh) {
            // 第一次加载,全局刷新
            notifyDataSetChanged();
        }
    }

    public void _setOnItemClickListener(@Nullable OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public List<M> _getItems() {
        if (mListItem == null) {
            return Collections.emptyList();
        } else {
            return mListItem;
        }
    }

    protected abstract int _bindLayout(int viewType);

    protected abstract RecyclerView.ViewHolder _createViewHolder(View itemView, int viewType);

    protected abstract void _onBindViewHolder(RecyclerView.ViewHolder holder, int position);

    protected void _onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
    }

    /**
     * item点击回调接口
     */
    public interface OnItemClickListener {
        void onItemClick(int position, View itemView);
    }
}
