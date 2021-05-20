/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.goxr3plus.xr3capture.application;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author mario
 */
public class VisualizarImagenes {
     private Image imagen;
     public static Stage stage = new Stage();
     
     public VisualizarImagenes(Image imagen){
        this.imagen = imagen;
        ImageView imageView = new ImageView(imagen);
        HBox hbox = new HBox(imageView);

        Scene scene = new Scene(hbox, 500, 500);
        stage.setScene(scene);
        stage.show();
     }
     
}
