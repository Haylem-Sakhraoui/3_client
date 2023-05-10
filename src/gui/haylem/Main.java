/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

/**
 *
 * @author HAYLEM SAKHRAOUI
 */
public class Main extends Application{
    
    public static void main(String[] args) {
        launch(args);
        
        
     Parent parent;
     
     Stage stage;
    }
    @Override
    public void start(Stage primaryStage)throws Exception{
        
//        String userRole = getUserRole(); 
//
//    // Load the appropriate FXML file based on the user's role
//    Parent root;
//    if (userRole.equals("recruiter")) {
//        root = FXMLLoader.load(getClass().getResource("consultPage.fxml"));
//    } else if (userRole.equals("freelancer")) {
//        root = FXMLLoader.load(getClass().getResource("profilespeech.fxml"));
//    } else {
//        // Handle invalid user role
//        throw new IllegalArgumentException("Invalid user role: " + userRole);
//    }
//
//    Scene scene = new Scene(root);
//    primaryStage.setScene(scene);
//    primaryStage.setTitle("TN-JOB");
//    primaryStage.show();    
        

  
        Parent root = FXMLLoader.load(getClass().getResource("profilespeech.fxml"));
        
        Scene scene =new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TN-JOB");
        primaryStage.show();
        
        
    }
    }

