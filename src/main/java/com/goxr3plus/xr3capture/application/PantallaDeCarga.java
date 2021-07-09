/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.goxr3plus.xr3capture.application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.sound.midi.ControllerEventListener;

/**
 *
 * @author mario
 */
public class PantallaDeCarga {
    public Stage stage;
    public PantallaDeCarga(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PantallaDeCarga.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);            
            // set up the stage
            stage = new Stage();
            stage.setTitle("Cargando por favor espere...");
            stage.setWidth(220);
            stage.setHeight(180);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.centerOnScreen();
        } catch (IOException ex) {
            Logger.getLogger(PantallaDeCarga.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
