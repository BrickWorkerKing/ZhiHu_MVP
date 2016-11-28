//package com.nn.zhihumvp.helper.rx;
//
//import android.content.Intent;
//
//import com.nn.zhihumvp.R;
//import com.nn.zhihumvp.exception.ServerFailueException;
//import com.nn.zhihumvp.model.bean.RESTResult;
//
//import rx.Observable;
//import rx.Subscriber;
//import rx.functions.Func1;
//
///**
// * Rx处理服务器返回
// * Created by YoKeyword on 16/4/27.
// */
//public class RxResultHelper {
//
//    public static <T> Observable.Transformer<RESTResult, T> handleResult() {
//        return new Observable.Transformer<RESTResult, T>() {
//            @Override
//            public Observable<T> call(Observable<RESTResult> tObservable) {
//                return tObservable.flatMap(
//                        new Func1<RESTResult, Observable<T>>() {
//                            @Override
//                            public Observable<T> call(RESTResult result) {
//                                if (result != null) {
//                                    return createData(result);
//                                }else{
//                                    return Observable.error(new ServerFailueException());
//                                }
//                                return Observable.empty();
//                            }
//                        }
//
//                );
//            }
//        };
//    }
//
//    private static <T> Observable<T> createData(T t) {
//        return Observable.create(new Observable.OnSubscribe<T>() {
//            @Override
//            public void call(Subscriber<? super T> subscriber) {
//                try {
//                    subscriber.onNext(t);
//                    subscriber.onCompleted();
//                } catch (Exception e) {
//                    subscriber.onError(e);
//                }
//            }
//        });
//    }
//}
