package src.Interfaces;

import java.util.ArrayList;

import src.Model.User;

public interface UserOperations{

    void addUser(User user);

    ArrayList<User> getAllUsers();
    
    //register
    User register(String name,String email,String password);

    //login
    User login(String name,String password);
    
}