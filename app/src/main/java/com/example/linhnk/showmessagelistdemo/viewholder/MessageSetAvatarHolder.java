package com.example.linhnk.showmessagelistdemo.viewholder;

import android.view.View;
import android.widget.ImageView;


import com.example.linhnk.showmessagelistdemo.R;

import butterknife.Bind;

/**
 * Created by usr0200475 on 16/02/05.
 */
public class MessageSetAvatarHolder extends MessageViewHolder {

    @Bind(R.id.img_avatar)
    ImageView avatar;

    public MessageSetAvatarHolder(View itemView) {
        super(itemView);
    }

}
