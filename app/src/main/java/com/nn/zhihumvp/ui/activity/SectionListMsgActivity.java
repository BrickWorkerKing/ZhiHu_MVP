package com.nn.zhihumvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.nn.zhihumvp.R;
import com.nn.zhihumvp.app.Config;
import com.nn.zhihumvp.base.BaseActivity;
import com.nn.zhihumvp.contract.SectionMsgListContract;
import com.nn.zhihumvp.model.vo.SectionMsgVO;
import com.nn.zhihumvp.presenter.SectionMsgListPresenter;
import com.nn.zhihumvp.ui.adapter.SectionMsgListAdapter;
import com.nn.zhihumvp.ui.adapter.diffcallback.SectionMsgDiffCallBack;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SectionListMsgActivity extends BaseActivity implements SectionMsgListContract.View {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.ry_list)
    RecyclerView ryList;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private SectionMsgListPresenter presenter;
    private String id = "";
    private String title = "";
    private SectionMsgListAdapter sectionMsgListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_list_msg);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra(Config.BUNDLE);
            id = bundle.getString("id");
            title = bundle.getString("title");
        }
        toolbar.setTitle(title);
        _initToolbar(toolbar);
        _initRecyclerView(ryList);
        _initRefreshLayout(refreshLayout);
        ryList.setAdapter(sectionMsgListAdapter = new SectionMsgListAdapter(this));
        presenter = new SectionMsgListPresenter(this);
        presenter.loadSectionMsgList(id, true);
    }

    @Override
    public void showRefreshProgress() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void hideRefreshProgress() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoadHint() {

    }

    @Override
    public void hideLoadHint() {

    }

    @Override
    public void showData(@NonNull List<SectionMsgVO> sectionMsgVOs, boolean isRefresh) {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new SectionMsgDiffCallBack(sectionMsgListAdapter._getItems(), sectionMsgVOs));
        result.dispatchUpdatesTo(sectionMsgListAdapter);
        sectionMsgListAdapter._setItems(sectionMsgVOs);
    }

    @Override
    public void showError(String error, boolean isRefresh) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        presenter.onDetachView();
        super.onDestroy();
    }
}
