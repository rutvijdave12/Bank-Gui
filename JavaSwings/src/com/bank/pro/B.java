
package com.bank.pro;

import com.bank.exceptions.InvalidAccountNumberException;
import com.bank.main.Customer;
import com.bank.model.DBConfig;
import java.sql.SQLException;

public class B {
    
    private DBConfig db;
    
    public B(){
        this.db = new DBConfig();
    }
    

    
    public boolean checkAccountNumber(String accountNumber) throws ClassNotFoundException, SQLException, InvalidAccountNumberException{
        boolean status = db.checkAccountNumber(accountNumber);
        if(status){
            return true;
        }
        throw new InvalidAccountNumberException();
    }

    public Customer fetchCustomerDetails(String accountNumber) throws ClassNotFoundException, SQLException {
        Customer c = db.fetchCustomerDetails(accountNumber);
        return c;
        
    }

    public void saveTransaction(Customer c, String amount) throws ClassNotFoundException, SQLException {
        db.saveTransaction(c,amount);
    }

    public void updateBalance(double updatedBalance, Customer c) throws ClassNotFoundException, SQLException {
        db.updateBalance(updatedBalance,c);
    }
    
}
