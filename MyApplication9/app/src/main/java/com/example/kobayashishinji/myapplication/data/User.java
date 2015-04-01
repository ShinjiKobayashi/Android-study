package com.example.kobayashishinji.myapplication.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kobayashi.shinji on 2015/03/10.
 */
public class User implements Parcelable {
    private int userId;
    private String userName;
    private String mail;

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };


    public User() {
    }

    public User(int id, String name, String addr) {
        this.userId = id;
        this.userName = name;
        this.mail = addr;
    }

    private User(Parcel in) {
        this.userId = in.readInt();
        this.userName = in.readString();
        this.mail = in.readString();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(userName);
        dest.writeString(mail);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
