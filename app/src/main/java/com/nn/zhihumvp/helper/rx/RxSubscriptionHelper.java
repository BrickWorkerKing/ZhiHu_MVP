//package com.nn.zhihumvp.helper.rx;
//
//
//import org.reactivestreams.Subscription;
//
///**
// * Rx观察者管理类
// *
// * @author LiuZongRui  16/11/16
// */
//
//public class RxSubscriptionHelper {
//
//    private CompositeSubscription mSubscription;
//
//    protected void rxAdd(Subscription s) {
//        if (mSubscription == null) {
//            mSubscription = new CompositeSubscription();
//        }
//        mSubscription.add(s);
//    }
//
//    protected void unSubscribe() {
//        if (mSubscription != null && mSubscription.hasSubscriptions()) {
//            mSubscription.unsubscribe();
//            mSubscription = null;
//        }
//    }
//}
