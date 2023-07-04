package PageSeeProduct;

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
import Database.AntrianPesanan.AllPesanan;
import Database.AntrianPesanan.Pesanan;
import Database.Barang.AllBarang;
import Database.Barang.Barang;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class seeproductController implements Initializable {

      @FXML
    private Stage stage;
    @FXML
    private Parent root;
    private Scene scene;


    @FXML
    private Button buybutton;
    @FXML
    private Button cancelbutform;

    


    
    
      public Button getCancelbutform() {
        return cancelbutform;
    }

    @FXML
    private Label seepodpemilik;

    @FXML
    private Label seeprodalamat;

    @FXML
    private TextArea seeproddes;

    @FXML
    private ImageView seeprodimage;

    @FXML
    private Label seeprodname;
    @FXML
    private Label seeproddate;
    @FXML
    private Label seeprodstock;

    
    @FXML
    private ScrollPane pagedetailsproduct;

    // public ScrollPane getPagedetailsproduct() {
    //     return pagedetailsproduct;
    // }
    // public AnchorPane getPagepesan() {
    //     return pagepesan;
    // }

    @FXML
    private AnchorPane pagepesan;


    AllBarang seebarang = new AllBarang();


    @FXML
    public void topagepesan(ActionEvent event) {
        pagedetailsproduct.setVisible(false);
        pagepesan.setVisible(true);
        confirmpesanan();
        
    }
    @FXML
    public void cancelpagepesan(ActionEvent event) {
        pagedetailsproduct.setVisible(true);
        pagepesan.setVisible(false);
        
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
            getFile = new FileInputStream("src\\PageSeeProduct\\seeproduct.xml");
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
        seebarang = datatmp;
        

        
        
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
    private TextField userpembeli;
    
    @FXML
    private ChoiceBox<String> pilihdistribusi;
    
    
    
    
    @FXML
    private TextArea alamatpembeli;
    @FXML
    private TextArea alamatpenjual;
    
    @FXML
    private TextField jumlahpesanan;
    
    @FXML
    private Label maxstock;
    
    @FXML
    private TextField namaPenjual;
    
    @FXML
    private TextField nomorpenjual;


    Account roleuser =new Account();
     @FXML
    private void updateroleuser() {

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

        roleuser = datatmp.getRefoodAccounts().get(0);

        // try {
            
        //             for (int i = 0; i < datatmp.getAlldata().size(); i++) {
        //                 daftarData.add(datatmp.getAlldata().get(i));
                        
        //             }
            
        // } catch (Exception e) {
        //     // System.out.println("salah disini dapa");
        //     // TODO: handle exception
        // }
        // System.out.println(datatmp.getAlldata().size());

        
        

        
        
    }

    AllPesanan pesananlist = new AllPesanan();
    @FXML
    private void updatepesanan() {

        XStream xstream = new XStream(new StaxDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        xstream.processAnnotations(Pesanan.class);
        xstream.processAnnotations(AllPesanan.class);
        xstream.ignoreUnknownElements();
        FileInputStream getFile = null;
        String readXML = "";
        try {
            getFile = new FileInputStream("src\\Database\\AntrianPesanan\\PesananBase.xml");
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
        AllPesanan datatmp = (AllPesanan) xstream.fromXML(readXML);

        pesananlist = datatmp;
    }

    @FXML
    private void xmlpesanupdate() {

        XStream xstream = new XStream(new StaxDriver());
      xstream.processAnnotations(Pesanan.class);
        xstream.processAnnotations(AllPesanan.class);

        // Datadiri data1 = new Datadiri("Daffa","Laki-Laki");
        // Datadiri data2 = new Datadiri("Najwa","Perempuan");
        // Datadiri data3 = new Datadiri("Widya","Perempuan");
        AllPesanan datain = new AllPesanan();
        System.out.println("Update xml jalan");

        datain = pesananlist;


        // Datasum.getAlldata().add(data1);
        // Datasum.getAlldata().add(data2);
        // Datasum.getAlldata().add(data3);


        String xml = xstream.toXML(datain);
        FileOutputStream myFile = null;
        try {
            String folderPath = "src\\Database\\AntrianPesanan";
                String fileName = "PesananBase.xml";
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
    private void updatebarangkebase(String stockbaru) {

        for (int i = 0; i < barangdatashow.getRefoodBarang().size(); i++) {

            if (barangdatashow.getRefoodBarang().get(i).getFotoproduk().equals(seebarang.getRefoodBarang().get(0).getFotoproduk()) && barangdatashow.getRefoodBarang().get(i).getDeskripsiproduk().equals(seebarang.getRefoodBarang().get(0).getDeskripsiproduk()) ) {

                // Barang tmp = seebarang.getRefoodBarang().get(0);
                // barangdatashow.getRefoodBarang().get(i).se = tmp;
                barangdatashow.getRefoodBarang().get(i).setStockproduk(stockbaru);
                break;

                
            }
            
        }
        
    }


    @FXML
    private Label warnstock,warndistribusi;


    @FXML
    private void BikinPesanan(ActionEvent event) throws IOException {
        System.out.println("ini distribusi " + pilihdistribusi.getValue());

        if (Integer.valueOf(jumlahpesanan.getText()) <= Integer.valueOf(seebarang.getRefoodBarang().get(0).getStockproduk())&&!jumlahpesanan.getText().equals("") && Integer.valueOf(jumlahpesanan.getText()) >0)  {
            if (pilihdistribusi.getValue() != null) {
                
                Pesanan barupesan = new Pesanan();
        
                barupesan.setPembeli(roleuser);
                barupesan.setProduct(seebarang.getRefoodBarang().get(0));
                pesananlist.getRefoodPesanan().add(barupesan);
        
                int x = Integer.valueOf(seebarang.getRefoodBarang().get(0).getStockproduk()) - Integer.valueOf(jumlahpesanan.getText());
                // seebarang.getRefoodBarang().get(0).setStockproduk(String.valueOf(x));
        
                updatebarangkebase(String.valueOf(x));
    
                barupesan.setInfopesanan("Menunggu Konfirmasi");
                barupesan.setJumlahpesanan(jumlahpesanan.getText());
                barupesan.setPengirimanpesanan(pilihdistribusi.getValue());
                
        
                xmlpesanupdate();
                xmlupdate();
                warnstock.setVisible(false);
                warndistribusi.setVisible(false);
        
                root = FXMLLoader.load(getClass().getClassLoader().getResource("HomePage/Homepage.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }else{
                warndistribusi.setVisible(true);
                warnstock.setVisible(false);
            }
            
        }else{

           
                
            warnstock.setVisible(true);
    

        }


        
        
    }

    
    
    
    @FXML
    private void confirmpesanan() {

        userpembeli.setText(roleuser.getNamaBadan());
        alamatpembeli.setText(roleuser.getAlamatBadan());

        maxstock.setText("/"+seebarang.getRefoodBarang().get(0).getStockproduk());

        namaPenjual.setText(seebarang.getRefoodBarang().get(0).getOwner().getNamaBadan());

        nomorpenjual.setText(seebarang.getRefoodBarang().get(0).getOwner().getNomorBadan());


        alamatpenjual.setText(seebarang.getRefoodBarang().get(0).getOwner().getAlamatBadan());
        
    }

    AllBarang barangdatashow = new AllBarang();

    

    @FXML
    private void updatebarang() {

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
        barangdatashow = datatmp;
        

        
        
    }


    @FXML
    private void xmlupdate() {

        XStream xstream = new XStream(new StaxDriver());
       xstream.processAnnotations(Barang.class);
        xstream.processAnnotations(AllBarang.class);

       
        AllBarang datain = new AllBarang();

        datain = barangdatashow;




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

    public Button getButton(){
        return buybutton;
    }

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        update();
        updateroleuser();
        updatepesanan();
        updatebarang();
        seepodpemilik.setText(seebarang.getRefoodBarang().get(0).getOwner().getNamaBadan());
        seeprodalamat.setText(seeprodalamat.getText()+" "+seebarang.getRefoodBarang().get(0).getOwner().getAlamatBadan());

        seeproddes.setText(seebarang.getRefoodBarang().get(0).getDeskripsiproduk().replace("\n", "").replaceAll("\\s+", " ").trim());

        // System.out.println(seebarang.getRefoodBarang().get(0).getDeskripsiproduk().replace("\n", "").replaceAll("\\s+", " ").trim());
        // System.out.println(seebarang.getRefoodBarang().get(0).getDeskripsiproduk().trim());
        
        seeprodname.setText(seebarang.getRefoodBarang().get(0).getNamaproduk());
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(seebarang.getRefoodBarang().get(0).getFotoproduk()));
        seeprodimage.setImage(image);

        seeproddate.setText(seeproddate.getText()+" "+seebarang.getRefoodBarang().get(0).getExpiredproduk());

        seeprodstock.setText(seeprodstock.getText()+" "+seebarang.getRefoodBarang().get(0).getStockproduk());
    
        String []distribusi = seebarang.getRefoodBarang().get(0).getPengirimanproduk().split(",");
        for (String i : distribusi) {
            pilihdistribusi.getItems().add(i);
            
        }
    }
}
