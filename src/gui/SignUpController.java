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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Salim Ben Hamida
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField emailField;
    @FXML
    private ComboBox<String> Role;

    ObservableList<String> roles = FXCollections.observableArrayList("freelancer", "recruteur", "candidat");
    @FXML
    private Button SignUpbtn;
    Parent mainPage;
    Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Role.setItems(roles);
        emailValidator();
        passwordValidator();

    }

    @FXML
    private void SignUp(ActionEvent event) {
        try {
            UserService sp = new UserService();
            User u = new User(nameField.getText(), emailField.getText(), passwordField.getText(), Role.getValue());

            if (nameField.getText().isEmpty() || passwordField.getText().isEmpty() || emailField.getText().isEmpty() || Role.getValue().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Attention");
                alert.setContentText("Please Enter your credentials");
                alert.showAndWait();

            } else if (nameField.getText().isEmpty()==false || passwordField.getText().isEmpty()==false  || emailField.getText().isEmpty()==false  || Role.getValue().isEmpty()==false ) {
                if (sp.findUserByEmail(emailField.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("User Already Exists");
                    alert.showAndWait();

                } else {
                    sp.addUser(u);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("Welcome to our Application ");
                    alert.showAndWait();

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        try {
                            mainPage = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                        Scene scene = new Scene(mainPage);
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.close();
                        stage.setScene(scene);
                        stage.show();

                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("check your credentials");
                alert.showAndWait();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void emailValidator() {
        emailField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue && emailField.getText().isEmpty() == false) { //when focus lost
                if (!emailField.getText().matches("^(.+)@(\\S+)$")) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Attention");
                    alert.setContentText("Please Verify Your email");
                    alert.showAndWait();
                    emailField.setText("");
                }
            }

        });
    }

    private void passwordValidator() {

        passwordField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue && passwordField.getText().isEmpty() == false) {
                if (!passwordField.getText().matches("^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$")) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Attention");
                    alert.setContentText("Please Verify Your password");
                    alert.showAndWait();
                    passwordField.setText("");
                }
            }

        });
    }

    @FXML
    private void checkEmail(TouchEvent event) {
    }

 

}
