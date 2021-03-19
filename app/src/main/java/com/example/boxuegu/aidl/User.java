package com.example.boxuegu.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    public int age;

    public String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    protected User(Parcel in) {
        this.age = in.readInt();
        this.name = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(age);
        out.writeString(name);
    }
}
