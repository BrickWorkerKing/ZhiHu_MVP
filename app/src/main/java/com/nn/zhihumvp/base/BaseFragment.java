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
import com.nn.zhihumvp.helper.rx.RxDisposableManager;

import io.reactivex.disposables.Disposable;

/**
 * @author LiuZongRui  16/11/17
 */

public class BaseFragment extends Fragment implements IBasePage {

    private Activity mActivity;
    private RxDisposableManager mDisposableManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mActivity = getActivity();
        mDisposableManager = new RxDisposableManager();
    }

    protected Activity _getActivity() {
        return this.mActivity;
    }

    protected void _goToActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(mActivity, clazz);
        intent.putExtra(Config.BUNDLE, bundle);
        startActivity(intent);
    }

    protected void _rxAdd(Disposable disposable){
        if (mDisposableManager == null){
            mDisposableManager = new RxDisposableManager();
        }
        mDisposableManager.rxAdd(disposable);
    }

    @Override
    public void _initToolbar(Toolbar toolbar) {

    }

    @Override
    public void _initRecyclerView(RecyclerView recyclerView) {
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
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
        super.onDetach();
        if (mDisposableManager != null){
            mDisposableManager.disposableAll();
        }
    }
}
