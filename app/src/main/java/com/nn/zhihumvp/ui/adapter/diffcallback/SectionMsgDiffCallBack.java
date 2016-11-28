package com.nn.zhihumvp.ui.adapter.diffcallback;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.base.BaseDiffUtilCallBack;
import com.nn.zhihumvp.model.vo.SectionMsgVO;

import java.util.List;

/**
 * 栏目消息
 *
 * @author LiuZongRui  16/11/23
 */

public class SectionMsgDiffCallBack extends BaseDiffUtilCallBack<SectionMsgVO> {

    public SectionMsgDiffCallBack(@NonNull List<SectionMsgVO> oldItems, @NonNull List<SectionMsgVO> newItems) {
        super(oldItems, newItems);
    }

    @Override
    protected boolean _areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldItems.get(oldItemPosition).getId().equals(mNewItems.get(newItemPosition).getId());
    }

    @Override
    protected boolean _areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return true;
    }

    @Override
    protected Object _getChangePayload(int oldItemPosition, int newItemPosition) {
        return null;
    }
}
