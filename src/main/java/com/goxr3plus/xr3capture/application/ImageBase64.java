/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.goxr3plus.xr3capture.application;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import sun.misc.IOUtils;
/**
 *
 * @author mario
 */
public class ImageBase64 {
    public String readStringImage() throws URISyntaxException{
        String text = "";
        try {
            
          //text = new String(Files.readAllBytes(Paths.get("C:\\repositorios\\screenshot\\resources\\txt\\fotoBase64.txt")));
          //text = new String(Files.readAllBytes(getClass().getResourceAsStream("/txt/fotoBase64.txt")));
          text = new String(Files.readAllBytes(Paths.get(getClass().getResource("/txt/fotoBase64.txt").toURI())));
        } catch (IOException e) {
          e.printStackTrace();
        }

    return text;
        
    }
    /*public void whenStoringInFileTooLongString_thenNoCompilationError() throws IOException {
    FileInputStream fis = new FileInputStream("src/test/resources/stringtoolong.txt");
    String stringTooLong;
        stringTooLong = IOUtils.toString(fis, "UTF-8");
    assertThat(stringTooLong).isNotEmpty();
}

    private Object assertThat(String stringTooLong) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
