package com.example.contactapp.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.contactapp.model.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    public final LiveData<List<User>> readAllData;
    private final UserRepository repository;


    public UserViewModel(@NonNull Application application) {
        super(application);
        UserDao userDao = UserDatabase.getDatabase(application).userDao();
        repository = new UserRepository(userDao);
        readAllData = repository.readAllData;
    }


    public void addUser(User user) {
        repository.addUser(user);
    }

    public void updateUser(User user) { repository.updateUser(user);}

    public void deleteUser(User user){
        repository.deleteUser(user);
    }
    public void deleteAllUsers(){
        repository.deleteAllUsers();
    }


}
