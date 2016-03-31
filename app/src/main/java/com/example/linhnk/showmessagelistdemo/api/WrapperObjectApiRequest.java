package com.example.linhnk.showmessagelistdemo.api;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import java.util.HashMap;

import jp.gmomedia.commons.CommonApplication;
import jp.gmomedia.commons.api.NetworkUtils;
import jp.gmomedia.commons.api.volley.BaseApiRequest;
import jp.gmomedia.commons.api.volley.Response.Response;
import jp.gmomedia.commons.api.volley.callback.OnFailListener;
import jp.gmomedia.commons.api.volley.callback.OnSuccessListener;
import jp.gmomedia.commons.api.volley.request.BaseTypeRequest;
import jp.gmomedia.commons.util.DebugLog;

/**
 * Created by usr0200475 on 16/03/31.
 */
public abstract class WrapperObjectApiRequest<T> extends BaseApiRequest<T> implements OnSuccessListener<T>, OnFailListener {

    @Override
    protected BaseTypeRequest excecuteRequest() {
        if ((getMethod() == Request.Method.GET || getMethod() == Request.Method.DELETE) && getRequestParams() != null) {
            baseTypeRequest = new WrapperGsonRequest<>(getMethod(), createRequestUrl(), getResponseClass(), handleRequestHeaders(), new HashMap<String, String>(), getListener(), getErrorListener());
        } else {
            baseTypeRequest = new WrapperGsonRequest<>(getMethod(), createRequestUrl(), getResponseClass(), handleRequestHeaders(), handleRequestParams(), getListener(), getErrorListener());
        }
        baseTypeRequest.setRetryPolicy(getDefaultRetryPolicy());
        NetworkUtils.getInstance(CommonApplication.getInstance()).addToRequestQueue(baseTypeRequest);
        return baseTypeRequest;
    }

    abstract public void onRequestSuccess(T response);

    abstract public void onRequestError(VolleyError error);

    public WrapperObjectApiRequest() {
        setListener(this, this);
    }

    @Override
    public void onFail(BaseTypeRequest request, Response response) {
        DebugLog.e("onFail");

    }


    @Override
    public void onPrerequisiteFail(BaseTypeRequest request, VolleyError error) {
        DebugLog.e("onPrerequisiteFail");
    }

    @Override
    public void onSuccess(BaseTypeRequest request, Response<T> response) {
        onRequestSuccess(response.responseObject);
    }

}