package com.nn.zhihumvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nn.zhihumvp.R;
import com.nn.zhihumvp.base.BaseAdapter;
import com.nn.zhihumvp.base.BaseFragment;
import com.nn.zhihumvp.contract.SectionListContract;
import com.nn.zhihumvp.model.vo.SectionVO;
import com.nn.zhihumvp.presenter.SectionListPresenter;
import com.nn.zhihumvp.ui.activity.SectionListMsgActivity;
import com.nn.zhihumvp.ui.adapter.SectionListAdapter;
import com.nn.zhihumvp.ui.adapter.diffcallback.SectionDiffCallBack;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 栏目新闻
 *
 * @author LiuZongRui  16/11/17
 */

public class SectionListFragment extends BaseFragment implements SectionListContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ry_list)
    RecyclerView ryList;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    Unbinder unbinder;
    private SectionListPresenter presenter;
    private SectionListAdapter listAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_section_list, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    private void init() {
        toolbar.setTitle("栏目");
        _initRecyclerView(ryList);
        _initRefreshLayout(refreshLayout);
        ryList.setAdapter(listAdapter = new SectionListAdapter(_getActivity()));
        presenter = new SectionListPresenter(this);
        presenter.loadSectionList(true);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadSectionList(true);
            }
        });
        listAdapter._setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View itemView) {
                String id = "";
                String title = "";
                SectionVO bean = listAdapter._getItems().get(position);
                if (bean != null) {
                    id = bean.getId();
                    title = bean.getName();
                }
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("title", title);
                _goToActivity(SectionListMsgActivity.class, bundle);
            }
        });
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
    public void showData(@NonNull List<SectionVO> list, boolean isRefresh) {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new SectionDiffCallBack(listAdapter._getItems(), list));
        result.dispatchUpdatesTo(listAdapter);
        listAdapter._setItems(list);
    }

    @Override
    public void showError(String error, boolean isRefresh) {
        Toast.makeText(_getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetach() {
        presenter.onDetachView();
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
