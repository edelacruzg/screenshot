package main.java.com.goxr3plus.xr3capture.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import main.java.com.goxr3plus.xr3capture.application.CaptureWindow;
import main.java.com.goxr3plus.xr3capture.application.ImageBase64;
import main.java.com.goxr3plus.xr3capture.application.Main;
import main.java.com.goxr3plus.xr3capture.application.VisualizarImagenes;
import main.java.com.goxr3plus.xr3capture.model.rest.RESTClient;
import main.java.com.goxr3plus.xr3capture.model.rest.models.CallDocumentsRequest;
import main.java.com.goxr3plus.xr3capture.model.rest.models.CallDocumentsResponse;
import main.java.com.goxr3plus.xr3capture.model.rest.models.ValidatingRequest;
import main.java.com.goxr3plus.xr3capture.model.rest.models.ValidatingResponse;
import sun.misc.BASE64Decoder;

/**
 * The Scene of the primary window of the application.
 *
 * @author GOXR3PLUS
 */
public class MainWindowController {
	
	/** The root. */
	@FXML
	private StackPane root;
	
	/** The more. */
	@FXML
	private JFXButton more;
	
	/** The minimize. */
	@FXML
	private JFXButton minimize;
	
	/** The exit button. */
	@FXML
	private JFXButton exitButton;
	
	/** The time slider. */
	@FXML
	private JFXSlider timeSlider;
	
	/** The capture button. */
	@FXML
	private JFXButton captureButton;
	
	/** The open image viewer. */
	@FXML
	private JFXButton openImageViewer;
	
	/** The region. */
	@FXML
	private Region region;
	
	/** The progress bar. */
	@FXML
	private ProgressIndicator progressBar;
        
        @FXML
        private ImageView fotosCliente;
        
        @FXML
	private JFXButton botonSiguiente;
        
        @FXML
	private JFXButton botonAnterior;
        
        @FXML
	private JFXButton btnINEFrente;
        
        @FXML
	private JFXButton btnINEVuelta;
        
        @FXML
        private ImageView IViewSelfie;
        
        @FXML
        private ImageView IViewINEFrente;
         
        @FXML
        private ImageView IViewINEVuelta;
	
        @FXML
        private JFXButton btnBuscarCliente;
        
        @FXML
        private JFXButton btnSubirImagen;
        
        @FXML
        private TextField txtIDFinsus;
        
        @FXML
        private HBox contenedorFotos;
        
        @FXML
        private VBox contenedorScreenshot;
	// --------------------------------------------
	BufferedImage[] imagenesArray = new BufferedImage[3];
	/** The image viewer thread. */
	// The thread which is opening imageViewer
	private Thread imageViewerThread;
	
	/** The settings window controller. */
	// References from other controllers
	SettingsWindowController settingsWindowController;
	
	/** The capture window controller. */
	CaptureWindowController captureWindowController;
        
        
        private String[] imagenesCliente = new String[3];
        private int posicionImagen = 0;
	
	/**
	 * Add the needed references from the other controllers.
	 *
	 * @param captureWindowController
	 *            the capture window controller
	 * @param settingsWindowController
	 *            the settings window controller
	 */
	@SuppressWarnings("hiding")
	public void addControllerReferences(CaptureWindowController captureWindowController , SettingsWindowController settingsWindowController) {
		
		this.captureWindowController = captureWindowController;
		this.settingsWindowController = settingsWindowController;
	}
	
