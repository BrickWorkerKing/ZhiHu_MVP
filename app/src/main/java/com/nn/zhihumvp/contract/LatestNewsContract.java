package com.nn.zhihumvp.contract;

import com.nn.zhihumvp.base.IBasePresenter;
import com.nn.zhihumvp.base.IBaseView;
import com.nn.zhihumvp.model.vo.LatestNewsVO;

import java.util.List;
import java.util.Map;

/**
 * @author LiuZongRui  16/11/16
 */

public interface LatestNewsContract {

    interface View extends IBaseView<Map<String, List<LatestNewsVO>>> {

        void showRefreshProgress();

        void hideRefreshProgress();

    }

    interface Presenter extends IBasePresenter<Map<String, List<LatestNewsVO>>> {

        void loadNewsList(boolean isRefresh);

        void onRefresh();

        void onRefreshEnd();
    }

}
