package MyProfile;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import Database.Account;
import Database.AllAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
public class MyprofileController implements Initializable {

    
    @FXML
    private Circle circmage;

    @FXML
    private TextArea showalamatprofile;

    @FXML
    private TextField showemailprofile;

    @FXML
    private TextField showephoneprofile;

    @FXML
    private TextField shownameprofile;

    @FXML
    private TextField showroleprofile;

    AllAccount datauser = new AllAccount();

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
            getFile = new FileInputStream("src\\Login\\User.xml");
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

        datauser = datatmp;
        System.out.println(datauser.getRefoodAccounts().get(0).getAlamatBadan());
        
        shownameprofile.setText(datauser.getRefoodAccounts().get(0).getNamaBadan());
        showemailprofile.setText(datauser.getRefoodAccounts().get(0).getEmail());

        showephoneprofile.setText(datauser.getRefoodAccounts().get(0).getNomorBadan());
        showalamatprofile.setText(datauser.getRefoodAccounts().get(0).getAlamatBadan());

        showroleprofile.setText(datauser.getRefoodAccounts().get(0).getRole());
        
       
      
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        update();

        Image img = new Image("/MyProfile/mif.jpg");


        circmage.setFill(new ImagePattern(img));
        
    }
}