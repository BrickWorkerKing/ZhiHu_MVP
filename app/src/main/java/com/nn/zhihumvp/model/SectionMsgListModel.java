package com.nn.zhihumvp.model;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.contract.SectionMsgListContract;
import com.nn.zhihumvp.helper.ApiManager;
import com.nn.zhihumvp.helper.rx.RxSchedulersHelper;
import com.nn.zhihumvp.model.dto.SectionMsgListDTO;
import com.nn.zhihumvp.model.vo.SectionMsgVO;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * @author LiuZongRui  16/11/22
 */

public class SectionMsgListModel {

    private SectionMsgListContract.Presenter presenter;

    public SectionMsgListModel(@NonNull SectionMsgListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public Disposable loadSectionMsgList(String id, final boolean isRefresh) {
        return ApiManager.getInstance().getApiService().getSectionMsgList(id)
                .map(new Function<SectionMsgListDTO, List<SectionMsgVO>>() {
                    @Override
                    public List<SectionMsgVO> apply(@io.reactivex.annotations.NonNull SectionMsgListDTO sectionMsgListDTO) throws Exception {
                        return sectionMsgListDTO.transform();
                    }
                })
                .compose(RxSchedulersHelper.<List<SectionMsgVO>>io_main())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Disposable disposable) throws Exception {
                        if (isRefresh) {
                            presenter.onRefresh();
                        } else {
                            presenter.onLoadStart();
                        }
                    }
                })
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (isRefresh) {
                            presenter.onRefreshEnd();
                        } else {
                            presenter.onLoadEnd();
                        }
                    }
                })
                .subscribe(new Consumer<List<SectionMsgVO>>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull List<SectionMsgVO> sectionMsgVOs) throws Exception {
                        presenter.onLoadDataSuccess(sectionMsgVOs, isRefresh);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        presenter.onLoadDataFail(throwable.getMessage(), isRefresh);
                    }
                });
    }
}
