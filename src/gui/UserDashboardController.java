/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.UserSession;

/**
 * FXML Controller class
 *
 * @author Salim Ben Hamida
 */
public class UserDashboardController implements Initializable {

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
    private Label userLabel;
    UserSession session;
    User u;
    @FXML
    private Button btnVar;
    Parent MainPage;
    Stage stage;
    @FXML
    private Button btnRes;
    @FXML
    private Label welcomeText;
    @FXML
    private Label text1;
    @FXML
    private Label text2;
    @FXML
    private BorderPane pane1;
    @FXML
    private ImageView redirect1;
    @FXML
    private BorderPane pane2;
    @FXML
    private ImageView redirect2;
    @FXML
    private BorderPane pane3;
    @FXML
    private Label text3;
    @FXML
    private HBox box;
    @FXML
    private Button btnPos;
    @FXML
    private ImageView redirect3;
    @FXML
    private Button addCmt;
    @FXML
    private Button addPub;
    @FXML
    private Button btnRec;
    @FXML
    private Button btnPortfolio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        u = getCurrentUser();
        userLabel.setText(userLabel.getText() + " " + u.getUsername());
        if (u.getRole().equals("recruteur")) {
            pane3.setOpacity(0.40);
            pane3.setDisable(true);
            text3.setOpacity(0.40);
            btnPos.setDisable(true);
            btnPos.setOpacity(0.40);

        } else if (u.getRole().equals("candidat")) {
            pane2.setOpacity(0.40);
            pane2.setDisable(true);
            text2.setOpacity(0.40);
            pane1.setOpacity(0.40);
            pane1.setDisable(true);
            text1.setOpacity(0.40);
            btnRes.setDisable(true);
            btnRes.setOpacity(0.40);
            btnVar.setDisable(true);
            btnVar.setOpacity(0.40);
        }
        welcomeText.setText(welcomeText.getText() + " " + u.getUsername());
    }

    @FXML
    private void handleClicks(ActionEvent event) {
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
    private void accessReservation(ActionEvent event) {
            System.out.println("done");
            try {
                MainPage = FXMLLoader.load(getClass().getResource("/gui/ReservationFreelancer.fxml"));
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
    private void accessReservation(MouseEvent event) {
            System.out.println("done");
            try {
                MainPage = FXMLLoader.load(getClass().getResource("/gui/ReservationFreelancer.fxml"));
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
    private void accessDem(ActionEvent event) {
        try {
            MainPage = FXMLLoader.load(getClass().getResource("/gui/FXMLRecruteur.fxml"));
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
    private void accessDem(MouseEvent event) {
        try {
            MainPage = FXMLLoader.load(getClass().getResource("/gui/FXMLRecruteur.fxml"));
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
    private void goPoste(MouseEvent event) {
                try {
            MainPage = FXMLLoader.load(getClass().getResource("/gui/FXMLCandidat.fxml"));
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
    private void goPosteMenu(ActionEvent event) {
              try {
            MainPage = FXMLLoader.load(getClass().getResource("/gui/FXMLCandidat.fxml"));
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
    private void addComment(ActionEvent event) {
            try {
            MainPage = FXMLLoader.load(getClass().getResource("/gui/HomeCom.fxml"));
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
    private void addPublication(ActionEvent event) {
            try {
            MainPage = FXMLLoader.load(getClass().getResource("/gui/FrontEnd_Publication.fxml"));
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
    private void goReclamation(ActionEvent event) {
             try {
            MainPage = FXMLLoader.load(getClass().getResource("/gui/idriss/Reclamation.fxml"));
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
    private void Portfolio(ActionEvent event) {
        try {
            MainPage = FXMLLoader.load(getClass().getResource("/gui/haylem/myProfile.fxml"));
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


