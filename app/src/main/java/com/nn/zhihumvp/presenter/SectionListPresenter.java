package com.nn.zhihumvp.presenter;

import android.support.annotation.NonNull;

import com.nn.zhihumvp.contract.SectionListContract;
import com.nn.zhihumvp.helper.rx.RxDisposableManager;
import com.nn.zhihumvp.model.SectionListModel;
import com.nn.zhihumvp.model.vo.SectionVO;

import java.util.List;

/**
 * @author LiuZongRui  16/11/21
 */

public class SectionListPresenter extends RxDisposableManager implements SectionListContract.Presenter {

    private SectionListContract.View sectionView;
    private SectionListModel listModel;

    public SectionListPresenter(SectionListContract.View view) {
        this.sectionView = view;
        listModel = new SectionListModel(this);
    }

    @Override
    public void loadSectionList(boolean isRefresh) {
        rxAdd(listModel.loadData(isRefresh));
    }

    @Override
    public void onRefresh() {
        sectionView.showRefreshProgress();
    }

    @Override
    public void onRefreshEnd() {
        sectionView.hideRefreshProgress();
    }

    @Override
    public void onDetachView() {
        this.sectionView = null;
        disposableAll();
    }

    @Override
    public void onLoadStart() {

    }

    @Override
    public void onLoadEnd() {

    }

    @Override
    public void onLoadDataSuccess(@NonNull List<SectionVO> list, boolean isRefresh) {
        sectionView.showData(list, isRefresh);
    }

    @Override
    public void onLoadDataFail(@NonNull String error, boolean isRefresh) {
        sectionView.showError(error, isRefresh);
    }
}
