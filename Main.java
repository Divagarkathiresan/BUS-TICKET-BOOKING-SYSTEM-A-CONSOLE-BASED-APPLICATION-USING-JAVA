import src.Interfaces.UserOperations;
import src.Model.*;
import src.Service.*;
import java.util.*;

public class Main{
    public static void main(String[] args){
        
        Scanner sc=new Scanner(System.in);
        UserOperations userService=new UserService();
        AdminService adminService=new AdminService(userService);
        BusService busService=new BusService();
        PaymentService paymentService=new PaymentService();
        BookingService bookingService=new BookingService(paymentService);
        AccountService accountService=new AccountService();
        User CurrentUser=null;
        Account CurrentAccount=null;
        
        Admin admin = new Admin(1, "Divagar", "Divagar@gmail.com", "1234", "Admin");
        User adminUser=new User(admin.getUserId(),admin.getName(),admin.getEmail(),admin.getPassword());

        userService.addUser(adminUser);

        while(true){

            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            try{
                int choice=Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Enter Name:");
                        String name=sc.nextLine();
                        System.out.println("Enter Email:");
                        String email=sc.nextLine();
                        System.out.println("Enter Password:");
                        String password=sc.nextLine();
                        System.out.println("Enter Account number: ");
                        int account_number=Integer.parseInt(sc.nextLine());
                        System.out.println("Enter Bank name: ");
                        String bank_name=sc.nextLine();
                        System.out.println("Enter initial amount: ");
                        int initial_amount=Integer.parseInt(sc.nextLine());

                        User new_user = userService.register(name,email,password);
                        accountService.createAccount(account_number, bank_name, initial_amount, new_user);
                        break;
                    
                    case 2:
                        System.out.println("Enter Name:");
                        String Login_name=sc.nextLine();
                        System.out.println("Enter Password:");
                        String Login_password=sc.nextLine();

                        if(Login_name.equals(admin.getName()) && Login_password.equals(admin.getPassword())) {
                            CurrentUser=admin;
                            System.out.println("Admin login successfull");
                            adminFunctions(sc,CurrentUser,busService,adminService);
                        }else{

                            CurrentUser = userService.login(Login_name, Login_password);
                        
                            if(CurrentUser==null) System.out.println("Invalid Credentials");
                            else {
                                CurrentAccount = accountService.getAccount(CurrentUser);
                                System.out.println("Login Successfull");
                                userFunction(sc,CurrentUser,busService,bookingService,paymentService,accountService,CurrentAccount);
                            }
                        }

                        break;
                    
                    case 3:
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;
                    
                    default:
                        System.out.println("Invalid choice . Please select from the given options");
                }
            }catch(NumberFormatException e){
                System.out.println("Enter Numbers only");
            }
        }
    }

    public static void userFunction(Scanner sc,User user,BusService busService,BookingService bookingService,PaymentService paymentService,AccountService accountService,Account account){

        while(true){

            System.out.println("1. Display all buses");
            System.out.println("2. Display buses as per Source & Destination");
            System.out.println("3. Book ticket");
            System.out.println("4. Get Booked Ticket");
            System.out.println("5. Profile");
            System.out.println("6. Get all payments");
            System.out.println("7. Logout");
            try{
            int choice =Integer.parseInt(sc.nextLine());

            switch(choice){

            case 1:
                System.out.println("All Buses");
                busService.displayAllBuses();
                break;

            case 2:
                System.out.println("Enter pickup Location : ");
                String source=sc.nextLine();
                System.out.println("Enter Drop Location : ");
                String destination=sc.nextLine();
                busService.displayBusAsPerSourceAndDestination(source, destination);
                break;

            case 3:
                System.out.println("Enter bus name : ");
                String bus_name_toBook=sc.nextLine();
                System.out.println("Enter pickup Location : ");
                String source_toBook=sc.nextLine();
                System.out.println("Enter Drop Location : ");
                String destination_toBook=sc.nextLine();

                Bus bus_toBook = busService.getBusAsPerSourceAndDestinationtoBook(bus_name_toBook,source_toBook, destination_toBook);

                if(bus_toBook==null) System.out.println("No Buses Available");
                else{
                    System.out.println("Enter number of seats : ");
                    int seats_toBook=Integer.parseInt(sc.nextLine());
                    bookingOperations(sc,account,user,bus_toBook,seats_toBook,bookingService);
                }
                break;
            
            case 4:
                
                ArrayList<Booking> list = bookingService.getbookings(user);
                for(Booking b:list){
                    
                    System.out.println("Booking Id : "+b.getBooking_id());
                    System.out.println("Bus name : "+b.getBus().getName());
                    System.out.println("Source : "+b.getBus().getSource());
                    System.out.println("Destination : "+b.getBus().getDestination());
                    System.out.println("Seats Booked : "+b.getSeats());
                    System.out.println("Payment Amount : "+b.getPayment().getAmount());
                    System.out.println("-------------------------");
                    
                }
                break;
            
            case 5:

                System.out.println("Name : "+user.getName());
                System.out.println("Email : "+user.getEmail());
                System.out.println("Password : "+user.getPassword());

                Account accountForProfile = accountService.getAccount(user);

                System.out.println("Account Number : "+accountForProfile.getAccount_number());
                System.out.println("Bank name : "+accountForProfile.getBank_name());
                System.out.println("Available amount : "+accountForProfile.getAmount());
                
                break;
            
            case 6:
                ArrayList<Payment> list_of_payments_done_by_user=paymentService.getAllPayments(user);
                for(Payment payment:list_of_payments_done_by_user){
                    System.out.println("Payment Id : "+payment.getPaymentId());
                    System.out.println("Payment amount : "+payment.getAmount());
                }
                break;
                
            case 7:
                return;
                
            default:
                System.out.println("Invalid choice . Please select from the given options");
            }
        }catch(NumberFormatException e){
               System.out.println("Enter numbers only"); 
        }
        }
    }

    public static void bookingOperations(Scanner sc,Account account,User user,Bus bus,int seats,BookingService bookingService){

        while(true){
            System.out.println("1. Confirm booking");
            System.out.println("2. Cancel booking, go to home page");
            int choice = Integer.parseInt(sc.nextLine());
            switch(choice){
                case 1:
                    bookingService.bookTickets(account,user,bus,seats);
                    return;
                case 2:
                    return;

                default:
                    System.out.println("Give the correct choice");
            }
        }
    }

    public static void adminFunctions(Scanner sc,User admin,BusService busService,AdminService adminService){

        while(true){

            System.out.println("1. Add bus");
            System.out.println("2. Update bus");
            System.out.println("3. Get all users");
            System.out.println("4. Logout");
            try{
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {

                    case 1:

                        System.out.println("Enter the bus name : ");
                        String name = sc.nextLine();
                        System.out.println("Enter the source : ");
                        String source = sc.nextLine();
                        System.out.println("Enter the destination : ");
                        String destination = sc.nextLine();
                        System.out.println("Enter the number of seats : ");
                        int seats = Integer.parseInt(sc.nextLine());
                        System.out.println("Enter the bus fare : ");
                        Double fare = Double.parseDouble(sc.nextLine());

                        busService.createBus( name, source, destination, seats, fare);
                        break;
                    case 2:
                        
                        System.out.println("Enter the bus name to update : ");
                        String name_toUpdate=sc.nextLine();
                        System.out.println("Enter the source to update : ");
                        String source_toUpdate=sc.nextLine();
                        System.out.println("Enter the destination to update : ");
                        String dest_toUpdate=sc.nextLine();
                        System.out.println("Enter the bus seats to update : ");
                        int seats_toUpdate=Integer.parseInt(sc.nextLine());
                        System.out.println("Enter the bus fare to update : ");
                        Double fare_toUpdate=Double.parseDouble(sc.nextLine());

                        busService.updateBus(name_toUpdate,source_toUpdate,dest_toUpdate,seats_toUpdate,fare_toUpdate);
                        break;
                    
                    case 3:
                        ArrayList<User> allUsers=adminService.listOfAllUsers();
                        
                        for(User user:allUsers){
                            System.out.println("User Id : "+user.getUserId());
                            System.out.println("User Name : "+user.getName());
                        }
                        break;
                    case 4:
                        System.out.println("Logging out...");
                        return;
                
                    default:
                        System.out.println("Enter the number from 1 to 3");
                }

            }catch(NumberFormatException e){
                System.out.print("Enter a number");
            }
        }
    }
    
}