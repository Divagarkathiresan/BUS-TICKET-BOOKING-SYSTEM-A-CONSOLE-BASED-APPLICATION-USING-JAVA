package src.Service;
import java.util.*;

import src.Exception.InsufficientBalanceException;
import src.Model.*;
import src.Utils.IdGenerator;

public class BookingService{
    
    private PaymentService paymentService;
    private ArrayList<Booking> bookings=new ArrayList<>();

    public BookingService(PaymentService paymentService){
        this.paymentService=paymentService;
    }

    public void bookTickets(Account account,User user,Bus bus,int seats){
        int AvailableSeats = bus.getAvailableSeats();
        if(AvailableSeats < seats || seats <= 0){
            System.out.println("No available seats for booking");
            return;
        }else{
            int Booking_id = IdGenerator.generateBookingId();
            try{
                Payment payment = paymentService.doPayment(Booking_id, account, user, bus, seats);
                Booking booking = new Booking(Booking_id, user, bus, seats,payment);
                bookings.add(booking);
                System.out.println("Bus Booked Successfully");
            }catch(InsufficientBalanceException e){
                System.out.println(e.getMessage());
            }

        }
    }

    public ArrayList<Booking> getbookings(User user){
        ArrayList<Booking> list=new ArrayList<>();
        for(Booking booking : bookings){
            if(booking.getUser().getUserId()==user.getUserId()){
                list.add(booking);
            }
        }
        return list;
    }

}