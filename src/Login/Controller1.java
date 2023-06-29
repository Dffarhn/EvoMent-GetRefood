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
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        transition.setByX(-440); // Set the relative translation along the X-axis // Set the relative translation along the Y-axis
        transition.play(); //

        TranslateTransition transition1 = new TranslateTransition(Duration.seconds(1), showinputlogin);
        transition1.setByX(-440); // Set the relative translation along the X-axis // Set the relative translation along the Y-axis
        transition1.play(); //
        TranslateTransition transition2 = new TranslateTransition(Duration.seconds(1), justimage);
        transition2.setByX(360); // Set the relative translation along the X-axis // Set the relative translation along the Y-axis
        transition2.play(); //
        showinputlogin.setVisible(true);
        showinputregister.setVisible(false);
        
    }


    @FXML
    private void Choosesignup(ActionEvent event) {;

        
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), showinputregister);
        transition.setByX(440); // Set the relative translation along the X-axis // Set the relative translation along the Y-axis
        transition.play(); //
        TranslateTransition transition1 = new TranslateTransition(Duration.seconds(1), showinputlogin);
        transition1.setByX(440); // Set the relative translation along the X-axis // Set the relative translation along the Y-axis
        transition1.play(); //

        TranslateTransition transition2 = new TranslateTransition(Duration.seconds(1), justimage);
        transition2.setByX(-360); // Set the relative translation along the X-axis // Set the relative translation along the Y-axis
        transition2.play(); //
        showinputlogin.setVisible(false);
        showinputregister.setVisible(true);
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
                System.out.println("iyanih ada");
                isada = true;

                System.out.println(loginpass.getText());
                
                
                if (cekAccount.getPassword().equals(loginpass.getText())) {
                    
                    Currentuser(cekAccount);
                    // System.out.println("nyampe password ada");
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("HomePage/Homepage.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                    
                    
                }else{
                    // System.out.println("Salah nih");
                    

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

        // try {
            
        //             for (int i = 0; i < datatmp.getAlldata().size(); i++) {
        //                 daftarData.add(datatmp.getAlldata().get(i));
                        
        //             }
            
        // } catch (Exception e) {
        //     // System.out.println("salah disini dapa");
        //     // TODO: handle exception
        // }
        // System.out.println(datatmp.getAlldata().size());

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

        // Datadiri data1 = new Datadiri("Daffa","Laki-Laki");
        // Datadiri data2 = new Datadiri("Najwa","Perempuan");
        // Datadiri data3 = new Datadiri("Widya","Perempuan");
        AllAccount datain = new AllAccount();
        System.out.println("Update xml jalan");

        datain = dataAccount;


        // Datasum.getAlldata().add(data1);
        // Datasum.getAlldata().add(data2);
        // Datasum.getAlldata().add(data3);


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

        // Datadiri data1 = new Datadiri("Daffa","Laki-Laki");
        // Datadiri data2 = new Datadiri("Najwa","Perempuan");
        // Datadiri data3 = new Datadiri("Widya","Perempuan");
        AllAccount datain = new AllAccount();
        System.out.println("Update xml jalan");

        datain.getRefoodAccounts().add(user);

        // Datasum.getAlldata().add(data1);
        // Datasum.getAlldata().add(data2);
        // Datasum.getAlldata().add(data3);


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









    @FXML
    private void ToDaftarDatabaseSeller(ActionEvent event) {

        Account tempAccount = new Account();

        tempAccount.setNamaBadan(daftarsellernama.getText());
        tempAccount.setAlamatBadan(daftarselleralamat.getText());

        tempAccount.setNomorBadan(daftarsellernomor.getText());

        tempAccount.setEmail(daftarselleremail.getText());
        tempAccount.setPassword(daftarsellerpass.getText());

        tempAccount.setRole(daftarrol);


        dataAccount.getRefoodAccounts().add(0,tempAccount);
        xmlupdate();

        showinputlogin.setVisible(true);
        showinputregister.setVisible(false);

        loginemail.setText("");
        loginpass.setText("");
        
    }

    
    @FXML
    private void ToDaftarDatabaseBuyer(ActionEvent event) {

        Account tempAccount = new Account();

        tempAccount.setNamaBadan(daftarbuyernama.getText());
        tempAccount.setAlamatBadan(daftarbuyeralamat.getText());

        tempAccount.setNomorBadan(daftarbuyernomor.getText());

        tempAccount.setEmail(daftarbuyeremail.getText());
        tempAccount.setPassword(daftarbuyerpass.getText());

        tempAccount.setRole(daftarrol);


        dataAccount.getRefoodAccounts().add(0,tempAccount);
        xmlupdate();

        showinputlogin.setVisible(true);
        showinputregister.setVisible(false);
        loginemail.setText("");
        loginpass.setText("");
        
    }











    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // roleuser.getItems().addAll("Penjual","Pembeli");
        // butlogin.setStyle("-fx-opacity : 1");
        update();
    }
}