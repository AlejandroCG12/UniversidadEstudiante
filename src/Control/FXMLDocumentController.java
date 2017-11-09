/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Estudiante;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Momo
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField TFNombre, TFCodigo, TFSemestre;
    @FXML
    private ImageView imageView;
    @FXML
    private ImageView imageViewMostrar;
    @FXML
    private Button botonAnteriorImagen;
    @FXML
    private Button botonSiguienteImagen;

    private DB db;
    private DBCollection colEstudiante;
    private String srcimg;
    File file;
    byte[] filecontent = null;
    private Image image;
    private int contador;
    private LinkedList<Image> imagenesList;

    @FXML
    private void handleButtonIngresarEstudiante(ActionEvent event) {
        String nombre = TFNombre.getText();
        String codigo = TFCodigo.getText();
        String semestre = TFSemestre.getText();
        Estudiante estudiante = new Estudiante(nombre, codigo, semestre, filecontent);
        colEstudiante.insert(estudiante);
    }

    @FXML
    private void handleButtonSalir(ActionEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
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
        srcimg = file.getAbsolutePath();
        image = new Image(file.toURI().toString());
        try {
            filecontent = Files.readAllBytes(file.toPath());
        } catch (IOException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
        imageView.setImage(image);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        botonAnteriorImagen.setVisible(false);
        imagenesList = new LinkedList<>();
        contador = 0;
        db = Util.conectarBaseDatos();
        colEstudiante = Util.conectarCollection(db, Estudiante.class);
    }

    @FXML
    private void handleButtonMostrar(ActionEvent event) throws IOException {
        BasicDBObject fields = new BasicDBObject("foto", 1);
        LinkedList<Estudiante> estudiante = (LinkedList<Estudiante>) Util.buscar(colEstudiante, Estudiante.class, null, fields);
        for (Estudiante estudiante1 : estudiante) {
            InputStream in = new ByteArrayInputStream(estudiante1.getFoto());
            BufferedImage imageB = ImageIO.read(in);
            Image imagen = SwingFXUtils.toFXImage(imageB, null);
            imagenesList.add(imagen);
        }
        imageViewMostrar.setImage(imagenesList.getFirst());
    }

    @FXML
    private void handleButtonActionAnteriorImagen(ActionEvent event) throws IOException {
        if (contador > 0) {
            contador--;
        }
        if (contador == 0) {
            botonAnteriorImagen.setVisible(false);
        }

        botonSiguienteImagen.setVisible(true);

        actualizarImagen();
    }

    @FXML
    private void handleButtonActionSiguienteImagen(ActionEvent event) throws IOException {
        if (contador < (imagenesList.size() - 1)) {
            contador++;
        }

        if (contador == (imagenesList.size() - 1)) {
            botonSiguienteImagen.setVisible(false);
        }

        botonAnteriorImagen.setVisible(true);

        actualizarImagen();
    }

    private void actualizarImagen() {
        Image image = imagenesList.get(contador);
        imageViewMostrar.setImage(image);
    }
}
