package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;

import org.h2.command.Prepared;

/**
 * The database has already created a table named 'Account'
 * The values contained are:
 * account_id integer primary key auto_increment,
 * username varchar(255),
 * password varchar(255)
*/ 
public class AccountDAO {
    //Insert a new account
    public Account insertAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "INSERT INTO account (username, password) VALUES (?, ?);" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()) {
                int generated_account_id = (int) pkeyResultSet.getLong(1);
                return new Account(generated_account_id, account.getUsername(), account.getPassword());
            }
            return null;
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    } //end insertAccount

    //Verify the login
    public Account verifyAccount(String username, String password, Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "SELECT account_id, username, password FROM account WHERE username = ? AND password = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                account = new Account(rs.getInt("account_id"),
                                    rs.getString("username"),
                                    rs.getString("password"));
                return account;
            } //end while loop
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}