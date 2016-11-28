package com.nn.zhihumvp.contract;

import com.nn.zhihumvp.base.IBasePresenter;
import com.nn.zhihumvp.base.IBaseView;
import com.nn.zhihumvp.model.vo.SectionMsgVO;

import java.util.List;

/**
 * @author LiuZongRui  16/11/22
 */

public interface SectionMsgListContract {

    interface View extends IBaseView<List<SectionMsgVO>> {
        void showRefreshProgress();

        void hideRefreshProgress();
    }

    interface Presenter extends IBasePresenter<List<SectionMsgVO>> {
        void loadSectionMsgList(String id, boolean isRefresh);

        void onRefresh();

        void onRefreshEnd();
    }

}
