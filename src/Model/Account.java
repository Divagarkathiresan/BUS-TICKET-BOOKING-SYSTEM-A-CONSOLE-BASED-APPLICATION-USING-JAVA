package src.Model;

public class Account{

    private int Account_number;
    private String bank_name;
    private int amount;
    private User user;

    
    public Account(int account_number, String bank_name, int amount,User user) {
        Account_number = account_number;
        this.bank_name = bank_name;
        this.amount = amount;
        this.user=user;
    }
    
    public int getAccount_number() {
        return Account_number;
    }
    public void setAccount_number(int account_number) {
        Account_number = account_number;
    }
    public String getBank_name() {
        return bank_name;
    }
    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
}