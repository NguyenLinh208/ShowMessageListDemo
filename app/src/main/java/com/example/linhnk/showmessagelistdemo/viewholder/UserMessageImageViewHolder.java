package com.example.linhnk.showmessagelistdemo.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.linhnk.showmessagelistdemo.R;
import com.example.linhnk.showmessagelistdemo.api.Message;

import butterknife.Bind;

/**
 * Created by usr0200475 on 16/01/29.
 */
public class UserMessageImageViewHolder extends MessageSetAvatarHolder {

    public static final int LAYOUT_ID = R.layout.item_message_image_user_layout;

    @Bind(R.id.image_view)
    ImageView imageView;

    public UserMessageImageViewHolder(View itemView) {
        super(itemView);
    }

    public void bindData(Message message, String avatarUrl, String date) {
        super.bindData(message, avatarUrl, date);
        Glide.with(context).load(message.pic).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
        Glide.with(context).load(avatarUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(avatar);
    }

}
