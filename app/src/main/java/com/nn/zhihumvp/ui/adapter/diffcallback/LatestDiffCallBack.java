package com.nn.zhihumvp.ui.adapter.diffcallback;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.base.BaseDiffUtilCallBack;
import com.nn.zhihumvp.model.vo.LatestNewsVO;

import java.util.List;

/**
 * @author LiuZongRui  16/11/18
 */

public class LatestDiffCallBack extends BaseDiffUtilCallBack<LatestNewsVO> {

    private List<String> mOldImageUrlList, mNewImageUrlList;

    private LatestDiffCallBack(@NonNull List<LatestNewsVO> oldItems, @NonNull List<LatestNewsVO> newItems) {
        super(oldItems, newItems, 2);
    }

    public LatestDiffCallBack(@NonNull List<LatestNewsVO> oldItems, @NonNull List<LatestNewsVO> newItems, @NonNull List<String> oldImageUrlList, @NonNull List<String> newImageUrlList) {
        this(oldItems, newItems);
        this.mOldImageUrlList = oldImageUrlList;
        this.mNewImageUrlList = newImageUrlList;
    }

    @Override
    protected boolean _areItemsTheSame(int oldItemPosition, int newItemPosition) {
        if (oldItemPosition != 0) {
            return mOldItems.get(oldItemPosition - 1).getId().equals(mNewItems.get(newItemPosition - 1).getId()); // 通过唯一的id判断是否是同一个对象
        } else {
            // position == 0为轮播图
            int oldLen = mOldImageUrlList.size();
            int newLen = mNewImageUrlList.size();
            if (oldLen != newLen) {
                return false;
            }
            for (int i = 0; i < oldLen; i++) {
                String oldUrl = mOldImageUrlList.get(i);
                if (!oldUrl.equals(mNewImageUrlList.get(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    protected boolean _areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return true; // 只要id相同,数据就相同
    }

    @Override
    protected Object _getChangePayload(int oldItemPosition, int newItemPosition) {
        return null;
    }
}
