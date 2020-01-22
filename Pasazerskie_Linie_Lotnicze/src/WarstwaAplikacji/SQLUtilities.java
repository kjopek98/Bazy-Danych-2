package WarstwaAplikacji;

import WarstwaBiznesowa.*;
import Model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;


public class SQLUtilities {
    //public static final String URL = "jdbc:sqlserver://localhost;databaseName=FlightStore;user=Admin;password=admin;";
    public static Connection connection;
    public SQLUtilities(){ }
    public static boolean Connect(String URL){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(URL);
            return true;
        }
        catch(ClassNotFoundException ex){
            return false;
        }
        catch(SQLException ex){
            System.out.println(ex);
            return false;
        }
    }
    public static boolean Disconnect(){
        try{
            connection.close();
            return true;
        }
        catch(SQLException ex){
            return false;
        }
    }
    public static int ExecuteNonQuery(PreparedStatement preparedStatement){
        try{
            return preparedStatement.executeUpdate();
        }
        catch(SQLException ex){
            return -1;
        }
    }
    public static ResultSet ExecuteQuery(PreparedStatement sql){
        try{
            return sql.executeQuery();
        }
        catch(SQLException ex){
            Logger.getLogger(SQLUtilities.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
