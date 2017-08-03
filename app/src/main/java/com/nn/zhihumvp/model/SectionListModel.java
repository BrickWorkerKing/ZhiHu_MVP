package com.nn.zhihumvp.model;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.contract.SectionListContract;
import com.nn.zhihumvp.helper.ApiManager;
import com.nn.zhihumvp.helper.rx.RxSchedulersHelper;
import com.nn.zhihumvp.helper.rx.RxSubscriber;
import com.nn.zhihumvp.model.dto.SectionListDTO;
import com.nn.zhihumvp.model.vo.SectionVO;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


/**
 * @author LiuZongRui  16/11/21
 */

public class SectionListModel {

    private SectionListContract.Presenter presenter;

    public SectionListModel(@NonNull SectionListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public Disposable loadData(final boolean isRefresh) {
        return ApiManager.getInstance().getApiService().getSectionList()
                .map(new Function<SectionListDTO, List<SectionVO>>() {
                    @Override
                    public List<SectionVO> apply(@io.reactivex.annotations.NonNull SectionListDTO sectionListDTO) throws Exception {
                        return sectionListDTO.transform();
                    }
                })
                .compose(RxSchedulersHelper.<List<SectionVO>>io_main())
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
                .subscribe(new Consumer<List<SectionVO>>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull List<SectionVO> sectionMsgVOs) throws Exception {
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
