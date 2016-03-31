package com.example.linhnk.showmessagelistdemo.viewholder;

import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.linhnk.showmessagelistdemo.R;
import com.example.linhnk.showmessagelistdemo.api.Message;

import butterknife.Bind;

/**
 * Created by usr0200475 on 16/01/29.
 */
public class MyMessageImageViewHolder extends MessageViewHolder {

    public static final int LAYOUT_ID = R.layout.item_my_message_image;

    @Bind(R.id.image_view)
    ImageView imageView;

    @Bind(R.id.content_layout)
    CardView contentLayout;

    @Bind(R.id.read_flg)
    TextView readFlag;

    public MyMessageImageViewHolder(View itemView) {
        super(itemView);
    }

    public void bindData(Message message, String avatarUrl, String date) {
        super.bindData(message, avatarUrl, date);
        Animation anim = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        Glide.with(context).load(message.pic).animate(anim).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
        readFlag.setVisibility(message.readFlg == 1? View.VISIBLE : View.GONE);
    }

}
