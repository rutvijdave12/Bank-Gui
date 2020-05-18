package com.bank.pro;

import com.bank.exceptions.InvalidCredentialsException;

public class A {

    public boolean processLogin(String username, String password) throws InvalidCredentialsException {
        if(username.equals("admin")){
            if(password.equals("1234")){
                return true;
            }
        }
        throw new InvalidCredentialsException();
    }
    
    
}
