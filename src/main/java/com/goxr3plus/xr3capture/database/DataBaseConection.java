/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.goxr3plus.xr3capture.database;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author mario
 */
public class DataBaseConection {
    public Connection databaseLink;
    
    public Connection getConnection(){
        String databaseName = "TEMP_BACK";
        String databaseUser = "GITUser";
        String databasePassword = "G17U53r_25$";
        String databaseHost = "74.208.84.208:3306/";
        String url = "jdbc:mysql://" + databaseHost + databaseName;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }catch(Exception e){
            e.printStackTrace();
        }
        return databaseLink;
    }
}
