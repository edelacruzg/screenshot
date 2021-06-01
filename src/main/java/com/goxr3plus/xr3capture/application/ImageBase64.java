/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.goxr3plus.xr3capture.application;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 *
 * @author mario
 */
public class ImageBase64 {
    public String readStringImage() throws URISyntaxException{
        String text = "";
        try {
            
          text = new String(Files.readAllBytes(Paths.get(getClass().getResource("/txt/fotoBase64.txt").toURI())));
        } catch (IOException e) {
          e.printStackTrace();
        }

    return text;
        
    }
}
