package com.nn.zhihumvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nn.zhihumvp.R;
import com.nn.zhihumvp.base.BaseAdapter;
import com.nn.zhihumvp.model.vo.SectionVO;
import com.nn.zhihumvp.util.GlideUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author LiuZongRui  16/11/21
 */

public class SectionListAdapter extends BaseAdapter<SectionVO> {

    private Context mContext;

    public SectionListAdapter(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected int _bindLayout(int viewType) {
        return R.layout.item_section;
    }

    @Override
    protected RecyclerView.ViewHolder _createViewHolder(View itemView, int viewType) {
        return new ItemViewHolder(itemView);
    }

    @Override
    protected void _onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder vh = (ItemViewHolder) holder;
        SectionVO section = _getItems().get(position);
        if (section != null) {
            GlideUtil.loadImage(mContext, section.getThumbnail(), vh.ivLogo);
            vh.tvName.setText(section.getName());
            vh.tvDescription.setText(section.getDescription());
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_logo)
        ImageView ivLogo;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_description)
        TextView tvDescription;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
