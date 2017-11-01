 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Estudiante;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Momo
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField TFNombre, TFCodigo,TFSemestre;
    @FXML
    private ImageView IV;
    
    private DB db;
    private DBCollection colEstudiante;
    private String srcimg;
    private Image image;
    private File file;
    
    @FXML
    private void handleButtonIngresarEstudiante(ActionEvent event) {
        String nombre = TFNombre.getText();
        String codigo = TFCodigo.getText();
        String semestre = TFSemestre.getText();
        Estudiante estudiante = new Estudiante(nombre,codigo,semestre);
        colEstudiante.insert(estudiante);
    }
    
    @FXML
    private void handleButtonSalir(ActionEvent event) {
        
    }
    
    @FXML
    private void handleButtonSubirFoto(ActionEvent event) {
        Stage st = new Stage(StageStyle.UTILITY);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Archivos JPG");
        //filtro para solo traer imagenes
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg"));
        //trae la ruta del archivo
        file = fileChooser.showOpenDialog(st);
        image = new Image(file.toURI().toString());

        IV.setImage(image);
        srcimg = file.getAbsolutePath();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = Util.conectarBaseDatos();
        colEstudiante = Util.conectarCollection(db, Estudiante.class);

    }    
    
}
