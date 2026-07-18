package src.Service;
import java.util.*;
import src.Model.*;


public class AccountService{

    public HashMap<Integer,Account> accounts = new HashMap<>();
    
    public void createAccount(int account_number,String bank_name,int initial_amount,User user){
        Account account = new Account(account_number, bank_name, initial_amount, user);
        accounts.put(account_number,account);
    }

    public Account getAccount(User user){
        for(Account account : accounts.values()){
            if(account.getUser().getUserId() == user.getUserId()){
                return account;
            }
        }
        return null;
    }

    

}