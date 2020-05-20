package com.bank.exceptions;

public class InvalidAccountNumberException extends Exception{
    public String getMessage(){
        return "Invalid Account Number";
    }
    
}
