package com.example.linhnk.showmessagelistdemo.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.example.linhnk.showmessagelistdemo.R;
import com.example.linhnk.showmessagelistdemo.api.Message;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by usr0200475 on 16/01/10.
 */
public class MessageViewHolder extends RecyclerView.ViewHolder implements SetDataMessage {

    @Bind(R.id.time)
    TextView time;

    @Bind(R.id.tv_date)
    TextView date;

    public Context context;

    public MessageViewHolder(View itemView) {
        super(itemView);
        this.context = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindData(Message message, String avatarUrl, String date) {
        time.setText(message.createdAt);
        if (date != null) {
            this.date.setText(date);
            this.date.setVisibility(View.VISIBLE);
        } else {
            this.date.setVisibility(View.GONE);
        }
    }

}
