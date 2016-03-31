package com.example.linhnk.showmessagelistdemo;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ViewGroup;


import com.example.linhnk.showmessagelistdemo.api.Message;
import com.example.linhnk.showmessagelistdemo.viewholder.MessageViewHolder;
import com.example.linhnk.showmessagelistdemo.viewholder.MyMessageImageViewHolder;
import com.example.linhnk.showmessagelistdemo.viewholder.MyMessageTextViewHolder;
import com.example.linhnk.showmessagelistdemo.viewholder.UserMessageImageViewHolder;
import com.example.linhnk.showmessagelistdemo.viewholder.UserMessageTextViewHolder;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import jp.gmomedia.commons.chat.BaseMessageListAdapter;
import jp.gmomedia.commons.util.LayoutUtils;


/**
 * Created by usr0200475 on 16/03/14.
 */
public class MessageListAdapter extends BaseMessageListAdapter<Message> {
    private FragmentActivity activity;

    public MessageListAdapter(FragmentActivity activity, Context context, List<Message> messageList, boolean hasCustomLoadMoreHolder, String avatarUrl, boolean isLoadMoreFromTop) {
        super(context, messageList, hasCustomLoadMoreHolder, avatarUrl, isLoadMoreFromTop);
        this.activity = activity;
    }

    @Override
    public boolean checkIsMyMessage(int position) {
        return getItem(position).status == 1;
    }

    @Override
    public String checkIsImageMessage(int position) {
        return getItem(position).pic;
    }

    /**
     * Check for show Date divide text
     * @param position
     * @return
     */
    private String needCreateDateViewHolder(int position) {
        String divideDate = null;
        if (position == 0 && !hasMoreData()) {
            divideDate = StringUtils.split(getItem(position).createdAt)[0];
        } else if ((position == 1 && !hasMoreData())|| (position > 1)) {
            String dateToday = StringUtils.split(getItem(position).createdAt)[0];
            String dateYesterday = StringUtils.split(getItem(position - 1).createdAt)[0];
            if (!TextUtils.equals(dateToday, dateYesterday)) {
                divideDate = dateToday;
            }
        }
        return divideDate;
    }

    /**
     * Creating View Holder for binding Message
     * @param parent
     * @param viewType
     * @return
     */

    @Override
    public RecyclerView.ViewHolder onActualCreateViewHolder(ViewGroup parent, int viewType) {
        switch (ViewType.values()[viewType]) {
            case MY_MESSAGE_IMAGE:
                return new MyMessageImageViewHolder(LayoutUtils.inflate(parent, MyMessageImageViewHolder.LAYOUT_ID, false));
            case MY_MESSAGE_TEXT:
                return new MyMessageTextViewHolder(LayoutUtils.inflate(parent, MyMessageTextViewHolder.LAYOUT_ID, false));
            case USER_MESSAGE_IMAGE:
                return new UserMessageImageViewHolder(LayoutUtils.inflate(parent, UserMessageImageViewHolder.LAYOUT_ID, false));
            case  USER_MESSAGE_TEXT:
                return new UserMessageTextViewHolder(LayoutUtils.inflate(parent, UserMessageTextViewHolder.LAYOUT_ID, false));
        }

        return null;
    }

    /**
     * For bind Message View Holder
     * @param holder
     * @param position
     */
    @Override
    public void onActualBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MessageViewHolder) holder).bindData(getItem(position), userAvatarUrl, needCreateDateViewHolder(position));
    }

}
