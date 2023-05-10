/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import services.UserService;
import services.UserSession;

/**
 * FXML Controller class
 *
 * @author Salim Ben Hamida
 */
public class LoginController implements Initializable {

    Parent SignUpPage, MainPage;
    Stage stage;
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button LoginBtn;
    @FXML
    private Hyperlink SignupLink;
    User u;
    UserSession session;
    private Group group;
    @FXML
    private StackPane root;
    private Captcha captcha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        captcha = new Captcha();

        captcha.setSummand1(new Random().nextInt(101));
        captcha.setSummand2(new Random().nextInt(101));
        captcha.setScaleX(1.0);
        captcha.setScaleY(1.0);
        root.getChildren().add(captcha);
        LoginBtn.setDisable(true);

    }

    @FXML
    private void Login(ActionEvent event) {

        try {
            UserService sp = new UserService();

            if (nameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Attention");
                alert.setContentText("Please Enter your credentials");
                alert.showAndWait();
            } else if (nameField.getText().isEmpty() == false && passwordField.getText().isEmpty() == false) {
                if (captcha.isIsClicked()) {
                    if (sp.findUser(new User(nameField.getText(), passwordField.getText())) != null) {
                        u = sp.findUser(new User(nameField.getText(), passwordField.getText()));
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setContentText("Welcome to our Application ");
                        alert.showAndWait();
                        startSession();
                        if (u.getRole().equals("admin")) {
                            try {
                                MainPage = FXMLLoader.load(getClass().getResource("/gui/adminDashboard.fxml"));
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }

                        } else if (u.getRole().equals("freelancer")) {
                            
                            try {
                                MainPage = FXMLLoader.load(getClass().getResource("/gui/haylem/profilespeech.fxml"));
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
//                            Alert alert1 = new Alert(Alert.AlertType.ERROR);
//                            alert.setTitle("Not Found");
//                            alert.setContentText("Freelnacer space is under construction");
//                            alert.showAndWait();
//                            System.exit(0);

                        } else {
                            try {
                                MainPage = FXMLLoader.load(getClass().getResource("/gui/userDashboard.fxml"));
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                        Scene scene = new Scene(MainPage);
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.close();
                        new SplashScreenDemo();
                        stage.setScene(scene);
                        stage.show();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Captcha");
                        alert.setContentText("check your credentials");
                        alert.showAndWait();

                    }

                }
                else
                {   Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Human Verification Failed");
                        alert.showAndWait();
                }
                

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void RedirectToSignUp(ActionEvent event) {
        try {
            SignUpPage = FXMLLoader.load(getClass().getResource("/gui/SignUp.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Scene scene = new Scene(SignUpPage);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    private void startSession() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("adminDashboard.fxml"));
        session = UserSession.getInstance();
        session.setU(u);

    }

    @FXML
    private void armBtn(MouseEvent event) {
        if (passwordField.getText().isEmpty() == false) {
            LoginBtn.setDisable(false);
        } else {
            LoginBtn.setDisable(true);
        }
    }
}
