package com.kobashin.example.practice1.app;

import java.io.Serializable;

/**
 * Created by skobayashi1 on 15/04/01.
 */
public class User implements Serializable {

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    private int mId;

    private String mName;

    private String mPhone;

    private String mMailAddr;

    private String mAddress;

    private String memo;

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    private boolean isChecked = false;
    // Drawable isn't serializable object. so, we can't passed in directly in Intent extras.
//    private Drawable mIcon;
    private int mIconId;

    public int getIconId() {
        return mIconId;
    }

    public void setIconId(int iconId) {
        mIconId = iconId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getMailAddr() {
        return mMailAddr;
    }

    public void setMailAddr(String mailAddr) {
        mMailAddr = mailAddr;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "User{" +
                "mName='" + mName + '\'' +
                ", mPhone='" + mPhone + '\'' +
                ", mMailAddr='" + mMailAddr + '\'' +
                ", mAddress='" + mAddress + '\'' +
                ", memo='" + memo + '\'' +
                ", mIcon=" + mIconId +
                '}';
    }
}
