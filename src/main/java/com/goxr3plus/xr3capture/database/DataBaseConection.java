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
    //PRUEBAS
    /*private final String databaseName = "";
    private final String databaseUser = "";
    private final String databasePassword = "";
    private final String databaseHost = "";*/
    //PRODUCCION
    private final String databaseName = "";
    private final String databaseUser = "";
    private final String databasePassword = "";
    private final String databaseHost = "";

    public Connection getConnection(){
        
        String url = "jdbc:mysql://" + databaseHost + databaseName +"?serverTimezone=UTC";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }catch(Exception e){
            e.printStackTrace();
        }
        return databaseLink;
    }
}
