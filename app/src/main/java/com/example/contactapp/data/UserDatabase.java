package com.example.contactapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.contactapp.model.User;


@Database(entities = {User.class}, version = 1, exportSchema = false)
abstract public class UserDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    private static volatile UserDatabase INSTANCE;

    public static synchronized UserDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserDatabase.class, "user_database").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }


















}
