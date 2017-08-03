package com.nn.zhihumvp.helper.rx;

import com.nn.zhihumvp.base.IBaseListViewPresenter;
import com.nn.zhihumvp.base.IBaseMapper;
import com.nn.zhihumvp.base.IBaseViewPresenter;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava工具类
 */
public class RxUtils {

    /**
     * 线程管理订阅
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> io_main() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }

        };
    }

    /**
     * 数据装换
     *
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T extends IBaseMapper, R> ObservableTransformer<T, R> transform_data() {
        return new ObservableTransformer<T, R>() {
            @Override
            public ObservableSource<R> apply(@NonNull Observable<T> upstream) {
                return upstream.map(new Function<T, R>() {
                    @Override
                    public R apply(@NonNull T t) throws Exception {
                        return (R) t.transform();
                    }
                });
            }
        };
    }

    /**
     * 非列表界面加载数据前后提示
     * @param presenter
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> load_start_end(@NonNull final IBaseViewPresenter presenter) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        presenter.onLoadStart();

                    }
                }).doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        presenter.onLoadEnd();
                    }
                });
            }
        };
    }

    /**
     * 列表界面加载数据前后其实
     * @param presenter
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> load_list_start_end(@NonNull final IBaseListViewPresenter presenter) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        presenter.onRefreshStart();

                    }
                }).doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        presenter.onRefreshEnd();
                    }
                });
            }
        };
    }

    /**
     * 处理服务器返回的数据
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> handle_result(){
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.flatMap(new Function<T, ObservableSource<? extends T>>() {
                    @Override
                    public ObservableSource<? extends T> apply(@NonNull T t) throws Exception {

                        // 对于会返回状态信息的接口，就可以用到此方法

                        return Observable.empty();
                    }
                });
            }
        };
    }
}
