
package com.bank.exceptions;

public class InsufficientFundsException extends Exception {
    
    public String getMessage(){
        return "Insufficient Funds";
    }
    
}
