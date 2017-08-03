package com.nn.zhihumvp.contract;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.base.IBaseView;
import com.nn.zhihumvp.base.IBaseViewPresenter;
import com.nn.zhihumvp.model.vo.NewsContentVO;

/**
 * 新闻详情
 *
 * @author LiuZongRui  16/11/21
 */

public interface NewsContentContract {

    interface View extends IBaseView<NewsContentVO> {

    }

    interface Presenter extends IBaseViewPresenter<NewsContentVO> {
        void loadNewsContent(@NonNull String id);
    }
}
