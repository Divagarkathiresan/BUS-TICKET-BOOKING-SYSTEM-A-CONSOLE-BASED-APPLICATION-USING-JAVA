package src.Utils;

public class IdGenerator {
    
    private static int userId=2;
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
