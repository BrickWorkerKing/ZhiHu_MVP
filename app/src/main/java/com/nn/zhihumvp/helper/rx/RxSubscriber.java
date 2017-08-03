//package com.nn.zhihumvp.helper.rx;
//
//
//import com.nn.zhihumvp.app.ProjectApplication;
//import com.nn.zhihumvp.util.NetUtil;
//
//import org.reactivestreams.Subscriber;
//
//
///**
// * http请求回调观察者
// * Created by YoKeyword on 16/4/27.
// */
//public abstract class RxSubscriber<T> implements Subscriber<T> {
//
//    @Override
//    public void onComplete() {
//
//    }
//
//    @Override
//    public void onError(Throwable e) {
//        e.printStackTrace();
//        if (!NetUtil.checkNet(ProjectApplication.getInstance())) {
//            _onError("网络不可用!");
//        } else {
//            _onError("请求失败，请稍后重试...");
//        }
//    }
//
//    @Override
//    public void onNext(T t) {
//        if (t != null) {
//            _onNext(t);
//        } else {
//            _onError("请求失败，请稍后重试...");
//        }
//    }
//
//    public abstract void _onNext(T t);
//
//    public abstract void _onError(String msg);
//}
//
