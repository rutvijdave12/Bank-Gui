package com.bank.exceptions;

public class InvalidCredentialsException extends Exception {
    public String getMessage(){
        return "Invalid Credentials";
    }
}
