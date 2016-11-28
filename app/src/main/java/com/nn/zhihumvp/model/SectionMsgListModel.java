package com.nn.zhihumvp.model;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.contract.SectionMsgListContract;
import com.nn.zhihumvp.helper.ApiManager;
import com.nn.zhihumvp.helper.rx.RxSchedulersHelper;
import com.nn.zhihumvp.helper.rx.RxSubscriber;
import com.nn.zhihumvp.model.dto.SectionMsgListDTO;
import com.nn.zhihumvp.model.vo.SectionMsgVO;

import java.util.List;

import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 * @author LiuZongRui  16/11/22
 */

public class SectionMsgListModel {

    private SectionMsgListContract.Presenter presenter;

    public SectionMsgListModel(@NonNull SectionMsgListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public Subscription loadSectionMsgList(String id, final boolean isRefresh) {
        return ApiManager.getInstance().getApiService().getSectionMsgList(id)
                .map(new Func1<SectionMsgListDTO, List<SectionMsgVO>>() {
                    @Override
                    public List<SectionMsgVO> call(SectionMsgListDTO sectionMsgListDTO) {
                        return sectionMsgListDTO.transform();
                    }
                })
                .compose(RxSchedulersHelper.<List<SectionMsgVO>>io_main())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if (isRefresh) {
                            presenter.onRefresh();
                        } else {
                            presenter.onLoadStart();
                        }
                    }
                })
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        if (isRefresh) {
                            presenter.onRefreshEnd();
                        } else {
                            presenter.onLoadEnd();
                        }
                    }
                })
                .subscribe(new RxSubscriber<List<SectionMsgVO>>() {
                    @Override
                    public void _onNext(List<SectionMsgVO> sectionMsgVOs) {
                        presenter.onLoadDataSuccess(sectionMsgVOs, isRefresh);
                    }

                    @Override
                    public void _onError(String msg) {
                        presenter.onLoadDataFail(msg, isRefresh);
                    }
                });
    }
}
