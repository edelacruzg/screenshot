/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.goxr3plus.xr3capture.application;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import javax.imageio.ImageIO;
/**
 *
 * @author mario
 */
public class ImageBase64 {

    private static byte[] toByteArray(BufferedImage bi, String format) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;
    }
    public String readStringImage() throws URISyntaxException{
        String text = "";
        try {
            
          text = new String(Files.readAllBytes(Paths.get(getClass().getResource("/txt/fotoBase64.txt").toURI())));
        } catch (IOException e) {
          e.printStackTrace();
        }

    return text;
        
    }
    public static String[] convertImageToBase64(BufferedImage[] imagenes) throws IOException{
        String[] imagenBase64 = new String[imagenes.length];
        for(int c = 0; c < imagenes.length; c++){
            BufferedImage bi = imagenes[c];
            byte[] bytes = toByteArray(bi, "png");
            String encodedString = Base64.getEncoder().encodeToString(bytes);
            imagenBase64[c] = encodedString;
        }
        
        return imagenBase64;
    }  
}
