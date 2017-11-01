/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Momo
 */
public class EstudianteUniversidad extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/FXMLDocument.fxml"));
        stage.setTitle("Universidad");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void changeScene(String fxml, ActionEvent event) throws IOException {
        Parent homeParent = FXMLLoader.load(EstudianteUniversidad.class.getResource("/Vista/"+fxml));
        Scene homeScene = new Scene(homeParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.hide();
        appStage.setScene(homeScene);
        appStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        
    }
    
}
