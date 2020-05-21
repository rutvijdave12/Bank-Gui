package com.bank.pro;

import com.bank.exceptions.InsufficientFundsException;
import com.bank.main.Customer;
import com.bank.model.DBConfig;
import java.sql.ResultSet;
import java.sql.SQLException;


public class C {
    private DBConfig db;
    
    public C(){
        this.db = new DBConfig();
        
    }
    

    public void saveTransaction(Customer c, String amount) throws SQLException, ClassNotFoundException {
        db.saveWithdrawalTransaction(c,amount);
        
    }

    public void checkAmount(String amount, String balance) throws InsufficientFundsException {
            if(Double.parseDouble(amount)>Double.parseDouble(balance)){
                throw new InsufficientFundsException();
            }
    }

    public void updateBalance(double updatedBalance, Customer c) throws ClassNotFoundException, SQLException {
            db.updateBalance(updatedBalance,c);
    }

    public Customer fetchTransactions(String accountNumber) throws ClassNotFoundException, SQLException {
            return db.fetchTransactions(accountNumber);
    }
    
}
