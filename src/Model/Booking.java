package src.Model;

public class Booking {
    
    private int booking_id;
    private User user;
    private Bus bus;
    private int seats;
    private Payment payment;

    public Booking(int booking_id, User user, Bus bus,int seats,Payment payment) {
        this.booking_id = booking_id;
        this.user = user;
        this.bus = bus;
        this.seats=seats;
        this.payment=payment;
    }

    public int getBooking_id() {
        return booking_id;
    }
    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Bus getBus() {
        return bus;
    }
    public void setBus(Bus bus) {
        this.bus = bus;
    }
    public Payment getPayment() {
        return payment;
    }
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    
    
}
