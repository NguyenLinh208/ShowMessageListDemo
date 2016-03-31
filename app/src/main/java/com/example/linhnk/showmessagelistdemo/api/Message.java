package com.example.linhnk.showmessagelistdemo.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by usr0200475 on 16/01/09.
 */
public class Message implements Parcelable {

    public int messageId;
    public int fromUserId;
    public int toUserId;
    public int status;
    public String message;
    public int picId;
    public String pic;
    public int readFlg;
    public String createdAt;
    public String updateAt;

    protected Message(Parcel in) {
        messageId = in.readInt();
        fromUserId = in.readInt();
        toUserId = in.readInt();
        status = in.readInt();
        message = in.readString();
        picId = in.readInt();
        pic = in.readString();
        readFlg = in.readInt();
        createdAt = in.readString();
        updateAt = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(messageId);
        dest.writeInt(fromUserId);
        dest.writeInt(toUserId);
        dest.writeInt(status);
        dest.writeString(message);
        dest.writeInt(picId);
        dest.writeString(pic);
        dest.writeInt(readFlg);
        dest.writeString(createdAt);
        dest.writeString(updateAt);
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        public Message createFromParcel(Parcel source) {
            return new Message(source);
        }

        public Message[] newArray(int size) {
            return new Message[size];
        }
    };


    @Override
    public String toString() {
        return "Message: {" +
                "messageId=" + messageId +
                ", fromUserId='" + fromUserId + '\'' +
                ", toUserId='" + toUserId + '\'' +
                ", status=" + status + '\'' +
                ", message=" + message + '\'' +
                ", picId=" + picId + '\'' +
                ", pic=" + pic + '\'' +
                ", readFlg=" + readFlg + '\'' +
                ", createdAt=" + createdAt + '\'' +
                ", updateAt=" + updateAt + '\'' +
                '}';
    }
}
