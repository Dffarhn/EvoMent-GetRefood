package Login;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import Database.Account;
import Database.AllAccount;
import Database.PasswordUtils;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
public class Controller1 implements Initializable {

    @FXML
    private Stage stage;
    @FXML
    private Parent root;
    private Scene scene;

    @FXML
    private AnchorPane showinputregister, showinputlogin,signupas,signupseller,signupbuyer,justimage;


    @FXML
    private TextField loginemail;
    @FXML
    private TextField daftarsellernama,daftarselleralamat,daftarsellernomor,daftarselleremail,daftarsellerpass;

    @FXML
    private TextField daftarbuyernama,daftarbuyeralamat,daftarbuyernomor,daftarbuyeremail,daftarbuyerpass;

    String daftarrol = ""; 

    @FXML
    private PasswordField loginpass;


    @FXML
    private Label warnloginemail,warnloginpass;





    @FXML
    private ImageView imagebackground;

    @FXML
    private Button butlogin, butsignup;

    @FXML
    private ChoiceBox<String> roleuser;



    AllAccount dataAccount = new AllAccount();


    @FXML
    private void Chooselogin(ActionEvent event) {

        
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), showinputregister);
        transition.setByX(-425); // Set the relative translation along the X-axis // Set the relative translation along the Y-axis
        transition.play(); //

        TranslateTransition transition1 = new TranslateTransition(Duration.seconds(1), showinputlogin);
        transition1.setByX(-425); // Set the relative translation along the X-axis // Set the relative translation along the Y-axis
        transition1.play(); //
        TranslateTransition transition2 = new TranslateTransition(Duration.seconds(1), justimage);
        transition2.setByX(280); // Set the relative translation along the X-axis // Set the relative translation along the Y-axis
        transition2.play(); //


                // Delay duration in milliseconds
        int delayDuration = 500;

        // Create a PauseTransition with the desired delay duration
        PauseTransition pauseTransition = new PauseTransition(Duration.millis(delayDuration));

        // Set the action to be performed after the delay
        pauseTransition.setOnFinished(even -> {
            // Perform any desired action here
            showinputlogin.setVisible(true);
            showinputregister.setVisible(false);
        });

        // Start the PauseTransition
        pauseTransition.play();
        
    }


    @FXML
    private void Choosesignup(ActionEvent event) {;

        
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), showinputregister);
        transition.setByX(425); // Set the relative translation along the X-axis // Set the relative translation along the Y-axis
        transition.play(); //
        TranslateTransition transition1 = new TranslateTransition(Duration.seconds(1), showinputlogin);
        transition1.setByX(425); // Set the relative translation along the X-axis // Set the relative translation along the Y-axis
        transition1.play(); //

        TranslateTransition transition2 = new TranslateTransition(Duration.seconds(1), justimage);
        transition2.setByX(-280); // Set the relative translation along the X-axis // Set the relative translation along the Y-axis
        transition2.play(); //
        int delayDuration = 500;
    
        // Create a PauseTransition with the desired delay duration
        PauseTransition pauseTransition = new PauseTransition(Duration.millis(delayDuration));
    
        // Set the action to be performed after the delay
        pauseTransition.setOnFinished(even -> {
            // Perform any desired action here
            showinputlogin.setVisible(false);
            showinputregister.setVisible(true);
        });
    
        // Start the PauseTransition
        pauseTransition.play();
    }

    @FXML
    private void ChoosesignupSeller(ActionEvent event) {

        signupas.setVisible(false);
        signupseller.setVisible(true);
        daftarrol = "Seller";
        
    }

    @FXML
    private void ChoosesignupBuyer(ActionEvent event) {

        signupas.setVisible(false);
        signupbuyer.setVisible(true);
        daftarrol = "Buyer";

        
    }
    
    @FXML
    private void CancelsignupSeller(ActionEvent event) {

        signupas.setVisible(true);
        signupseller.setVisible(false);
        
    }
    @FXML
    private void Cancelsignupbuyer(ActionEvent event) {

        signupas.setVisible(true);
        signupbuyer.setVisible(false);
        
    }




    @FXML

    private void Tohomepage(ActionEvent event) throws IOException{
        
        for (int i = 0; i < dataAccount.getRefoodAccounts().size(); i++) {
            
            
            warnloginpass.setText("");
            warnloginemail.setText("");
            Account cekAccount = dataAccount.getRefoodAccounts().get(i);
            boolean isada = false;
            if (cekAccount.getEmail().equals(loginemail.getText()) ) {
                isada = true;

                System.out.println(loginpass.getText());
                
                
                if (hidepas.decryptPassword(cekAccount.getPassword()).equals(loginpass.getText())) {
                    
                    Currentuser(cekAccount);
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("HomePage/Homepage.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                    
                    
                }else{
                    

                    warnloginpass.setText("*Password anda salah");
                    break;
                }
                break;

                
            }else{
                
            }

            if (!isada) {
                warnloginemail.setText("*Maaf Akun tidak ditemukan");
                
            }
            
        }
       
        
    }



    @FXML
    private void update() {

        XStream xstream = new XStream(new StaxDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        xstream.processAnnotations(Account.class);
        xstream.processAnnotations(AllAccount.class);
        xstream.ignoreUnknownElements();
        FileInputStream getFile = null;
        String readXML = "";
        try {
            getFile = new FileInputStream("src\\Database\\Accountbase.xml");
            readXML = xmlToString(getFile);
        } catch (Exception e) {
            System.err.println("Perhatian:"  + e.getMessage());
        } finally {
            if (getFile != null) {
                try {
                    getFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        AllAccount datatmp = (AllAccount) xstream.fromXML(readXML);

        dataAccount = datatmp;
        

        
        
    }

    public static String xmlToString(FileInputStream xmlFile) {
       int isiFile;
       char myChar;
       String myString = "";
       try {
          while ((isiFile = xmlFile.read()) != -1) {
               myChar = (char) isiFile;
               myString = myString + myChar;
          }
       } catch (IOException e) {
          e.printStackTrace();
       }
       return myString;
    }


    @FXML
    private void xmlupdate() {

        XStream xstream = new XStream(new StaxDriver());
       xstream.processAnnotations(Account.class);
        xstream.processAnnotations(AllAccount.class);

     
        AllAccount datain = new AllAccount();
        System.out.println("Update xml jalan");

        datain = dataAccount;


    


        String xml = xstream.toXML(datain);
        FileOutputStream myFile = null;
        try {
            String folderPath = "src\\Database";
                String fileName = "Accountbase.xml";
                String filePath = folderPath + File.separator + fileName;
            myFile = new FileOutputStream(filePath);
            byte[] bytes = xml.getBytes("UTF-8");
            myFile.write(bytes);
        } catch (Exception e) {
            System.err.println("Perhatian: " + e.getMessage());
        } finally {
            if (myFile != null) {
                try {
                    myFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        
        
    }
    @FXML
    private void Currentuser(Account user) {

        XStream xstream = new XStream(new StaxDriver());
       xstream.processAnnotations(Account.class);
        xstream.processAnnotations(AllAccount.class);

        AllAccount datain = new AllAccount();
        // System.out.println("Update xml jalan");

        datain.getRefoodAccounts().add(user);


        String xml = xstream.toXML(datain);
        FileOutputStream myFile = null;
        try {
            String folderPath = "src\\Login";
                String fileName = "User.xml";
                String filePath = folderPath + File.separator + fileName;
            myFile = new FileOutputStream(filePath);
            byte[] bytes = xml.getBytes("UTF-8");
            myFile.write(bytes);
        } catch (Exception e) {
            System.err.println("Perhatian: " + e.getMessage());
        } finally {
            if (myFile != null) {
                try {
                    myFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        
        
    }

     private void showAlert(String massage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Sign Up");
   
        alert.setContentText(massage);

        alert.showAndWait();
    }
    PasswordUtils hidepas = new PasswordUtils();




    
    @FXML
    private Label showwarnbuyeremail;

    @FXML
    private Label showwarnselleremail;



    @FXML
    private void ToDaftarDatabaseSeller(ActionEvent event) {

        boolean bisadaftar = true;

        
        for (int i = 0; i < dataAccount.getRefoodAccounts().size(); i++) {
            
            if (dataAccount.getRefoodAccounts().get(i).getEmail().equals(daftarselleremail.getText())) {

                bisadaftar = false;
                break;

                
                
                
            }
            
        }
        
        if (bisadaftar) {
            
            showwarnselleremail.setVisible(false);
            Account tempAccount = new Account();
    
            tempAccount.setNamaBadan(daftarsellernama.getText());
            tempAccount.setAlamatBadan(daftarselleralamat.getText());
    
            tempAccount.setNomorBadan(daftarsellernomor.getText());
    
            tempAccount.setEmail(daftarselleremail.getText());
    
            tempAccount.setPassword(hidepas.encryptPassword(daftarsellerpass.getText()));
    
    
    
            tempAccount.setRole(daftarrol);
    
            // index 0 supaya database
            dataAccount.getRefoodAccounts().add(0,tempAccount);
            //menambahkan ke database
            xmlupdate();
    
            showAlert("Selamat anda telah terdaftar menjadi Supplier");
    
            TranslateTransition transition = new TranslateTransition(Duration.seconds(1), showinputregister);
            transition.setByX(-425); // Set the relative translation along the X-axis // Set the relative translation along the Y-axis
            transition.play(); //
    
            TranslateTransition transition1 = new TranslateTransition(Duration.seconds(1), showinputlogin);
            transition1.setByX(-425); // Set the relative translation along the X-axis // Set the relative translation along the Y-axis
            transition1.play();
    
            TranslateTransition transition2 = new TranslateTransition(Duration.seconds(1), justimage);
            transition2.setByX(280); // Set the relative translation along the X-axis // Set the relative translation along the Y-axis
            transition2.play(); //
    
    
            int delayDuration = 500;
    
            // Create a PauseTransition with the desired delay duration
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(delayDuration));
    
            // Set the action to be performed after the delay
            pauseTransition.setOnFinished(even -> {
    
                showinputlogin.setVisible(true);
                signupas.setVisible(true);
                signupseller.setVisible(false);
                showinputregister.setVisible(false);
    
            });
    
            // Start the PauseTransition
            pauseTransition.play();
    
            
    
    
            loginemail.setText("");
            loginpass.setText("");
        }else{
            System.out.println("belum bisa daftar");
            showwarnselleremail.setVisible(true);
        }
        
    }

    // ini adalah cara untuk buyer sign up
    @FXML
    private void ToDaftarDatabaseBuyer(ActionEvent event) {

        
        boolean bisadaftar = true;

        
        for (int i = 0; i < dataAccount.getRefoodAccounts().size(); i++) {
            
            if (dataAccount.getRefoodAccounts().get(i).getEmail().equals(daftarbuyeremail.getText())) {
                
                bisadaftar = false;
                break;
                
                
                
                
            }
            
        }
        
        if (bisadaftar) {
            showwarnbuyeremail.setVisible(false);
            
            Account tempAccount = new Account();
    
            tempAccount.setNamaBadan(daftarbuyernama.getText());
            tempAccount.setAlamatBadan(daftarbuyeralamat.getText());
    
            tempAccount.setNomorBadan(daftarbuyernomor.getText());
    
            tempAccount.setEmail(daftarbuyeremail.getText());
            tempAccount.setPassword(hidepas.encryptPassword(daftarbuyerpass.getText()));
    
            tempAccount.setRole(daftarrol);
    
    
            dataAccount.getRefoodAccounts().add(0,tempAccount);
    
            //buat menambhakn yang sign up ke database
            xmlupdate();
    
            showAlert("Selamat anda telah terdaftar menjadi Buyer");
    
            TranslateTransition transition = new TranslateTransition(Duration.seconds(1), showinputregister);
            transition.setByX(-425); // Set the relative translation along the X-axis // Set the relative translation along the Y-axis
            transition.play(); //
    
            TranslateTransition transition1 = new TranslateTransition(Duration.seconds(1), showinputlogin);
            transition1.setByX(-425); // Set the relative translation along the X-axis // Set the relative translation along the Y-axis
            transition1.play();
    
            TranslateTransition transition2 = new TranslateTransition(Duration.seconds(1), justimage);
            transition2.setByX(280); // Set the relative translation along the X-axis // Set the relative translation along the Y-axis
            transition2.play(); //
    
    
    
    
            int delayDuration = 500;
    
            // Create a PauseTransition with the desired delay duration
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(delayDuration));
    
            // Set the action to be performed after the delay
            pauseTransition.setOnFinished(even -> {
                // Perform any desired action here
                showinputlogin.setVisible(true);
                signupas.setVisible(true);
                signupbuyer.setVisible(false);
                showinputregister.setVisible(false);
                // showinputlogin.setVisible(true);
                // showinputregister.setVisible(false);
            });
    
            // Start the PauseTransition
            pauseTransition.play();
    
            loginemail.setText("");
            loginpass.setText("");
        }else{

            showwarnbuyeremail.setVisible(true);

        }


        
    }











    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        update();
    }
}