package com.bank.model;

//Use java.sql package not mysql.jdbc package
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import com.bank.main.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class DBConfig {
    //Step 1: Providing information of the database
    String userDB="root";//Name of the user that has the permission to access the database
    //by default our user is administrator and the name of our administrator is "root"
    String password="";//No password was set
    String url="jdbc:mysql://localhost:3306/MySwingDB";/*Since we are accessing db from java we 
need the location of that database. 3306 is the port number of mysql.If we are using any other sql
    then just replace mysql by that the port number remains the same.If we want to connect to any 
    remote db then replace localhost by that ip*/
    String driver="com.mysql.jdbc.Driver";/*Providing a driver just like installing a printer driver
before using*/
    private Connection con;
    //Step2:Load the driver and make the connection
    private void dbConnect() throws ClassNotFoundException,SQLException {
        Class.forName(driver);//Suppose the class is not found therefore we need to throw a warning
        //Making a connection
        con = DriverManager.getConnection(url, userDB, password);
        //if our db is configured for not having any user than use only url input or else use the above one
        //This might throw a SQLException therefore throw a warning
      
    }
    private void dbClose() throws SQLException {
        //Method to close connection
        con.close();//might throw SQLException so thraw a warning 
        
    }
    public void createAccount(Customer c) throws SQLException,ClassNotFoundException{
        dbConnect();
        //Logic related to database will be written in between
        //writing a sql query string
        String sql = "insert into customer(name,address,email,accountNumber,balance) "
                + "values (?,?,?,?,?)";
        /*We will provide everything to the database except for id since the db will automatically 
        do it.Initially we won't provide any values(because of SQLInjection attack) so put question marks
        */
        PreparedStatement pstmt = con.prepareStatement(sql);
        //Passing the values
        pstmt.setString(1,c.getName());//1 means the 1st questionmark and it represents the value of the 1st parameter i.e. name
        pstmt.setString(2,c.getAddress());
        pstmt.setString(3,c.getEmail());
        pstmt.setString(4,c.getAccountNumber());
        pstmt.setString(5,c.getBalance());
        pstmt.executeUpdate();//Executes sql statement
        dbClose();
    }

    public boolean checkAccountNumber(String accountNumber) throws ClassNotFoundException, SQLException {
        dbConnect();
        //Creating a sql query
        int count = 0;
        String sql ="select * from customer where accountNumber=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,accountNumber);
        ResultSet rst = pstmt.executeQuery();//executeQuery is to fetch info whereas executeUpdate is for create,delete,update info 
        while(rst.next()){//if account number is present it returns true and goes into while(see javadoc)
          count = 1;  
        }
        dbClose();
        if(count==1){
            return true;
        }
        return false;
    }

    public Customer fetchCustomerDetails(String accountNumber) throws ClassNotFoundException, SQLException {
        dbConnect();
        Customer c = new Customer();
        String sql = "select * from customer where accountNumber=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,accountNumber);
        ResultSet rst = pstmt.executeQuery();
        while(rst.next()){
            c.setName(rst.getString(2));
            c.setAddress(rst.getString(3));
            c.setEmail(rst.getString(4));
            c.setAccountNumber(rst.getString(5));
            c.setBalance(rst.getString(6));
        }
        dbClose();
        return c;
        
    }

    public void saveTransaction(Customer c, String amount) throws ClassNotFoundException, SQLException {
        dbConnect();

        String sql = "insert into transactions(accountNumber,amount,initialBalance,finalBalance,dateOfTransaction,operation) "
                + "values (?,?,?,?,?,?)";
        
        
        double amt = Double.parseDouble(amount);
        double iniBal = Double.parseDouble(c.getBalance());
        double finalBal = amt + iniBal;
        PreparedStatement pstmt = con.prepareStatement(sql);
        //Passing the values
        pstmt.setString(1,c.getAccountNumber());
        pstmt.setString(2,amount);
        pstmt.setString(3,c.getBalance());
        pstmt.setString(4,Double.toString(finalBal));
        pstmt.setString(5,LocalDate.now().toString());
        pstmt.setString(6,"Deposit");
        pstmt.executeUpdate();//Executes sql statement
        dbClose();
    }

    public void updateBalance(double updatedBalance, Customer c) throws ClassNotFoundException, SQLException {
        dbConnect();

        String sql = "update customer SET balance = ? where accountNumber=?";
    
        PreparedStatement pstmt = con.prepareStatement(sql);
        //Passing the values
        pstmt.setString(1,Double.toString(updatedBalance));
        pstmt.setString(2,c.getAccountNumber());

        pstmt.executeUpdate();//Executes sql statement
        dbClose();
    }

    public void saveWithdrawalTransaction(Customer c, String amount) throws SQLException, ClassNotFoundException {
            dbConnect();

        String sql = "insert into transactions(accountNumber,amount,initialBalance,finalBalance,dateOfTransaction,operation) "
                + "values (?,?,?,?,?,?)";
        
        
        double amt = Double.parseDouble(amount);
        double iniBal = Double.parseDouble(c.getBalance());
        double finalBal = iniBal - amt;
        PreparedStatement pstmt = con.prepareStatement(sql);
        //Passing the values
        pstmt.setString(1,c.getAccountNumber());
        pstmt.setString(2,amount);
        pstmt.setString(3,c.getBalance());
        pstmt.setString(4,Double.toString(finalBal));
        pstmt.setString(5,LocalDate.now().toString());
        pstmt.setString(6,"Withdrawal");
        pstmt.executeUpdate();//Executes sql statement
        dbClose();
    }

    public Customer fetchTransactions(String accountNumber) throws ClassNotFoundException, SQLException {
            dbConnect();
            Customer c = new Customer();
            String sql="select * from customer where accountNumber=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, accountNumber);
            ResultSet rst = pstmt.executeQuery();
            while(rst.next()){
                c.setName(rst.getString(2));
                c.setAddress(rst.getString(3));
                c.setEmail(rst.getString(4));
                c.setAccountNumber(rst.getString(5));
                c.setBalance(rst.getString(6));
            }
            dbClose();
            return c;
    }
    
    
    
}
