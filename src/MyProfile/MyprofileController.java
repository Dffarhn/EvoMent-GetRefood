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
import Database.AntrianPesanan.AllPesanan;
import Database.AntrianPesanan.Pesanan;
import Database.Barang.AllBarang;
import Database.Barang.Barang;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
public class MyprofileController implements Initializable {
    XYChart.Series<String,Integer> chart1 = new XYChart.Series<String,Integer>();
    XYChart.Series<String,Integer> chart2 = new XYChart.Series<String,Integer>();
    XYChart.Series<String,Integer> chart3 = new XYChart.Series<String,Integer>();
    XYChart.Series<String,Integer> chart4 = new XYChart.Series<String,Integer>();

    @FXML
    private BarChart aktifchart;

    
    @FXML
    private Circle circmage;

    @FXML
    private TextArea showalamatprofile;

    @FXML
    private TextField showemailprofile;

    @FXML
    private TextField showephoneprofile;

    @FXML
    private Label shownameprofile;

    @FXML
    private Label showroleprofile;

    AllAccount datauser = new AllAccount();

    private void datamasukkegraph(int summakanan, int summinuman, int sumsayur, int sumbuah){
        chart1.getData().add(new XYChart.Data<String,Integer>("Makanan",summakanan));
        chart2.getData().add(new XYChart.Data<String,Integer>("Minuman",summinuman));
        chart3.getData().add(new XYChart.Data<String,Integer>("Sayur",sumsayur));
        chart4.getData().add(new XYChart.Data<String,Integer>("Buah",sumbuah));
        chart1.setName("Makanan");
        chart2.setName("Minuman");
        chart3.setName("Sayur");
        chart4.setName("Buah");


        aktifchart.getData().add(chart1);
        aktifchart.getData().add(chart2);
        aktifchart.getData().add(chart3);
        aktifchart.getData().add(chart4);
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

    AllPesanan productifbuyer =new AllPesanan();
     @FXML
    private void updatepesananuser() {

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

        // roleuser = datatmp.getRefoodAccounts().get(0);

        try {
            
            for (int i = 0; i < datatmp.getRefoodPesanan().size(); i++) {
    
                if (datatmp.getRefoodPesanan().get(i).getPembeli().getNamaBadan().equals(datauser.getRefoodAccounts().get(0).getNamaBadan() )&& datatmp.getRefoodPesanan().get(i).getPembeli().getNomorBadan().equals(datauser.getRefoodAccounts().get(0).getNomorBadan() )) {
    
                    productifbuyer.getRefoodPesanan().add(datatmp.getRefoodPesanan().get(i));
                    
                }
                
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }

    AllBarang productifseller = new AllBarang();
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
        // updatetodatabase = datatmp;

        for (int i = 0; i <datatmp.getRefoodBarang().size(); i++) {
            if (datatmp.getRefoodBarang().get(i).getOwner().getNamaBadan().equals(datauser.getRefoodAccounts().get(0).getNamaBadan())&&datatmp.getRefoodBarang().get(i).getOwner().getNomorBadan().equals(datauser.getRefoodAccounts().get(0).getNomorBadan())) {
                productifseller.getRefoodBarang().add(datatmp.getRefoodBarang().get(i));
            }
            
        }
        
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
        updatebarang();
        updatepesananuser();

        if (datauser.getRefoodAccounts().get(0).getRole().equals("Buyer")) {
            int makanansum = 0;
            int minumansum = 0;
            int sayursum = 0;
            int buahsum = 0;

            for (int i = 0; i < productifbuyer.getRefoodPesanan().size(); i++) {

                System.out.println(productifbuyer.getRefoodPesanan().get(i).getProduct().getCategoriproduk());

                switch (productifbuyer.getRefoodPesanan().get(i).getProduct().getCategoriproduk()) {
                    case "Makanan":
                        makanansum ++;
                        
                        break;
                
                    case "Minuman":
                    minumansum ++;
                        
                        break;
                
                    case "Sayur":
                    sayursum ++;
                        
                        break;
                
                    case "Buah":
                    buahsum++;
                        
                        break;
                
                    default:
                        System.out.println("gakebaca");
                        break;
                }
                
            }



            datamasukkegraph(makanansum,minumansum,sayursum,buahsum);
            
        }else if(datauser.getRefoodAccounts().get(0).getRole().equals("Seller")){

            int makanansum = 0;
            int minumansum = 0;
            int sayursum = 0;
            int buahsum = 0;

            for (int i = 0; i < productifseller.getRefoodBarang().size(); i++) {

                System.out.println(productifseller.getRefoodBarang().get(i).getCategoriproduk());

                switch (productifseller.getRefoodBarang().get(i).getCategoriproduk()) {
                    case "Makanan":
                        makanansum ++;
                        
                        break;
                
                    case "Minuman":
                    minumansum ++;
                        
                        break;
                
                    case "Sayur":
                    sayursum ++;
                        
                        break;
                
                    case "Buah":
                    buahsum++;
                        
                        break;
                
                    default:
                        System.out.println("gakebaca");
                        break;
                }
                
            }



            datamasukkegraph(makanansum,minumansum,sayursum,buahsum);

        }

        Image img = new Image("/MyProfile/userimage.png");

        // // src\MyProfile\backgroundup2.png
        // src\MyProfile\perusahaanpict.png


        circmage.setFill(new ImagePattern(img));
        
    }
}