package com.nn.zhihumvp.contract;

import com.nn.zhihumvp.base.IBaseListView;
import com.nn.zhihumvp.base.IBaseListViewPresenter;
import com.nn.zhihumvp.base.IBaseView;
import com.nn.zhihumvp.model.vo.SectionVO;

import java.util.List;

/**
 * @author LiuZongRui  16/11/21
 */
public interface SectionListContract {

    interface View extends IBaseListView<List<SectionVO>> {
    }

    interface Presenter extends IBaseListViewPresenter<List<SectionVO>> {
        void loadSectionList(boolean isRefresh);
    }

}
