package src.Utils;
import java.util.ArrayList;

import src.Model.User;
import src.Service.UserService;
public class IdGenerator extends UserService{
    
    private static UserService us=new UserService();
    private static ArrayList<User> users=us.getAllUsers();
    
    private static int userId=users.size()+1;
    private static int bookingId=1;
    private static int paymentId=1;
    private static int busId=1;
    

    public static int generateUserId() {
        return userId++;
    }

    public static int generateBookingId() {
        return bookingId++;
    }

    public static int generatePaymentId() {
        return paymentId++;
    }

    public static int generateBusId() {
        return busId++;
    }
}
