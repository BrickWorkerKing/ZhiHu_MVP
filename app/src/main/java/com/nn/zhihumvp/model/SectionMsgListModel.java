package com.nn.zhihumvp.model;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.contract.SectionMsgListContract;
import com.nn.zhihumvp.helper.ApiManager;
import com.nn.zhihumvp.helper.rx.RxException;
import com.nn.zhihumvp.helper.rx.RxUtils;
import com.nn.zhihumvp.model.dto.SectionMsgListDTO;
import com.nn.zhihumvp.model.vo.SectionMsgVO;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

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
                .compose(RxUtils.<SectionMsgListDTO, List<SectionMsgVO>>transform_data())
                .compose(RxUtils.<List<SectionMsgVO>>io_main())
                .compose(RxUtils.<List<SectionMsgVO>>load_list_start_end(presenter))
                .subscribe(new Consumer<List<SectionMsgVO>>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull List<SectionMsgVO> sectionMsgVOs) throws Exception {
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
