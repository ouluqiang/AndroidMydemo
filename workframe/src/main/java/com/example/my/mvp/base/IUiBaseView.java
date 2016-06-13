package com.example.my.mvp.base;

/**
 * Created by Administrator on 2016/6/12 0012.
 */
public interface IUiBaseView {

    public void onSucceed(Object object);

    public void onFailure(Throwable t);

    public void onLoading();

    public void onToast(String text);

    public Object getBean();

}