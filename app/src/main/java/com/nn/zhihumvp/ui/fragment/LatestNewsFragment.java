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
import com.nn.zhihumvp.contract.LatestNewsContract;
import com.nn.zhihumvp.helper.rx.RxSchedulersHelper;
import com.nn.zhihumvp.model.vo.LatestNewsVO;
import com.nn.zhihumvp.presenter.LatestNewsPresenter;
import com.nn.zhihumvp.ui.activity.NewsContentActivity;
import com.nn.zhihumvp.ui.adapter.LatestNewsAdapter;
import com.nn.zhihumvp.ui.adapter.diffcallback.LatestDiffCallBack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * 最新新闻
 *
 * @author LiuZongRui  16/11/17
 */

public class LatestNewsFragment extends BaseFragment implements LatestNewsContract.View {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.ry_list)
    RecyclerView ryList;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    private LatestNewsPresenter presenter;
    private LatestNewsAdapter latestNewsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_latest_news, container, false);
        ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    private void init() {
        toolbar.setTitle("最新消息");
        _initRecyclerView(ryList);
        _initRefreshLayout(refreshLayout);
        ryList.setAdapter(latestNewsAdapter = new LatestNewsAdapter(_getActivity()));
        presenter = new LatestNewsPresenter(this);
        presenter.loadNewsList(true);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadNewsList(true);
            }
        });
        latestNewsAdapter._setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View itemView) {
                if (position > 0) {
                    Bundle bundle = new Bundle();
                    String id = "";
                    LatestNewsVO newsBean = latestNewsAdapter._getItems().get(position - 1);
                    if (newsBean != null) {
                        id = newsBean.getId();
                    }
                    bundle.putString("id", id);
                    _goToActivity(NewsContentActivity.class, bundle);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        presenter.onDetachView();
        super.onDestroyView();
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
    public void showData(@NonNull Map<String, List<LatestNewsVO>> stringListMap, boolean isRefresh) {
        final List<LatestNewsVO> storyBeanList = stringListMap.get("newsList");
        final List<LatestNewsVO> topStoryBeanList = stringListMap.get("banner");
        final List<String> imageList = new ArrayList<>();
        for (LatestNewsVO bean : topStoryBeanList) {
            imageList.add(bean.getImage());
        }
        _rxAdd(Observable
                .create(new Observable.OnSubscribe<DiffUtil.DiffResult>() {
                    @Override
                    public void call(Subscriber<? super DiffUtil.DiffResult> subscriber) {
                        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new LatestDiffCallBack(latestNewsAdapter._getItems(), storyBeanList, latestNewsAdapter.getImageList(), imageList), true);
                        subscriber.onNext(diffResult);
                        subscriber.onCompleted();
                    }
                })
                .compose(RxSchedulersHelper.<DiffUtil.DiffResult>io_main())
                .subscribe(new Action1<DiffUtil.DiffResult>() {
                    @Override
                    public void call(DiffUtil.DiffResult diffResult) {
                        diffResult.dispatchUpdatesTo(latestNewsAdapter);
                        latestNewsAdapter.setData(storyBeanList, imageList);
                    }
                }));
    }

    @Override
    public void showError(String error, boolean isRefresh) {
        Toast.makeText(_getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
