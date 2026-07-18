package src.Service;

import java.util.*;

import src.Interfaces.UserOperations;
import src.Model.*;
import src.Utils.IdGenerator;

public class UserService implements UserOperations{

    private ArrayList<User> users=new ArrayList<>();

    
    public void addUser(User user){
        users.add(user);
    }
    
    public ArrayList<User> getAllUsers(){
        return users;
    }
    
    //register
    @Override
    public User register(String name,String email,String password){
        int userId=IdGenerator.generateUserId();
        User user=new User(userId, name, email, password);
        users.add(user);
        System.out.println("Registration successful");
        return user;
    }

    //login
    @Override
    public User login(String name,String password){

        for(User user:users){
            if((user.getName().equals(name)) && (user.getPassword().equals(password))){
                return user;
            }

        }
        return null;
    }
}