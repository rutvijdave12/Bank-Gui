package com.bank.main;

import com.bank.screens.Login;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        /*Accessing the login page*/
        Runnable r = new Runnable(){
            /*We require a runnable object to run a thread.Runnable is an 
            interface so we cannot instantiate therefore we will have to 
            override the run method.
            */
            @Override
            public void run() {
                /*Creating a login object*/
                Login login  = new Login();
                /*Setting setVisible to true which will show the page*/
                login.setVisible(true);
            }
            
        };
        /*The reference of runnable interface is given inside invokeLater*/
        SwingUtilities.invokeLater(r);
        /*SwingUtilities starts the thread Runabble does not start the thread.
        Whenever we have to start a thread we have to override the run method
        of either Thread class or Runnable interface.SwingUtilites requires 
        Runnable interface reference.
        */
            
            
    }
}
    

