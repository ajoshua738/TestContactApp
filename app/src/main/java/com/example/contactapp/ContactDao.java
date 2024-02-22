package com.example.contactapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Upsert;

@Dao
public interface ContactDao {

    @Upsert
    void insertContact(Contact contact);

    @Delete
    void deleteContact(Contact contact);


}
