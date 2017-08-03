package com.nn.zhihumvp.presenter;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.contract.SectionMsgListContract;
import com.nn.zhihumvp.helper.rx.RxDisposableManager;
import com.nn.zhihumvp.model.SectionMsgListModel;
import com.nn.zhihumvp.model.vo.SectionMsgVO;

import java.util.List;

/**
 * @author LiuZongRui  16/11/23
 */

public class SectionMsgListPresenter extends RxDisposableManager implements SectionMsgListContract.Presenter {

    private SectionMsgListModel msgListModel;
    private SectionMsgListContract.View view;

    public SectionMsgListPresenter(@NonNull SectionMsgListContract.View view) {
        this.view = view;
        this.msgListModel = new SectionMsgListModel(this);
    }

    @Override
    public void loadSectionMsgList(String id, boolean isRefresh) {
        rxAdd(msgListModel.loadSectionMsgList(id, isRefresh));
    }

    @Override
    public void onRefresh() {
        view.showRefreshProgress();
    }

    @Override
    public void onRefreshEnd() {
        view.hideRefreshProgress();
    }

    @Override
    public void onDetachView() {
        view = null;
        disposableAll();
    }

    @Override
    public void onLoadStart() {
        view.showLoadHint();
    }

    @Override
    public void onLoadEnd() {
        view.hideLoadHint();
    }

    @Override
    public void onLoadDataSuccess(@NonNull List<SectionMsgVO> sectionMsgVOs, boolean isRefresh) {
        view.showData(sectionMsgVOs, isRefresh);
    }

    @Override
    public void onLoadDataFail(@NonNull String error, boolean isRefresh) {
        view.showError(error, isRefresh);
    }
}
