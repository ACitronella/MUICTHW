import java.util.ArrayList;
import java.util.List;

public class Bank {
    //1.variable
    private ArrayList<BankAccount> accounts;
    //2.constructor
    public Bank(){
        accounts = new ArrayList<BankAccount>();
    }
    //3.methods
    //add an account to this bank
    public void addAccount(BankAccount a){
        accounts.add(a);
    }
    //gets the sum of the balances of all accounts in this bank
    public double getTotalBalance(){
        
        //**************** YOUR CODE HERE****************
        double sum = 0.0;
        for(BankAccount e : this.accounts){
            sum += e.getBalance();
        }
        return sum;
        //*********************************************
    }
    //counts the number of bank account whose balance is at least given value.
    public int countBalanceAtLeast(double atLeast){
        
        //**************** YOUR CODE HERE****************
        int count = 0;
        for (BankAccount e : this.accounts) {
            if(e.getBalance() > atLeast){
                count++;
            }
        }
        
        return count;
        //*********************************************
    }
    
    //finds a bank account with a given number
    public BankAccount find(int accountNumber){
        
        //**************** YOUR CODE HERE****************
        for(BankAccount e: this.accounts){
            if(e.getAccountNumber() == accountNumber){
                return e;
            }
        }
        return null;
        //*********************************************
    }
    
    //gets the bank account with the largest balance.
    public BankAccount getMax(){
        
        //**************** YOUR CODE HERE****************
        if(this.accounts.size() == 0){
            return null;
        }
        int max = 0;
        for (int i = 0; i < this.accounts.size(); i++) {
            if(this.accounts.get(max).getBalance() < this.accounts.get(i).getBalance()){
                max = i;
            }
        }
        return this.accounts.get(max);
        //*********************************************
    }
    
    //gets the bank account with the minimum balance.
    public BankAccount getMin(){
        
        //**************** YOUR CODE HERE****************
        if(this.accounts.size() == 0){
            return null;
        }
        int min = 0;
        for (int i = 0; i < this.accounts.size(); i++) {
            if(this.accounts.get(min).getBalance() > this.accounts.get(i).getBalance()){
                min = i;
            }
        }
        return this.accounts.get(min);
        //*********************************************
    }
    
    
    //finds duplicate accounts by checking the account numbers in O(N) without using Set and Map
    //return the list of all the accounts that are later found to be duplicate, if there is no duplicate simply return an empty list
    public List<BankAccount> findDuplicate(){

        //**************** YOUR CODE HERE****************
        // the solution that you expected is a bad design of Map. 
        // Then, why dont you teach whole map or move this excerise to map/set lesson
        // Because most of the student dont even know what does big O notation do with the code


        List<BankAccount> l = new ArrayList<>(this.accounts.size());
        List<BankAccount> a = new ArrayList<>();
        boolean isFound;
        int i, j;
        for(i = 0; i < this.accounts.size(); i++){
            isFound = false;
            for(j = 0; j < l.size(); j++){
                if(this.accounts.get(i).getAccountNumber() == l.get(j).getAccountNumber()){
                    a.add(this.accounts.get(i));
                    isFound = true;
                    break;
                }
            }
            if(!isFound){
                l.add(this.accounts.get(i)); 
            }
        }
        return a;
        //*********************************************
    }

    
}
