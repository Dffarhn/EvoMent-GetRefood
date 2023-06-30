package ListDistribusiPage;

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
import javafx.scene.layout.GridPane;
public class listdistribusicontroller implements Initializable {

       @FXML
    private GridPane listdistribusi;

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
    
                if (datatmp.getRefoodPesanan().get(i).getProduct().getOwner().getNamaBadan().equals(roleuser.getNamaBadan())&& datatmp.getRefoodPesanan().get(i).getProduct().getOwner().getNomorBadan().equals(roleuser.getNomorBadan()) ) {
    
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


    


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        updateroleuser();
        updatepesananuser();

        try {

                int roindexnew = 0;
                int coindexnew = 0;
                // datagridnew.add(new ArrayList<>());
        
                for (int i = 0 ; i < Pesananuser.getRefoodPesanan().size(); i++) {
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Listshow/listshow.fxml"));
                    AnchorPane item = loader.load();
        
                    // Set different text for each label
                    Label nameLabel = (Label) item.lookup("#namapembeli");
                    nameLabel.setText(Pesananuser.getRefoodPesanan().get(i).getPembeli().getNamaBadan());
        
                    Label banyakPenjualanLabel = (Label) item.lookup("#alamatpembeli");
                    banyakPenjualanLabel.setText(Pesananuser.getRefoodPesanan().get(i).getPembeli().getAlamatBadan());
                    // Label NamaPesanan = (Label) item.lookup("#NamaPesanan");
                    // NamaPesanan.setText(Pesananuser.getRefoodPesanan().get(i).getProduct().getNamaproduk());
                    // Label nomortoko = (Label) item.lookup("#nomortoko");
                    // nomortoko.setText(Pesananuser.getRefoodPesanan().get(i).getProduct().getOwner().getNomorBadan());
                    // Label alamattoko = (Label) item.lookup("#alamattoko");
                    // alamattoko.setText(Pesananuser.getRefoodPesanan().get(i).getProduct().getOwner().getAlamatBadan());
                    // Label jumlahpesanan = (Label) item.lookup("#jumlahpesanan");
                    // jumlahpesanan.setText("X "+Pesananuser.getRefoodPesanan().get(i).getJumlahpesanan());
                    Label statusorder = (Label) item.lookup("#stockyangdipesan");
                    statusorder.setText("Stock : " + Pesananuser.getRefoodPesanan().get(i).getJumlahpesanan());
    
                    ImageView imageView = (ImageView) item.lookup("#imageprod");
                    Image image = new Image(getClass().getClassLoader().getResourceAsStream(Pesananuser.getRefoodPesanan().get(i).getProduct().getFotoproduk()));
                    imageView.setImage(image);
        
                    // showbarangnew.add(item, coindexnew, roindexnew);
                    // datagridnew.get(0).add(barangdatashow.getRefoodBarang().get(i));
                    listdistribusi.add(item,coindexnew,roindexnew);
                    
                    coindexnew++;

                    if (coindexnew > 1) {
                            roindexnew++;
                            coindexnew = 0;
                    }
                    
    
    
        
                    
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("salah disini dapa");
            }





    }




}
