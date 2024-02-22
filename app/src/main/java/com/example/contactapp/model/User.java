package com.example.contactapp.model;


import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;

import androidx.room.Entity;
import androidx.room.PrimaryKey;






@Entity(tableName = "user_table")
public class User implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String firstName;
    public String lastName;
    public int age;


    // Default constructor
    public User() {
    }

    public User(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    protected User(Parcel in) {
        id = in.readInt();
        firstName = in.readString();
        lastName = in.readString();
        age = in.readInt();
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

    public User(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeInt(age);
    }

    @Override
    public int describeContents() {
        return 0;
    }




}
