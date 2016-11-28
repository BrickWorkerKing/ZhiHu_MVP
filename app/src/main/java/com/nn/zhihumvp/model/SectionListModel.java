package com.nn.zhihumvp.model;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.contract.SectionListContract;
import com.nn.zhihumvp.helper.ApiManager;
import com.nn.zhihumvp.helper.rx.RxSchedulersHelper;
import com.nn.zhihumvp.helper.rx.RxSubscriber;
import com.nn.zhihumvp.model.dto.SectionListDTO;
import com.nn.zhihumvp.model.vo.SectionVO;

import java.util.List;

import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 * @author LiuZongRui  16/11/21
 */

public class SectionListModel {

    private SectionListContract.Presenter presenter;

    public SectionListModel(@NonNull SectionListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public Subscription loadData(final boolean isRefresh) {
        return ApiManager.getInstance().getApiService().getSectionList()
                .map(new Func1<SectionListDTO, List<SectionVO>>() {
                    @Override
                    public List<SectionVO> call(SectionListDTO sectionListDTO) {
                        return sectionListDTO.transform();
                    }
                })
                .compose(RxSchedulersHelper.<List<SectionVO>>io_main())
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
                .subscribe(new RxSubscriber<List<SectionVO>>() {
                    @Override
                    public void _onNext(List<SectionVO> sectionVOs) {
                        presenter.onLoadDataSuccess(sectionVOs, isRefresh);
                    }

                    @Override
                    public void _onError(String msg) {
                        presenter.onLoadDataFail(msg, isRefresh);
                    }
                });
    }

}
