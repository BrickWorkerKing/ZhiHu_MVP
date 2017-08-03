package com.nn.zhihumvp.contract;

import com.nn.zhihumvp.base.IBaseView;
import com.nn.zhihumvp.base.IBaseViewPresenter;
import com.nn.zhihumvp.model.vo.StartImageVO;

/**
 * @author LiuZongRui  16/11/14
 */

public interface WelComeContract {

    interface View extends IBaseView<StartImageVO> {
        void toMainActivity();
    }

    interface Presenter extends IBaseViewPresenter<StartImageVO> {

        void loadImage();

        void toMainActivity();
    }
}
