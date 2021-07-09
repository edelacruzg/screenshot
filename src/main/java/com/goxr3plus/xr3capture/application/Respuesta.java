/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.goxr3plus.xr3capture.application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.com.goxr3plus.xr3capture.controllers.MainWindowController;
import main.java.com.goxr3plus.xr3capture.controllers.RespuestaController;

/**
 *
 * @author mario
 */
public class Respuesta {
    public Stage stage;
    public RespuestaController respuestaController;
    public MainWindowController mainWindowController;
    public Respuesta(MainWindowController mainWindowController){
        try {
            this.mainWindowController = mainWindowController;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Resultado.fxml"));
            Parent root = loader.load();
            respuestaController = loader.getController();
            respuestaController.NuevoMatch.addEventHandler(MouseEvent.MOUSE_CLICKED,  (MouseEvent event) -> {
                stage.close();
                this.mainWindowController.reailzarNuevoFaceMatch();
            });
            respuestaController.RechazarExpediente.addEventHandler(MouseEvent.MOUSE_CLICKED,  (MouseEvent event) -> {
                stage.close();
                this.mainWindowController.rechazarExpediente();
            });
            respuestaController.Aceptar.addEventHandler(MouseEvent.MOUSE_CLICKED,  (MouseEvent event) -> {
                stage.close();
                this.mainWindowController.rechazarExpediente();
            });
            Scene scene = new Scene(root);            
            // set up the stage
            stage = new Stage();
            stage.setTitle("RESPUESTA");
            stage.setWidth(300);
            stage.setHeight(600);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.initStyle(StageStyle.UNDECORATED);
            
        } catch (IOException ex) {
            Logger.getLogger(Respuesta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
