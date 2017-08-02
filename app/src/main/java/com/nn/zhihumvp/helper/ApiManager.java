package com.nn.zhihumvp.helper;

import com.nn.zhihumvp.BuildConfig;
import com.nn.zhihumvp.model.dto.LatestNewsDTO;
import com.nn.zhihumvp.model.dto.NewsContentDTO;
import com.nn.zhihumvp.model.dto.SectionListDTO;
import com.nn.zhihumvp.model.dto.SectionMsgListDTO;
import com.nn.zhihumvp.model.dto.StartImageDTO;
import com.nn.zhihumvp.util.LogUtil;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Api管理
 *
 * @author LiuZongRui  16/11/15
 */

public class ApiManager {

    private static final String HOST_HOME = "http://news-at.zhihu.com/"; // 服务器ip
    private static final String API = "api/4/";
    private static volatile ApiManager apiManager;
    private ApiService apiService;

    public static ApiManager getInstance() {
        if (apiManager == null) {
            synchronized (ApiManager.class) {
                if (apiManager == null) {
                    apiManager = new ApiManager();
                }
            }
        }
        return apiManager;
    }

    public ApiService getApiService() {
        if (apiService == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            if (BuildConfig.DEBUG) {
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            } else {
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            }

            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(15000, TimeUnit.MILLISECONDS)
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            LogUtil.i("Retrofit HostName -> " + hostname);
                            return HOST_HOME.equals(hostname);
                        }
                    })
                    .addInterceptor(loggingInterceptor)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(HOST_HOME + API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                    .client(client)
                    .build();
            apiService = retrofit.create(ApiService.class);
        }

        return apiService;
    }

    public interface ApiService {

        /**
         * 获取欢迎页的图片
         *
         * @return
         */
        @GET("start-image/1080*1920")
        Observable<StartImageDTO> getWelComePic();

        /**
         * 获取最新新闻信息
         *
         * @return
         */
        @GET("news/latest")
        Observable<LatestNewsDTO> getLatestNews();

        /**
         * 获取新闻内容
         *
         * @return
         */
        @GET("news/{id}")
        Observable<NewsContentDTO> getNewsContent(@Path("id") String id);

        /**
         * 获取栏目列表
         *
         * @return
         */
        @GET("sections")
        Observable<SectionListDTO> getSectionList();

        /**
         * 通过栏目id获取栏目消息
         *
         * @param id
         * @return
         */
        @GET("section/{id}")
        Observable<SectionMsgListDTO> getSectionMsgList(@Path("id") String id);
    }

}
