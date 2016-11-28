package com.nn.zhihumvp.util;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * @author LiuZongRui  16/11/14
 */

public class HttpUtil {

    public static void get(String url, final Callback callback) {

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                callback.onError(errorResponse);
            }
        });
    }

    public interface Callback {
        void onSuccess(JSONObject response);

        void onError(JSONObject errorResponse);
    }
}

