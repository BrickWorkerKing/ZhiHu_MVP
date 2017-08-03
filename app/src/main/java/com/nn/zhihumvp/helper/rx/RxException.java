package com.nn.zhihumvp.helper.rx;

import com.nn.zhihumvp.app.ProjectApplication;
import com.nn.zhihumvp.util.NetUtil;

import java.net.SocketException;
import java.net.UnknownHostException;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by nn on 17-8-3.
 */
public class RxException<T extends Throwable> implements Consumer<T> {


    private static final String SOCKET_TIMEOUT_EXCEPTION = "网络连接超时，请检查您的网络状态，稍后重试";
    private static final String CONNECT_EXCEPTION = "网络连接异常，请检查您的网络状态";
    private static final String UNKNOWN_HOST_EXCEPTION = "网络异常，请检查您的网络状态";

    private Consumer<? super Throwable> mError;

    public RxException(Consumer<? super Throwable> error) {
        this.mError = error;
    }

    @Override
    public void accept(@NonNull T t) throws Exception {
        if (!NetUtil.checkNet(ProjectApplication.getInstance())) {
            mError.accept(new Throwable(CONNECT_EXCEPTION));
        } else if (t instanceof SocketException) {
            mError.accept(new Throwable(SOCKET_TIMEOUT_EXCEPTION));
        } else if (t instanceof UnknownHostException) {
            mError.accept(new Throwable(UNKNOWN_HOST_EXCEPTION));
        } else {
            mError.accept(t);
        }
    }


}
