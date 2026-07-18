package src.Model;

public class Payment{

    private int PaymentId;
    private Account account;
    private int amount;

    
    public Payment(int paymentId, Account account, int amount) {
        PaymentId = paymentId;
        this.account = account;
        this.amount = amount;
    }

    public int getPaymentId() {
        return PaymentId;
    }
    public void setPaymentId(int paymentId) {
        PaymentId = paymentId;
    }
    
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    
}