package com.example.linhnk.showmessagelistdemo.api;

import java.util.List;

/**
 * Created by usr0200475 on 16/01/25.
 */
public class GetMessageListResponse {
    public TargetUserData targetUserData;
    public int count;
    public List<Message> messages;
    public int pager;
    public int lastPageNo;
}
