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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import services.UserService;
import services.UserSession;

/**
 * FXML Controller class
 *
 * @author Salim Ben Hamida
 */
public class AddPageController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnPackages1;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button addBtn;
    @FXML
    private Button modBtn;
    @FXML
    private Button delBtn;
    @FXML
    private TextField nameField;
    @FXML
    private TextField EmailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private RadioButton role1;
    @FXML
    private RadioButton role2;
    @FXML
    private RadioButton role3;
    private ToggleGroup tg = new ToggleGroup();
    Parent mainPage;
    Stage stage;
    UserSession session;
    User u;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        role1.setToggleGroup(tg);
        role1.setUserData("freelancer");
        role2.setToggleGroup(tg);
        role2.setUserData("recruteur");
        role3.setToggleGroup(tg);
        role3.setUserData("candidat");
         emailValidator();
        passwordValidator();
           u = getCurrentUser();
        System.out.println(u);


    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void addRequest(ActionEvent event) {

        try {
            RadioButton rb = (RadioButton) tg.getSelectedToggle();
            String role = rb.getText();
            UserService sp = new UserService();
            User u = new User(nameField.getText(), EmailField.getText(), passwordField.getText(), role);
            if (sp.findUserByEmail(u.getEmail())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("User Already Exists");
                alert.showAndWait();
                ClearFields();

            } else {
                sp.addUser(u);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText(u.getUsername() + " Added Successfully ");
                alert.showAndWait();
                ClearFields();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void ClearFields() {
        nameField.setText("");
        EmailField.setText("");
        passwordField.setText("");
        role1.setSelected(false);
        role2.setSelected(false);
        role3.setSelected(false);

    }

    @FXML
    private void goToMainScreen(MouseEvent event) {
        try {
            mainPage = FXMLLoader.load(getClass().getResource("/gui/adminDashboard.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Scene scene = new Scene(mainPage);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToMainScreen(ActionEvent event) {
        try {
            mainPage = FXMLLoader.load(getClass().getResource("/gui/adminDashboard.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Scene scene = new Scene(mainPage);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setScene(scene);
        stage.show();
    }
private void emailValidator() {
        EmailField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue && EmailField.getText().isEmpty() == false) { //when focus lost
                if (!EmailField.getText().matches("^(.+)@(\\S+)$")) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Attention");
                    alert.setContentText("Please Verify Your email");
                    alert.showAndWait();
                    EmailField.setText("");
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
        public User getCurrentUser() {
        session = UserSession.getInstance();
        return session.getU();

    }

}
