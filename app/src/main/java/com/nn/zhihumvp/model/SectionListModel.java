package com.nn.zhihumvp.model;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.contract.SectionListContract;
import com.nn.zhihumvp.helper.ApiManager;
import com.nn.zhihumvp.helper.rx.RxException;
import com.nn.zhihumvp.helper.rx.RxUtils;
import com.nn.zhihumvp.model.dto.SectionListDTO;
import com.nn.zhihumvp.model.vo.SectionVO;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


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
                .compose(RxUtils.<SectionListDTO, List<SectionVO>>transform_data())
                .compose(RxUtils.<List<SectionVO>>io_main())
                .compose(RxUtils.<List<SectionVO>>load_list_start_end(presenter))
                .subscribe(new Consumer<List<SectionVO>>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull List<SectionVO> sectionMsgVOs) throws Exception {
                        presenter.onLoadDataSuccess(sectionMsgVOs, isRefresh);
                    }
                }, new RxException<>(new Consumer<Throwable>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                        presenter.onLoadDataFail(throwable.getMessage(), isRefresh);
                    }
                }));
    }

}
