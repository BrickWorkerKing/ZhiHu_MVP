package com.nn.zhihumvp.contract;

import com.nn.zhihumvp.base.IBasePresenter;
import com.nn.zhihumvp.base.IBaseView;
import com.nn.zhihumvp.model.vo.SectionVO;

import java.util.List;

/**
 * @author LiuZongRui  16/11/21
 */
public interface SectionListContract {

    interface View extends IBaseView<List<SectionVO>> {
        void showRefreshProgress();

        void hideRefreshProgress();
    }

    interface Presenter extends IBasePresenter<List<SectionVO>> {
        void loadSectionList(boolean isRefresh);

        void onRefresh();

        void onRefreshEnd();
    }

}
