/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.User;
import entities.categorie;
import entities.service;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.controlsfx.control.textfield.TextFields;
import services.UserService;
import services.categoriesmethodes;
import services.servicesmethodes;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ReservationFreelancerController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private TableView<categorie> tb;
    @FXML
    private TableColumn<categorie, Integer> id_service;
    @FXML
    private TableColumn<categorie, String> nom_categorie;
    @FXML
    private TextField zonerecherche;
    @FXML
    private TableView<service> tb2;
    @FXML
    private TableColumn<service, Integer> id_service2;
    @FXML
    private TableColumn<service, String> nom_service;
    int index = -1;
    @FXML
    private TextField zoneselect;
    @FXML
    private TextArea desc;
    @FXML
    private Button pdf;
    @FXML
    private TableView<User> tb1;
    @FXML
    private Text tx;
    @FXML
    private ImageView qrView;

    Connection connexion;
    Statement stm;
    @FXML
    private TableColumn<?, ?> id_freelancer;
    @FXML
    private TableColumn<?, ?> email_freelancer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            UserService sp = new UserService();
            List<User> freelancers = sp.showUser().stream().filter(user -> user.getRole().equals("freelancer")).collect(Collectors.toList());
            System.out.println(freelancers);
            servicesmethodes sm = new servicesmethodes();

            List<String> nomServiceList = new ArrayList<>();
            List<service> l = sm.afficherpersonne();
            for (service obj : l) {
                nomServiceList.add(obj.getNom_service());
            

            System.out.print(nomServiceList);

            TextFields.bindAutoCompletion(zonerecherche, nomServiceList);
            servicesmethodes sm1 = new servicesmethodes();
            List<service> l1 = sm1.afficherpersonne();
            ObservableList<service> categories = FXCollections.observableArrayList(l1);
            id_service2.setCellValueFactory(new PropertyValueFactory<service, Integer>("id_service"));
            nom_service.setCellValueFactory(new PropertyValueFactory<service, String>("nom_service"));
            tb2.setItems(categories);

            ObservableList<User> frs = FXCollections.observableArrayList(freelancers);
             id_freelancer.setCellValueFactory(new PropertyValueFactory<>("id"));
             email_freelancer.setCellValueFactory(new PropertyValueFactory<>("email"));
            tb1.setItems(frs);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void afficher(MouseEvent event) throws SQLException {
        categoriesmethodes cm = new categoriesmethodes();
        String s = zonerecherche.getText();
        List<categorie> l = cm.afficherpar(s);
        ObservableList<categorie> categories = FXCollections.observableArrayList(l);
        id_service.setCellValueFactory(new PropertyValueFactory<categorie, Integer>("id_service"));
        nom_categorie.setCellValueFactory(new PropertyValueFactory<categorie, String>("nom_categorie"));
        tb.setItems(categories);

    }

    @FXML
    private void GenererPDf(MouseEvent event) throws FileNotFoundException, DocumentException, IOException {
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\HAYLEM SAKHRAOUI\\Downloads\\Compressed\\PidevPersonne\\Pdfgenerer.pdf"));
        doc.open();
        String s1 = zonerecherche.getText();
        String s2 = zoneselect.getText();
        String s3 = desc.getText();
//        Image img = Image.getInstance("D:\\Program Files\\Java\\Projects\\PidevPersonne\\src\\gui\\images\\logo.jpg");
//
//        doc.add(img);
        doc.add(new Paragraph(s1));
        doc.add(new Paragraph("------------"));
        doc.add(new Paragraph(s2));
        doc.add(new Paragraph(s3));
        doc.close();
//        Desktop.getDesktop().open(new File("D:\\Program Files\\Java\\Projects\\PidevPersonne\\src\\Pdf"));
    }

    @FXML
    private void ReserverMail(MouseEvent event) {
        System.out.print("email en cours");
        final String username = "thameurboukhris8@gmail.com";
        final String password = "tskohxfhijikjcua";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("thameurboukhris8@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("thamer.boukhris@esprit.tn"));
            String s1 = zonerecherche.getText();
            String s2 = zoneselect.getText();
            String s3 = desc.getText();
            message.setSubject(s1);
            message.setText(s2);
            message.setText(s3);

            Transport.send(message);

            System.out.println("Email envoyé avec succès");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void get_selected_item(MouseEvent event) {
        index = tb.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        zoneselect.setText(String.valueOf(nom_categorie.getCellData(index)));
    }

    @FXML
    private void Qrcode(MouseEvent event) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String s1 = zonerecherche.getText();
        String s2 = zoneselect.getText();
        String s3 = desc.getText();
        String myWeb = s1 + "  " + s2 + "  " + s3;

        int width = 100;
        int height = 100;
        String fileType = "png";

        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            System.out.println("Success...");

        } catch (WriterException ex) {
            Logger.getLogger(JavaFX_QRCodeWriter.class.getName()).log(Level.SEVERE, null, ex);
        }

        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));

    }

    public List<User> getFreelancers() throws SQLException {

        List<User> freelancers = new ArrayList<>();
        String req = "select * from `user`  ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            User u = new User(rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("username"), rst.getString("email"),
                    rst.getString("password"), rst.getString("role"), rst.getString("remember_token"), rst.getString("created_at"), rst.getString("updated_at"));
            freelancers.add(u);
        }
        return freelancers;

    }

}
