package com.nn.zhihumvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nn.zhihumvp.R;
import com.nn.zhihumvp.base.BaseAdapter;
import com.nn.zhihumvp.model.vo.LatestNewsVO;
import com.nn.zhihumvp.util.GlideImageLoader;
import com.nn.zhihumvp.util.GlideUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author LiuZongRui 16/11/18
 */

public class LatestNewsAdapter extends BaseAdapter<LatestNewsVO> {

    private enum ItemType {
        BANNER,
        LIST_VIEW
    }

    private List<String> mImageList;

    public List<String> getImageList() {
        return mImageList != null ? mImageList : Collections.<String>emptyList();
    }

    public LatestNewsAdapter(@NonNull Context context) {
        super(context, 2); // 这边有两种item布局类型
    }

    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);
        if (position == 0) {
            return ItemType.BANNER.ordinal();
        } else {
            return ItemType.LIST_VIEW.ordinal();
        }
    }

    @Override
    protected int _bindLayout(int viewType) {
        return ItemType.BANNER.ordinal() == viewType ? R.layout.layout_banner : R.layout.item_latest_news;
    }

    @Override
    protected RecyclerView.ViewHolder _createViewHolder(View itemView, int viewType) {
        return ItemType.BANNER.ordinal() == viewType ? new BannerViewHolder(itemView) : new ItemViewHolder(itemView);
    }

    @Override
    protected void _onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            BannerViewHolder vh = (BannerViewHolder) holder;
            vh.bannerLayout.setImages(mImageList);
            vh.bannerLayout.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            vh.bannerLayout.setIndicatorGravity(BannerConfig.CENTER);
            vh.bannerLayout.setImageLoader(new GlideImageLoader());
            vh.bannerLayout.start();
        } else {
            ItemViewHolder vh = (ItemViewHolder) holder;
            LatestNewsVO storyBean = mListItem.get(position - 1);
            if (storyBean != null) {
                vh.tvTitle.setText(storyBean.getTitle());
                GlideUtil.loadImage(mContext, storyBean.getImage(), vh.ivImage);
            }
        }
    }

    public void setData(@NonNull List<LatestNewsVO> storyList, @NonNull List<String> imageList, boolean isRefresh) {
        this.mImageList = imageList;
        _setItems(storyList, isRefresh);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tvTitle;
        @BindView(R.id.iv_image)
        ImageView ivImage;

        ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

   static class BannerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.banner_layout)
        Banner bannerLayout;

        BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
