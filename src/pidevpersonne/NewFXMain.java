/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevpersonne;


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Salim Ben Hamida
 */
public class NewFXMain extends Application {

    Parent root;
    Stage stage;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        try {
            root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        stage.getIcons().add(new Image("pidevpersonne/logo.jpg"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("TN-Job application");

        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
