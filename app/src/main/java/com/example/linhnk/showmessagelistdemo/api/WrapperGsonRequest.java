package com.example.linhnk.showmessagelistdemo.api;

/**
 * Created by usr0200475 on 16/03/31.
 */

import com.android.volley.Response;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import java.util.Map;

import jp.gmomedia.commons.api.volley.request.GsonRequest;

/**
 * Created by HungCV on 3/14/2016.
 */
public class WrapperGsonRequest<T> extends GsonRequest<T> {

    static {
        gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
    }

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param method
     * @param url           URL of the request to make
     * @param clazz         Relevant class object, for Gson's reflection
     * @param headers       Map of request headers
     * @param params
     * @param listener
     * @param errorListener
     */
    public WrapperGsonRequest(int method, String url, Class<T> clazz, Map<String, String> headers, Map<String, String> params, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, clazz, headers, params, listener, errorListener);
    }
}
