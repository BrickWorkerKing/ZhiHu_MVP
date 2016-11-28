package com.nn.zhihumvp.helper.rx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 线程管理订阅
 * Created by YoKeyword on 16/4/27.
 */
public class RxSchedulersHelper {

    public static <T> Observable.Transformer<T, T> io_main() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
