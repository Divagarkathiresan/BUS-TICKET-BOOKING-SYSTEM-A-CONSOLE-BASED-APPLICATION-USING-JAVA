package src.Service;
import java.util.*;

import src.Exception.InsufficientBalanceException;
import src.Model.*;
import src.Utils.IdGenerator;

public class PaymentService{


    ArrayList<Payment> payments = new ArrayList<>();

    public Payment doPayment(int Booking_id,Account account,User user,Bus bus,int seat) throws InsufficientBalanceException{
        int payment_id = IdGenerator.generatePaymentId();
        
        int fare = (int) bus.getFare();
        int required_amount = fare * seat;
        
        // checking the amount whether the amount is available in the account or not
        int available_amount = account.getAmount();
        
        if(available_amount < required_amount){
            throw new InsufficientBalanceException("Insufficient bank balance");
        }else{
            
            //update seats
            bus.setAvailableSeats(bus.getAvailableSeats() - seat);

            //update the amount in the account
            account.setAmount(available_amount - required_amount);
            Payment payment = new Payment(payment_id, account, required_amount);
            payments.add(payment);
            return payment;
        }
        

    }

    public ArrayList<Payment> getAllPayments(User user){
        ArrayList<Payment> ans=new ArrayList<>();
        for(Payment payment:payments){
            if(payment.getAccount().getUser().getName()==user.getName()){
                ans.add(payment);
            }
        }
        return ans;
    }
}