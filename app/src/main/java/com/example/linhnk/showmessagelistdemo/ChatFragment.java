package com.example.linhnk.showmessagelistdemo;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;


import com.example.linhnk.showmessagelistdemo.api.ApiObjectCallback;
import com.example.linhnk.showmessagelistdemo.api.GetMessageListRequest;
import com.example.linhnk.showmessagelistdemo.api.GetMessageListResponse;
import com.example.linhnk.showmessagelistdemo.api.Message;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.gmomedia.commons.chat.BaseChatFragment;
import jp.gmomedia.commons.chat.RequestType;
import jp.gmomedia.commons.util.DebugLog;
import jp.gmomedia.commons.util.RecycleViewUtil;

/**
 * Created by usr0200475 on 16/03/31.
 */
public class ChatFragment extends BaseChatFragment<GetMessageListResponse> {

    public static final String TAG = "ChatFragment";

    @Bind(R.id.et_input)
    protected EditText etInputText;

    @Bind(R.id.recyclerView)
    protected RecyclerView recyclerView;

    private MessageListAdapter adapter;
    private int pager = 1;
    private int lastPageNo = 5;

    private List<Message> messageList;
    LinearLayoutManager linearLayoutManager;

    @Override
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

    @Override
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

    @Override
    public void loadMoreData() {
        if (!requesting) {
            requesting = true;
            pager++;
            getMessageList(RequestType.LoadMore);
        }
    }

}