	/**
	 * Will be called as soon as FXML file is loaded.
	 */
	@FXML
	public void initialize() throws URISyntaxException {
		
		// more
		more.setOnAction(a -> settingsWindowController.show());
		
		// minimize
		minimize.setOnAction(a -> CaptureWindow.stage.setIconified(true));
		
		// exitButton
		exitButton.setText("Cerrar");
		exitButton.setOnAction(a -> {
			CaptureWindow.stage.close();
			//DataBase.saveDataBaseSettings(settingsWindowController);
			// Platform.exit();
		});
		
		// captureButton
		captureButton.setOnAction(a -> captureWindowController.prepareForCapture(imagenesArray, 0));
                btnINEFrente.setOnAction(a -> captureWindowController.prepareForCapture(imagenesArray, 1));
                btnINEVuelta.setOnAction(a -> captureWindowController.prepareForCapture(imagenesArray, 2));
		
		// region
		region.visibleProperty().bind(progressBar.visibleProperty());
		
		// openImageViewer
		openImageViewer.setOnAction(ac -> {
			//	    // isAlive?
			//	    if (imageViewerThread != null && imageViewerThread.isAlive()) {
			//		ac.consume();
			//		return;
			//	    }
			//
			//	    // Open ImageViewer
			//	    String path = "";//= DataBase.getBasePathForClass(DataBase.class);
			//
			//	    imageViewerThread = new Thread(() -> {
			//		Platform.runLater(Notifications.create().title("Processing").text("Opening XR3ImageViewer....\n Current path is: " + path).hideAfter(Duration.millis(2000))::show);
			//
			//		try {
			//
			//		    ProcessBuilder builder = new ProcessBuilder("java", "-jar", path + "XR3ImageViewer.jar");
			//		    Process process = builder.start();
			//		    process.waitFor();
			//
			//		    if (process.exitValue() != 0)
			//			Platform.runLater(Notifications.create().title("Error").text("Can't open XR3ImageViewer!\nBuilder Directory:" + path + "\nTrying to start:" + path + "XR3ImageViewer.jar")
			//				.hideAfter(Duration.millis(2000))::showError);
			//
			//		} catch (IOException | InterruptedException ex) {
			//		    Logger.getLogger(getClass().getName()).log(Level.INFO, null, ex);
			//		}
			//	    });
			//
			//	    imageViewerThread.setDaemon(true);
			//	    imageViewerThread.start();
			
		});
		
		// timeSlider
		timeSlider.setOnScroll(s -> timeSlider.setValue(timeSlider.getValue() + ( s.getDeltaY() > 0 ? 1 : -1 )));
                btnBuscarCliente.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    CallDocumentsRequest req = new CallDocumentsRequest();
                    String idAsociado = txtIDFinsus.getText();
                    if(!idAsociado.trim().isEmpty()){
                        req.setIdAsociado(idAsociado);
                        CallDocumentsResponse cDoc = RESTClient.consultaDocs(req);
                        if(!cDoc.getImageSelfie().trim().isEmpty()){
                            contenedorFotos.setVisible(true);
                            contenedorScreenshot.setVisible(true);
                            botonAnterior.setVisible(true);
                            botonSiguiente.setVisible(true);
                            imagenesCliente[0]=cDoc.getImageSelfie();
                            imagenesCliente[1]=cDoc.getIneFront();
                            imagenesCliente[2]=cDoc.getIneBack();
                            BufferedImage imagenDecode = decodeToImage(imagenesCliente[0]);
                            fotosCliente.setImage(SwingFXUtils.toFXImage(imagenDecode, null));
                        }else{
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("INFORMACIÓN");
                            alert.setHeaderText("NO SE ENCONTRARON FOTOS PARA ESE ID ASOCIADO\n"+idAsociado);
                            alert.showAndWait();
                        }
                        
                    }else{
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("EL ID ASOCIADO NO PUEDE ESTA VACIO");
                        alert.showAndWait();
                    }
                    
                });
                
                botonSiguiente.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        SiguienteImagen();
                    }
                });
                botonAnterior.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        ImagenAnterior();
                    }
                });
                IViewSelfie.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    new VisualizarImagenes(IViewSelfie.getImage());
                });
                 IViewINEFrente.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    new VisualizarImagenes(IViewINEFrente.getImage());
                });
                  IViewINEVuelta.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    new VisualizarImagenes(IViewINEVuelta.getImage());
                    
                });
                btnSubirImagen.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                    try {
                        String[] fotosBase64 = ImageBase64.convertImageToBase64(imagenesArray);
                        ValidatingRequest vr = new ValidatingRequest();
                        vr.setIdAsociado(txtIDFinsus.getText());
                        vr.setSelfie(fotosBase64[0]);
                        vr.setIneFront(fotosBase64[1]);
                        vr.setIneBack(fotosBase64[2]);
                        ValidatingResponse response = RESTClient.validaFaceMatch(vr);
                             
                    } catch (IOException ex) {
                        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                captureButton.setMinHeight(50);
                captureButton.setMinWidth(120);
                captureButton.setMaxHeight(50);
                captureButton.setMaxWidth(120);
                btnINEFrente.setMinHeight(50);
                btnINEFrente.setMinWidth(120);
                btnINEFrente.setMaxHeight(50);
                btnINEFrente.setMaxWidth(120);
                btnINEVuelta.setMinHeight(50);
                btnINEVuelta.setMinWidth(120);
                btnINEVuelta.setMaxHeight(50);
                btnINEVuelta.setMaxWidth(120);
                btnSubirImagen.setMinHeight(30);
                btnSubirImagen.setMinWidth(200);
                btnSubirImagen.setMaxHeight(30);
                btnSubirImagen.setMaxWidth(200);
                IViewSelfie.minHeight(300);
                IViewSelfie.minWidth(300);
               txtIDFinsus.setText("100-10-400");
	}
	
	/**
	 * Gets the time slider.
	 *
	 * @return The TimeSlider
	 */
	public Slider getTimeSlider() {
		return timeSlider;
	}
	
	/**
	 * Gets the root.
	 *
	 * @return The Root of the FXML
	 */
	public StackPane getRoot() {
		return root;
	}
        
        private BufferedImage decodeToImage(String imageString) {
 
        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
        private void SiguienteImagen(){
            this.posicionImagen++;
            this.posicionImagen = (this.posicionImagen >= this.imagenesCliente.length) ? 0 : posicionImagen;
            BufferedImage imagenDecode = decodeToImage(imagenesCliente[this.posicionImagen]);
            fotosCliente.setImage(SwingFXUtils.toFXImage(imagenDecode, null));
        }
        private void ImagenAnterior(){
            this.posicionImagen--;
            this.posicionImagen = (this.posicionImagen < 0 ) ? (this.imagenesCliente.length - 1) : posicionImagen;
            BufferedImage imagenDecode = decodeToImage(imagenesCliente[this.posicionImagen]);
            fotosCliente.setImage(SwingFXUtils.toFXImage(imagenDecode, null));
        }
        public void ponerImagen(BufferedImage[] imagenes){
            this.imagenesArray = imagenes;
            for(int c = 0; c < imagenes.length; c++){
                if(imagenes[c] != null){
                  switch(c){
                      case 0:
                          IViewSelfie.setImage(SwingFXUtils.toFXImage(imagenes[c], null));
                      break;
                      case 1:
                          IViewINEFrente.setImage(SwingFXUtils.toFXImage(imagenes[c], null));
                      break;
                      case 2:
                          IViewINEVuelta.setImage(SwingFXUtils.toFXImage(imagenes[c], null));
                      break;
                  }
                      
                  
                }
                
            }
        }
}
