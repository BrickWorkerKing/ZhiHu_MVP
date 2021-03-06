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
import com.nn.zhihumvp.helper.rx.RxUtils;
import com.nn.zhihumvp.model.vo.LatestNewsVO;
import com.nn.zhihumvp.presenter.LatestNewsPresenter;
import com.nn.zhihumvp.ui.activity.NewsContentActivity;
import com.nn.zhihumvp.ui.adapter.LatestNewsAdapter;
import com.nn.zhihumvp.ui.adapter.diffcallback.LatestDiffCallBack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

/**
 * 最新新闻
 *
 * @author LiuZongRui  16/11/17
 */

public class LatestNewsFragment extends BaseFragment implements LatestNewsContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ry_list)
    RecyclerView ryList;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    Unbinder unbinder;
    private LatestNewsPresenter presenter;
    private LatestNewsAdapter latestNewsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_latest_news, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    private void init() {
        toolbar.setTitle("最新消息");
        _initRecyclerView(ryList);
        _initRefreshLayout(refreshLayout);
        ryList.setAdapter(latestNewsAdapter = new LatestNewsAdapter(_getActivity()));
        presenter = new LatestNewsPresenter(this);
        presenter.loadNewsList(false);
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
        unbinder.unbind();
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
    public void showData(@NonNull Map<String, List<LatestNewsVO>> stringListMap, boolean isRefresh) {
        final List<LatestNewsVO> storyBeanList = stringListMap.get("newsList");
        final List<LatestNewsVO> topStoryBeanList = stringListMap.get("banner");
        final List<String> imageList = new ArrayList<>();
        for (LatestNewsVO bean : topStoryBeanList) {
            imageList.add(bean.getImage());
        }
        if (isRefresh) {
            _rxAdd(Observable
                    .create(new ObservableOnSubscribe<DiffUtil.DiffResult>() {
                        @Override
                        public void subscribe(@io.reactivex.annotations.NonNull ObservableEmitter<DiffUtil.DiffResult> e) throws Exception {
                            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new LatestDiffCallBack(latestNewsAdapter._getItems(), storyBeanList, latestNewsAdapter.getImageList(), imageList), true);
                            e.onNext(diffResult);
                            e.onComplete();
                        }
                    })
                    .compose(RxUtils.<DiffUtil.DiffResult>io_main())
                    .subscribe(new Consumer<DiffUtil.DiffResult>() {
                        @Override
                        public void accept(@io.reactivex.annotations.NonNull DiffUtil.DiffResult diffResult) throws Exception {
                            diffResult.dispatchUpdatesTo(latestNewsAdapter);
                        }
                    }));
        }
        latestNewsAdapter.setData(storyBeanList, imageList, isRefresh);

    }

    @Override
    public void showError(@NonNull String error, boolean isRefresh) {
        Toast.makeText(_getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
