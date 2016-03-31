package com.example.linhnk.showmessagelistdemo.api;

/**
 * Created by HungCV on 1/18/2016.
 */
public interface ApiObjectCallback<T> {

    void onSuccess(T response);

    void onFail(int errorCode, String message);

}
