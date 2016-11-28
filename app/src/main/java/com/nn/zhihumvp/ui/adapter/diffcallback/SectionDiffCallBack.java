package com.nn.zhihumvp.ui.adapter.diffcallback;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.base.BaseDiffUtilCallBack;
import com.nn.zhihumvp.model.vo.SectionVO;

import java.util.List;

/**
 * 栏目列表Diff
 *
 * @author LiuZongRui  16/11/23
 */

public class SectionDiffCallBack extends BaseDiffUtilCallBack<SectionVO> {

    public SectionDiffCallBack(@NonNull List<SectionVO> oldItems, @NonNull List<SectionVO> newItems) {
        super(oldItems, newItems);
    }

    @Override
    protected boolean _areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldItems.get(oldItemPosition).getId().equals(mNewItems.get(newItemPosition).getId());
    }

    @Override
    protected boolean _areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return true; // 只要id相同,数据相同
    }

    @Override
    protected Object _getChangePayload(int oldItemPosition, int newItemPosition) {
        return null;
    }
}
