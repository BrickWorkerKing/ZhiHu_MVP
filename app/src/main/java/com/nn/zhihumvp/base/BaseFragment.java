package com.nn.zhihumvp.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.nn.zhihumvp.R;
import com.nn.zhihumvp.app.Config;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author LiuZongRui  16/11/17
 */

public class BaseFragment extends Fragment implements IBasePage {

    private Activity activity;
    private CompositeSubscription compositeSubscription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.activity = getActivity();
    }

    protected Activity _getActivity() {
        return this.activity;
    }

    @Override
    public void _rxAdd(Subscription subscription) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(subscription);
    }

    protected void _goToActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(activity, clazz);
        intent.putExtra(Config.BUNDLE, bundle);
        startActivity(intent);
    }

    @Override
    public void _initToolbar(Toolbar toolbar) {

    }

    @Override
    public void _initRecyclerView(RecyclerView recyclerView) {
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        }
    }

    @Override
    public void _initRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        }
    }

    @Override
    public void _gotoActivity(Class clazz, Bundle bundle) {

    }

    @Override
    public void onDetach() {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
        }
        super.onDetach();
    }
}
