/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.UserService;
import services.UserSession;

/**
 * FXML Controller class
 *
 * @author Salim Ben Hamida
 */
public class AdminDashboardController implements Initializable {

    @FXML
    private Button btnOverview;
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
    private TableColumn<User, Integer> idField;
    @FXML
    private TableColumn<User, String> UsernameField;
    @FXML
    private TableColumn<User, String> EmailField;
    @FXML
    private TableColumn<User, String> PasswordField;
    @FXML
    private TableColumn<User, String> RoledField;
    private TableColumn<User, String> EditField;
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> tokenField;
    @FXML
    private TableColumn<User, String> createdField;
    @FXML
    private TableColumn<User, String> updatedField;
    @FXML
    private Button addBtn;
    @FXML
    private Button modBtn;
    @FXML
    private Button delBtn;
    Parent MainPage,addPage, updatePage;
    Stage stage;
    UserSession session;
    User u;
    @FXML
    private Button btnModule;
    @FXML
    private Label welcomeText;
    @FXML
    private Button btnModule1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillTable();
        u = getCurrentUser();
        welcomeText.setText(welcomeText.getText()+" "+u.getUsername());

    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void addWindow(ActionEvent event) {
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/addPage"));
            session = UserSession.getInstance();
            session.setU(u);
            addPage = FXMLLoader.load(getClass().getResource("/gui/addPage.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Scene scene = new Scene(addPage);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setScene(scene);
        stage.show();
    }

    private void fillTable() {
        try {
            UserService sp = new UserService();
            ObservableList<User> users = FXCollections.observableArrayList(sp.showUser());
            idField.setCellValueFactory(new PropertyValueFactory<>("id"));
            UsernameField.setCellValueFactory(new PropertyValueFactory<>("username"));
            EmailField.setCellValueFactory(new PropertyValueFactory<>("email"));
            PasswordField.setCellValueFactory(new PropertyValueFactory<>("password"));
            RoledField.setCellValueFactory(new PropertyValueFactory<>("role"));
            tokenField.setCellValueFactory(new PropertyValueFactory<>("remember_token"));
            createdField.setCellValueFactory(new PropertyValueFactory<>("created_at"));
            updatedField.setCellValueFactory(new PropertyValueFactory<>("updated_at"));
            userTable.setItems(users);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void deleteUser(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Are you sure to delete the current user");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                UserService sp = new UserService();
                sp.deleteUser(userTable.getSelectionModel().getSelectedItem());
                fillTable();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    @FXML
    private void modifyWindow(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("updatePage.fxml"));
            Parent root = loader.load();
            User u = userTable.getSelectionModel().getSelectedItem();
            UpdatePageController c = loader.getController();
            c.setFields(u.getUsername(), u.getPassword(), u.getEmail(), u.getRole());
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public User getCurrentUser() {
        session = UserSession.getInstance();
        return session.getU();

    }

    @FXML
    private void Exit(ActionEvent event) {
        session.destorySession();
        System.exit(0);
    }

    @FXML
    private void accessModule(ActionEvent event) {
          try {
                            MainPage = FXMLLoader.load(getClass().getResource("/gui/categoriesMain.fxml"));
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
              Scene scene = new Scene(MainPage);
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                    new SplashScreenDemo();
                    stage.setScene(scene);
                    stage.show();
    }

    @FXML
    private void accessUser(ActionEvent event) {
          try {
                            MainPage = FXMLLoader.load(getClass().getResource("/gui/userDashboard.fxml"));
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
              Scene scene = new Scene(MainPage);
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                    new SplashScreenDemo();
                    stage.setScene(scene);
                    stage.show();
    }

}
