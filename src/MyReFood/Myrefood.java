package MyReFood;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import Database.Account;
import Database.AllAccount;
import Database.AntrianPesanan.AllPesanan;
import Database.AntrianPesanan.Pesanan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
public class Myrefood implements Initializable {

    
    @FXML
    private GridPane vieworder;

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

    AllPesanan Pesananuser =new AllPesanan();
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
    
                if (datatmp.getRefoodPesanan().get(i).getPembeli().getNamaBadan().equals(roleuser.getNamaBadan())&& datatmp.getRefoodPesanan().get(i).getPembeli().getNomorBadan().equals(roleuser.getNomorBadan()) ) {
    
                    Pesananuser.getRefoodPesanan().add(datatmp.getRefoodPesanan().get(i));
                    
                }
                
            }
        } catch (Exception e) {
            // TODO: handle exception
        }


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

    @FXML
    private Label showarnzero;

    
    @FXML
    private AnchorPane gridadd;






    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateroleuser();
        updatepesananuser();

         try {
                int roindexnew = 0;
                int coindexnew = 0;
                // datagridnew.add(new ArrayList<>());
        
                for (int i = 0 ; i < Pesananuser.getRefoodPesanan().size(); i++) {
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Displaymyrefood\\Displayorder.fxml"));
                    AnchorPane item = loader.load();
        
                    // Set different text for each label
                    
                    // Label banyakPenjualanLabel = (Label) item.lookup("#banyakPenjualanLabel");
                    // banyakPenjualanLabel.setText(banyakPenjualanLabel.getText()+ barangdatashow.getRefoodBarang().get(i).getStockproduk());
                    Label NamaPesanan = (Label) item.lookup("#NamaPesanan");
                    NamaPesanan.setText(Pesananuser.getRefoodPesanan().get(i).getProduct().getNamaproduk());
                    Label nomortoko = (Label) item.lookup("#nomortoko");
                    nomortoko.setText(Pesananuser.getRefoodPesanan().get(i).getProduct().getOwner().getNomorBadan());
                    Label alamattoko = (Label) item.lookup("#alamattoko");
                    alamattoko.setText(Pesananuser.getRefoodPesanan().get(i).getProduct().getOwner().getAlamatBadan());
                    Label jumlahpesanan = (Label) item.lookup("#jumlahpesanan");
                    jumlahpesanan.setText("X "+Pesananuser.getRefoodPesanan().get(i).getJumlahpesanan());
                    Label statusorder = (Label) item.lookup("#statusorder");
                    statusorder.setText(Pesananuser.getRefoodPesanan().get(i).getInfopesanan());

                    Label nameLabel = (Label) item.lookup("#NamaToko");
                    if (Pesananuser.getRefoodPesanan().get(i).getInfopesanan().equals("APPROVED")) {
                        if (Pesananuser.getRefoodPesanan().get(i).getPengirimanpesanan().equals("pickup")) {
                            
                            nameLabel.setText(Pesananuser.getRefoodPesanan().get(i).getProduct().getOwner().getNamaBadan() + " > u can pickup at " + Pesananuser.getRefoodPesanan().get(i).getTanggalDistribusi() );
                        }else{

                            nameLabel.setText(Pesananuser.getRefoodPesanan().get(i).getProduct().getOwner().getNamaBadan() + " > will be order at " + Pesananuser.getRefoodPesanan().get(i).getTanggalDistribusi() );
                        }
                        
                    }else{
                        
                        nameLabel.setText(Pesananuser.getRefoodPesanan().get(i).getProduct().getOwner().getNamaBadan() + " >");
                    }
                    
                    ImageView imageView = (ImageView) item.lookup("#fotoproduk");
                    Image image = new Image(getClass().getClassLoader().getResourceAsStream(Pesananuser.getRefoodPesanan().get(i).getProduct().getFotoproduk()));
                    imageView.setImage(image);
        
                    // showbarangnew.add(item, coindexnew, roindexnew);
                    // datagridnew.get(0).add(barangdatashow.getRefoodBarang().get(i));
                    
                    if (i > 3) {
                        
                        // // vieworder.getHeight();
                        gridadd.setPrefHeight(gridadd.getPrefHeight()+320);
                        
                        // vieworder.setPrefHeight(vieworder.getHeight()+419);

                        vieworder.setMinHeight(vieworder.getMinHeight() + 380);
                        // vieworder.setPrefHeight(vieworder.getPrefHeight() + 200);
                        
                        // RowConstraints rowConstraints = new RowConstraints();
                        // rowConstraints.setPrefHeight(200); // Set the preferred height of the row
                        // vieworder.getRowConstraints().add(rowConstraints);

                    RowConstraints rowConstraints = new RowConstraints();
                    rowConstraints.setMinHeight(50.0); // Set the minimum height of the row
                    rowConstraints.setPrefHeight(50.0); // Set the preferred height of the row
                    rowConstraints.setVgrow(Priority.SOMETIMES); // Set the vertical grow property of the row
                    vieworder.getRowConstraints().add(rowConstraints);
                        
                    }
                    
                    vieworder.add(item,coindexnew,roindexnew);
    
    
                    roindexnew++;
                    // coindex++;
        
                    
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("salah disini dapa");
            }

            if (Pesananuser.getRefoodPesanan().size()==0) {
                showarnzero.setVisible(true);
                
            }else{
                showarnzero.setVisible(false);

            }
    }
}
