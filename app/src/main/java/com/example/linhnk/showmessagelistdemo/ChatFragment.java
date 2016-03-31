package com.example.linhnk.showmessagelistdemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import com.example.linhnk.showmessagelistdemo.api.ApiObjectCallback;
import com.example.linhnk.showmessagelistdemo.api.GetMessageListRequest;
import com.example.linhnk.showmessagelistdemo.api.GetMessageListResponse;
import com.example.linhnk.showmessagelistdemo.api.Message;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.gmomedia.commons.chat.RequestType;
import jp.gmomedia.commons.util.DebugLog;
import jp.gmomedia.commons.util.RecycleViewUtil;
import jp.gmomedia.commons.util.UpScrollListener;

/**
 * Created by usr0200475 on 16/03/31.
 */
public class ChatFragment extends android.support.v4.app.Fragment {

    public static final String TAG = "ChatFragment";
    protected boolean requesting;
    protected boolean needLoadMore;

    @Bind(R.id.et_input)
    protected EditText etInputText;

    @Bind(R.id.recyclerView)
    protected RecyclerView recyclerView;

    private MessageListAdapter adapter;
    private int pager = 1;
    private int lastPageNo = 5;

    private List<Message> messageList;
    LinearLayoutManager linearLayoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return initView(inflater, container);
    }

    public View initView(LayoutInflater inflater, ViewGroup container) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(getLayoutId(), container, false);
        initRecyclerView(rootView);
        return rootView;
    }

    public int getLayoutId() {
        return R.layout.fragment_chat_view;
    }


    public void initRecyclerView(View rootView) {
        ButterKnife.bind(this, rootView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        setOnScrollListener(recyclerView);
        getMessageList(RequestType.Initial);
    }

    public void setOnScrollListener(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new UpScrollListener((LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int page) {
                if (needLoadMore) {
                    loadMoreData();
                }
            }
        });
    }

    public void handlerResponse(GetMessageListResponse response, RequestType requestType) {
        switch (requestType) {
            case Initial:
                handlerInitResponse(response);
                break;
            default:
                handlerLoadMoreResponse(response);
        }
    }


    public void getMessageList(final RequestType requestType) {
        final GetMessageListRequest request = new GetMessageListRequest(requestType);
        request.setRequestListener(new ApiObjectCallback<GetMessageListResponse>() {
            @Override
            public void onSuccess(GetMessageListResponse response) {
                requesting = false;
                handlerResponse(response, requestType);
            }

            @Override
            public void onFail(int errorCode, String message) {
                DebugLog.e("FAILED");
            }
        });
        request.execute();
    }

    public void handlerInitResponse(GetMessageListResponse response) {
        messageList = response.messages;
        if (response != null) {
            adapter = new MessageListAdapter(getActivity(), getContext(), messageList, false, response.targetUserData.userPic, true);
            adapter.setHasMoreData(pager < lastPageNo);
            recyclerView.setAdapter(adapter);
            RecycleViewUtil.scrollToEndWithoutAnim(recyclerView, messageList.size());
        }

        needLoadMore = pager < lastPageNo;
    }

    public void handlerLoadMoreResponse(GetMessageListResponse response) {
        adapter.setHasMoreData(pager < lastPageNo);
        if (adapter.hasMoreData()) {
            adapter.loadMore(response.messages);
        } else {
            adapter.addMoreDataNoAnim(response.messages);
        }

        RecycleViewUtil.scrollToLastPosition(recyclerView, linearLayoutManager, response.messages.size());
        needLoadMore = pager < lastPageNo;
    }

    public void loadMoreData() {
        if (!requesting) {
            requesting = true;
            pager++;
            getMessageList(RequestType.LoadMore);
        }
    }

}
