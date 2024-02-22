package com.example.contactapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Contact {

    public String firstName;
    public String lastName;
    public String phoneNumber;

    @PrimaryKey(autoGenerate = true)
    public int Id = 0;


}
