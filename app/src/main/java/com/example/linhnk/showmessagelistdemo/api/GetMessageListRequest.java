package com.example.linhnk.showmessagelistdemo.api;

/**
 * Created by usr0200475 on 16/03/31.
 */

import com.android.volley.Request;
import com.android.volley.VolleyError;

import java.util.Map;

import jp.gmomedia.commons.chat.RequestType;

public class GetMessageListRequest extends WrapperObjectApiRequest<GetMessageListResponse> {

    private RequestType requestType;
    private ApiObjectCallback<GetMessageListResponse> requestListener;

    public GetMessageListRequest(RequestType requestType) {
        this.requestType = requestType;
    }

    @Override
    public int getMethod() {
        return Request.Method.GET;
    }

    @Override
    public String getRequestURL() {
        if (requestType == RequestType.Initial) {
            return "http://www.json-generator.com/api/json/get/bXXSfOuedK?indent=2";
        } else {
            return "http://www.json-generator.com/api/json/get/bNGmVckHeG?indent=2";
        }
    }

    @Override
    public Map<String, String> getRequestParams() {
        return null;
    }

    @Override
    public Class<GetMessageListResponse> getResponseClass() {
        return GetMessageListResponse.class;
    }

    @Override
    public void onRequestSuccess(GetMessageListResponse response) {
        if (requestListener != null) {
            requestListener.onSuccess(response);
        }
    }

    @Override
    public void onRequestError(VolleyError error) {
        if (requestListener != null) {
            requestListener.onFail(0 , error.getMessage());
        }
    }

    public void setRequestListener(ApiObjectCallback<GetMessageListResponse> requestListener) {
        this.requestListener = requestListener;
    }
}
