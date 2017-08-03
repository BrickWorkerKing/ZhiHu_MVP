package com.nn.zhihumvp.helper.rx;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Rx观察者管理类
 * 用来管理所有的http请求，在退出页面的时候中断全部请求
 *
 * @author LiuZongRui  16/11/16
 */

public class RxDisposableManager {

    private CompositeDisposable mManager;

    public void rxAdd(Disposable disposable) {
        if (mManager == null) {
            mManager = new CompositeDisposable();
        }
        mManager.add(disposable);
    }

    public void disposableAll() {
        if (null != mManager && mManager.size() > 0) {
            mManager.clear();
            mManager = null;
        }
    }
}
