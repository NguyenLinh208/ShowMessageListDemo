package com.example.linhnk.showmessagelistdemo.viewholder;

import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.linhnk.showmessagelistdemo.R;
import com.example.linhnk.showmessagelistdemo.api.Message;

import butterknife.Bind;
import jp.gmomedia.commons.util.DebugLog;

/**
 * Created by usr0200475 on 16/01/29.
 */
public class UserMessageTextViewHolder extends  MessageSetAvatarHolder  {

    public static final int LAYOUT_ID = R.layout.item_message_text_user_layout;

    @Bind(R.id.tv_content)
    TextView content;

    public UserMessageTextViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(Message message, String avatarUrl, String date) {
        super.bindData(message, avatarUrl, date);
        content.setText(message.message);
        DebugLog.i("avatar link " + avatarUrl);
        Glide.with(context).load(avatarUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(avatar);
    }
}
