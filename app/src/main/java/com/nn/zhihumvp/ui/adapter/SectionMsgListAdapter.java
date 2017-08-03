package com.nn.zhihumvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nn.zhihumvp.R;
import com.nn.zhihumvp.base.BaseAdapter;
import com.nn.zhihumvp.model.vo.SectionMsgVO;
import com.nn.zhihumvp.util.GlideUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author LiuZongRui  16/11/23
 */

public class SectionMsgListAdapter extends BaseAdapter<SectionMsgVO> {

    private Context mContext;

    public SectionMsgListAdapter(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected int _bindLayout(int viewType) {
        return R.layout.item_section_msg;
    }

    @Override
    protected RecyclerView.ViewHolder _createViewHolder(View itemView, int viewType) {
        return new ItemViewHolder(itemView);
    }

    @Override
    protected void _onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder vh = (ItemViewHolder) holder;
        SectionMsgVO vo = _getItems().get(position);
        if (vo != null) {
            GlideUtil.loadImage(mContext, vo.getImage(), vh.ivLogo);
            vh.tvDate.setText(vo.getDate());
            vh.tvTitle.setText(vo.getTitle());
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_logo)
        ImageView ivLogo;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_date)
        TextView tvDate;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
