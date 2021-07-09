/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.goxr3plus.xr3capture.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mario
 */
public class DataBaseController {
    private final int numeroIntentos = 5;
    public DataBaseController(){
        
    }
    public void insertarCliente(String idAsociado){
        DataBaseConection connectNow = new DataBaseConection();
        Connection connectDB = connectNow.getConnection();
        
        String connectQuery = "INSERT INTO ss_verify (idAsociado, intentos) VALUES ('" + idAsociado + "', 0);";
        
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(connectQuery);
            connectDB.close();
        } catch (Exception e) {
            try {
                connectDB.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataBaseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void verificarExistencia(String idAsociado){
        DataBaseConection connectNow = new DataBaseConection();
        Connection connectDB = connectNow.getConnection();
        String connectQuery = "SELECT * FROM ss_verify WHERE idAsociado = '" + idAsociado + "';";
        
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);           
            if(!queryOutput.first()){
                insertarCliente(idAsociado);
            }
             connectDB.close();
        } catch (Exception e) {
            try {
                connectDB.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataBaseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void sumarIntento(String idAsociado){
        
        DataBaseConection connectNow = new DataBaseConection();
        Connection connectDB = connectNow.getConnection();
        
        String connectQuery = "UPDATE ss_verify set intentos = intentos + 1 WHERE idAsociado ='" + idAsociado + "'";
        
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(connectQuery);
            connectDB.close();
        } catch (Exception e) {
            try {
                connectDB.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataBaseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public int verificarIntentos(String idAsociado){
        int intentos = 0;
        DataBaseConection connectNow = new DataBaseConection();
        Connection connectDB = connectNow.getConnection();
        String connectQuery = "SELECT intentos FROM ss_verify WHERE idAsociado = '" + idAsociado + "';";
        
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);            
            if(queryOutput.next()){
                if((queryOutput.getInt("intentos") + 1) >= 5){
                    if((queryOutput.getInt("intentos") + 1) == 5){
                        sumarIntento(idAsociado);
                    }
                    
                }else{
                    sumarIntento(idAsociado);
                }
                intentos = queryOutput.getInt("intentos") + 1;
            }
            connectDB.close();
        } catch (Exception e) {
            try {
                connectDB.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataBaseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return intentos;
    }
    public void reiniciarIntentos(String idAsociado){
        DataBaseConection connectNow = new DataBaseConection();
        Connection connectDB = connectNow.getConnection();
        
        String connectQuery = "UPDATE ss_verify set intentos = 0 WHERE idAsociado ='" + idAsociado + "'";
        
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(connectQuery);
            connectDB.close();
        } catch (Exception e) {
            try {
                connectDB.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataBaseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
