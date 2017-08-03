package com.nn.zhihumvp.contract;

import com.nn.zhihumvp.base.IBaseListView;
import com.nn.zhihumvp.base.IBaseListViewPresenter;
import com.nn.zhihumvp.base.IBaseView;
import com.nn.zhihumvp.model.vo.LatestNewsVO;

import java.util.List;
import java.util.Map;

/**
 * @author LiuZongRui  16/11/16
 */

public interface LatestNewsContract {

    interface View extends IBaseListView<Map<String, List<LatestNewsVO>>> {

    }

    interface Presenter extends IBaseListViewPresenter<Map<String, List<LatestNewsVO>>> {

        void loadNewsList(boolean isRefresh);

    }

}
