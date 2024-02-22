package com.example.contactapp.data;

import androidx.lifecycle.LiveData;

import com.example.contactapp.model.User;

import java.util.List;

public class UserRepository {
    private final UserDao userDao;
    LiveData<List<User>> readAllData;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
        this.readAllData = userDao.readAllData();
    }

    public LiveData<List<User>> getReadAllData() {
        return readAllData;
    }

    void addUser(User user){
        userDao.addUser(user);
    }

    void updateUser(User user) { userDao.updateUser(user);}


    void deleteUser(User user){
        userDao.deleteUser(user);
    }



    void deleteAllUsers(){
        userDao.deleteAllUsers();
    }
}

