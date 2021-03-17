//ID: 6388003
//Name: Phuriwat Angkoondittaphong
//Section: 1

import java.util.ArrayList;


/*
 * IMPLEMENT & SUBMIT THIS FILE
 */

public class AccountManager {
	
    public ArrayList<Account> accounts;

    public AccountManager(int failLimit){
        this.accounts = new ArrayList<>();
        Account.FAIL_ATTEMPT_LIMIT = failLimit;
    }

    public boolean registerAccount(Account acc){
        if(acc == null){
            return false;
        }
        for(Account a: this.accounts){
            if(a.getUsername().equals(acc.getUsername())){
                return false;
            }
        }
        return this.accounts.add(acc);

    }

    public Account getLastAccount(){
        if(this.accounts.size() == 0){
            return null;
        }
        return this.accounts.get(this.accounts.size() - 1);
    }

    public boolean login(String username, String password){
        
        for(Account a : this.accounts){
            if(a.getUsername().equals(username)){
                if(a.authentication(password)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean resetPassword(Account acc, String newPassword){
        if(acc == null){
            return false;
        }
        for(Account a: this.accounts){
            if(a.isEquals(acc)){
                a.setPassword(newPassword);
                return true;
            }
        }
        return false;
    }
}

