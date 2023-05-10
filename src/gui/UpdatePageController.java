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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Salim Ben Hamida
 */
public class UpdatePageController implements Initializable {

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
    @FXML
    private Button updateBtn;
    private ToggleGroup tg = new ToggleGroup();
    User currentUser;
    Parent mainPage;
    Stage stage;

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
        System.out.println(currentUser);

    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void updateRequest(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Are you sure to update the current user");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                System.out.println(currentUser);
                UserService sp = new UserService();
                RadioButton rb = (RadioButton) tg.getSelectedToggle();
                String role = rb.getText();
                sp.updateRole(currentUser, role);
                sp.updateUserEmail(currentUser, EmailField.getText());
                sp.updateUserName(currentUser, nameField.getText());
                sp.updateUserPassword(currentUser, passwordField.getText());
            } catch (SQLException ex) {
                Logger.getLogger(UpdatePageController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void setCurrentUser(User u) {
             this.currentUser=u;
    }

    public void setFields(String name, String password, String email, String role) {
        nameField.setText(name);
        passwordField.setText(password);
        EmailField.setText(email);
        System.out.println(role);
        if (role.equals("freelancer")) {
            role1.setSelected(true);
        }
        if (role.equals("recruteur")) {
            role2.setSelected(true);
        }
        if (role.equals("candidat")) {
            role3.setSelected(true);
        }
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

}
