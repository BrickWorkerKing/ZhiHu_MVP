package com.nn.zhihumvp.contract;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.base.IBasePresenter;
import com.nn.zhihumvp.base.IBaseView;
import com.nn.zhihumvp.model.vo.NewsContentVO;

/**
 * 新闻详情
 *
 * @author LiuZongRui  16/11/21
 */

public interface NewsContentContract {

    interface View extends IBaseView<NewsContentVO> {

    }

    interface Presenter extends IBasePresenter<NewsContentVO> {
        void loadNewsContent(@NonNull String id);
    }
}
