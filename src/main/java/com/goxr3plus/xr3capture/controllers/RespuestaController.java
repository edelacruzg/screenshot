/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.goxr3plus.xr3capture.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author mario
 */
public class RespuestaController {
    
    @FXML
    public Button NuevoMatch;
    
    @FXML
    public Button RechazarExpediente;
    
    @FXML
    public Button Aceptar;
    
    @FXML
    private Button GuardarMotivo;
    
    @FXML
    private TextArea txtMotivo;
    
    @FXML
    private Label lblIdAsociado;
    
    @FXML
    private Label lblTextoRespuesta;
    
    @FXML 
    private ImageView imgRespuesta;
    
    @FXML
    private StackPane panelPrincipal;
    
    public RespuestaController(){
        
    }
    public void initialize(){
       
    }
    public void setTextoRespuesta(String texto){
        lblTextoRespuesta.setText(texto);
    }
    public void setIdAsociado(String idAsociado){
        lblIdAsociado.setText(idAsociado);
    }
    public void setImagenRespuesta(String status){
        try {
            BufferedImage image;
            image = (status.equals("000")) ? ImageIO.read(getClass().getResource("/image/success.png")) : ImageIO.read(getClass().getResource("/image/cross.png"));
            imgRespuesta.setImage(SwingFXUtils.toFXImage(image, null));
        } catch (IOException ex) {
            Logger.getLogger(RespuestaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ocultarBotones(String code){
            if(code.equals("000")){
                NuevoMatch.setVisible(false);
                RechazarExpediente.setVisible(false);
            }else{
                Aceptar.setVisible(false);
            }
    }
}
