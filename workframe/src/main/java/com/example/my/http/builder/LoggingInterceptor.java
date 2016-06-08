package com.example.my.http.builder;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/4/27.
 */
public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request=chain.request();
        Log.i("url",request.url()+"");
        Response response=chain.proceed(request);
        return response;
    }
}
