package src.Service;
import java.util.*;

import src.Interfaces.UserOperations;
import src.Model.User;


public class AdminService {
    private UserOperations userService;

    public AdminService(UserOperations userService2){
        userService=userService2;
    }

    public ArrayList<User> listOfAllUsers(){
        return userService.getAllUsers();
    }
}
