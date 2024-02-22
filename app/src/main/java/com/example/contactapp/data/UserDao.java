package com.example.contactapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Upsert;

import com.example.contactapp.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Upsert
    void addUser(User user);

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    LiveData<List<User>> readAllData();

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

   @Query("DELETE FROM user_table")
   void deleteAllUsers();



}
