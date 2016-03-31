package com.example.linhnk.showmessagelistdemo.viewholder;


import com.example.linhnk.showmessagelistdemo.api.Message;

/**
 * Created by usr0200475 on 16/02/05.
 */
public interface SetDataMessage {
   void bindData(Message message, String avatarUrl, String date);
}
