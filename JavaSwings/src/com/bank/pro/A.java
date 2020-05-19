package com.bank.pro;

import com.bank.exceptions.InvalidCredentialsException;
import com.bank.main.Customer;
import com.bank.model.DBConfig;
import java.sql.SQLException;

public class A {
    
    private DBConfig db;
    
    public A(){
        this.db = new DBConfig();
    }
    

    public boolean processLogin(String username, String password) throws InvalidCredentialsException {
        if(username.equals("admin")){
            if(password.equals("1234")){
                return true;
            }
        }
        throw new InvalidCredentialsException();
    }

    public void createAccount(Customer c) throws SQLException, ClassNotFoundException{
        db.createAccount(c);
    }
    
    
}
