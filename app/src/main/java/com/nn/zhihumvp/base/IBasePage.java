package com.nn.zhihumvp.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

/**
 * @author LiuZongRui  16/11/23
 */

public interface IBasePage {

    void _initToolbar(Toolbar toolbar);

    void _initRecyclerView(RecyclerView recyclerView);

    void _initRefreshLayout(SwipeRefreshLayout swipeRefreshLayout);

    void _gotoActivity(Class clazz, Bundle bundle);
}
