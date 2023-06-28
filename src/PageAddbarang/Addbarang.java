package PageAddbarang;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import Database.Account;
import Database.AllAccount;
import Database.Barang.AllBarang;
import Database.Barang.Barang;
import HomePage.HomepageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
public class Addbarang implements Initializable {
     @FXML
    private Stage stage;
    @FXML
    private Parent root;
    private Scene scene;

      @FXML
    private CheckBox cekjne;

    @FXML
    private CheckBox cekjnt;

    @FXML
    private CheckBox cekpickup;

     @FXML
    private ChoiceBox<String> categorieschoice;

    @FXML
    private DatePicker dateproduct;

    @FXML
    private TextArea deskripsiproduk;

    @FXML
    private TextField kuantitasbarang;

    @FXML
    private TextField namaproduct;

    @FXML
    private TextField pathpict;

    @FXML
    private ImageView previewpict;

    AllBarang barangdatabase = new AllBarang();
    Account pemilik = new Account();

    
    @FXML
    private void TambahProduct(ActionEvent event) throws IOException{
        
        Barang product = new Barang();
        product.setNamaproduk(namaproduct.getText());
        product.setDeskripsiproduk(deskripsiproduk.getText());
        product.setStockproduk(kuantitasbarang.getText());

        product.setCategoriproduk(categorieschoice.getValue().toString());

        product.setExpiredproduk(dateproduct.getValue().toString());
        product.setOwner(pemilik);

        product.setFotoproduk(pathpict.getText());
        String pengirimanlist = "";


        if (cekjne.isSelected()) {
            pengirimanlist += "jne,";
        }
        if (cekjnt.isSelected()) {
            pengirimanlist += "jnt,";
        }
        if (cekpickup.isSelected()) {
            pengirimanlist += "pickup,";
        }

        product.setPengirimanproduk(pengirimanlist);


        barangdatabase.getRefoodBarang().add(product);
        xmlupdate();

        root = FXMLLoader.load(getClass().getClassLoader().getResource("HomePage/Homepage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        // System.out.println(pathpict.getText());
        
        
    }

    @FXML
    private void handleChooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        fileChooser.setInitialDirectory(new File("src\\Database\\Barang\\fotoProduct"));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            previewpict.setImage(image);
            pathpict.setText(selectedFile.getPath());
        }
    }






    @FXML
    private void update() {

        XStream xstream = new XStream(new StaxDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        xstream.processAnnotations(Barang.class);
        xstream.processAnnotations(AllBarang.class);
        xstream.ignoreUnknownElements();
        FileInputStream getFile = null;
        String readXML = "";
        try {
            getFile = new FileInputStream("src\\Database\\Barang\\Barangbase.xml");
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
        AllBarang datatmp = (AllBarang) xstream.fromXML(readXML);

        // try {
            
        //             for (int i = 0; i < datatmp.getAlldata().size(); i++) {
        //                 daftarData.add(datatmp.getAlldata().get(i));
                        
        //             }
            
        // } catch (Exception e) {
        //     // System.out.println("salah disini dapa");
        //     // TODO: handle exception
        // }
        // System.out.println(datatmp.getAlldata().size());

        barangdatabase = datatmp;
        

        
        
    }


    @FXML
    private void updateowner() {

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

       pemilik = datatmp.getRefoodAccounts().get(0);
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
       xstream.processAnnotations(Barang.class);
        xstream.processAnnotations(AllBarang.class);

        // Datadiri data1 = new Datadiri("Daffa","Laki-Laki");
        // Datadiri data2 = new Datadiri("Najwa","Perempuan");
        // Datadiri data3 = new Datadiri("Widya","Perempuan");
        AllBarang datain = new AllBarang();
        System.out.println("Update xml jalan");

        datain = barangdatabase;


        // Datasum.getAlldata().add(data1);
        // Datasum.getAlldata().add(data2);
        // Datasum.getAlldata().add(data3);


        String xml = xstream.toXML(datain);
        FileOutputStream myFile = null;
        try {
            String folderPath = "src\\Database\\Barang";
                String fileName = "Barangbase.xml";
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

    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        update();
        updateowner();
        categorieschoice.getItems().addAll("Buah","Sayur","Makanan","Minuman");
    }
}
