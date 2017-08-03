package com.nn.zhihumvp.contract;

import com.nn.zhihumvp.base.IBaseListView;
import com.nn.zhihumvp.base.IBaseListViewPresenter;
import com.nn.zhihumvp.base.IBaseView;
import com.nn.zhihumvp.model.vo.SectionMsgVO;

import java.util.List;

/**
 * @author LiuZongRui  16/11/22
 */

public interface SectionMsgListContract {

    interface View extends IBaseListView<List<SectionMsgVO>> {
    }

    interface Presenter extends IBaseListViewPresenter<List<SectionMsgVO>> {
        void loadSectionMsgList(String id, boolean isRefresh);
    }

}
