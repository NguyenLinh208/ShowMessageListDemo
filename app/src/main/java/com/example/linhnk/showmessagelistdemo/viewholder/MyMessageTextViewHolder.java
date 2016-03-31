package com.example.linhnk.showmessagelistdemo.viewholder;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;


import com.example.linhnk.showmessagelistdemo.R;
import com.example.linhnk.showmessagelistdemo.api.Message;

import butterknife.Bind;

/**
 * Created by usr0200475 on 16/01/29.
 */
public class MyMessageTextViewHolder extends  MessageViewHolder  {

    public static final int LAYOUT_ID = R.layout.item_my_message_text;

    @Bind(R.id.tv_content)
    TextView content;

    @Bind(R.id.content_layout)
    CardView contentLayout;

    @Bind(R.id.read_flg)
    TextView readFlag;

    public MyMessageTextViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(Message message, String avatarUrl, String date) {
        super.bindData(message, avatarUrl, date);
        contentLayout.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        contentLayout.setRadius(10);
        content.setText(message.message);
        readFlag.setVisibility(message.readFlg == 2 ? View.VISIBLE : View.GONE);
    }

}
