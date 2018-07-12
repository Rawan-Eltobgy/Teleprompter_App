package com.example.eltobgy.teleprompterapp.models;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by Eltobgy on 10-Jul-18.
 */


public class TeleprompterFile implements Parcelable {
    private int id;
    private String title;
    private String content;
    public static final Creator<TeleprompterFile> CREATOR = new Creator<TeleprompterFile>() {
        @Override
        public TeleprompterFile createFromParcel(Parcel in) {
            return new TeleprompterFile(in);
        }

        @Override
        public TeleprompterFile[] newArray(int size) {
            return new TeleprompterFile[size];
        }
    };


    public TeleprompterFile() {
    }

    public TeleprompterFile(Parcel in) {
        id = in.readInt();
        title = in.readString();
        content = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return content;
    }

    public void setDescription(String content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "TeleprompterFile{" +
                "id='" + id + '\'' +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }}

