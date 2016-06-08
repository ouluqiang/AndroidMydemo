package com.example.my.http.backcall;

/**
 * Created by Administrator on 2016/4/27.
 */
public abstract class BaseBackCall<T> {

    public abstract void onFailure(Throwable t);

    public abstract void onSucced(T t);

    public abstract void onLoading();


}
