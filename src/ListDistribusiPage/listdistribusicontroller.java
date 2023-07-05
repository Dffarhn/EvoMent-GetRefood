package ListDistribusiPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
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
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;
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

    AllPesanan UpdatePesananDataBase =new AllPesanan();
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
        UpdatePesananDataBase = datatmp;

        try {
            
            for (int i = 0; i < datatmp.getRefoodPesanan().size(); i++) {
    
                if (datatmp.getRefoodPesanan().get(i).getProduct().getOwner().getNamaBadan().equals(roleuser.getNamaBadan())&& datatmp.getRefoodPesanan().get(i).getProduct().getOwner().getNomorBadan().equals(roleuser.getNomorBadan()) && datatmp.getRefoodPesanan().get(i).getInfopesanan().equals("Menunggu Konfirmasi") ) {
    
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


     ArrayList<ArrayList<Pesanan>> datagridnew = new ArrayList<>();
    @FXML
    private void handleGridClick(MouseEvent event) {
    }

    @FXML
    private void xmlupdatebarangdatabase() {

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
    
    
    
    private void getupdatedistribusi(){
        XStream xstream = new XStream(new StaxDriver());
        xstream.processAnnotations(Pesanan.class);
        xstream.processAnnotations(AllPesanan.class);
    
        // Datadiri data1 = new Datadiri("Daffa","Laki-Laki");
        // Datadiri data2 = new Datadiri("Najwa","Perempuan");
        // Datadiri data3 = new Datadiri("Widya","Perempuan");
        AllPesanan datain = new AllPesanan();
        // datain.getRefoodPesanan().add(datagridnew.get(row).get(column));
        datain = UpdatePesananDataBase;
        
    
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
    private void buttondelete(ActionEvent event, DatePicker dp,Label warn, Button gone) {
        Button clickedButton = (Button) event.getSource();
        int columnIndex = GridPane.getColumnIndex(clickedButton.getParent());
        int rowIndex = GridPane.getRowIndex(clickedButton.getParent());
        System.out.println("Button clicked on grid: " + columnIndex + "," + rowIndex);

        warn.setText("REJECTED");

        gone.setVisible(false);

        // String selectedDate = dp.getValue().toString();
        // System.out.println("Selected date: " + selectedDate);

        dp.setDisable(true);

        // clickedButton.setStyle("-fx-background-color: #009900;");

        clickedButton.setStyle(" -fx-shape: 'M0 0 H150 V30 H0 Z';");

        //  double initialWidth = clickedButton.getWidth();



        Timeline timeline = new Timeline(
        new KeyFrame(Duration.millis(500),
        new KeyValue(clickedButton.prefWidthProperty(), 140)
   
        )
        );

        timeline.play();

        // warn.setVisible(true);

        Timeline timeline2 = new Timeline(
        new KeyFrame(Duration.millis(500), new KeyValue(warn.visibleProperty(), true)
        
        )
        );

        timeline2.play();

        datagridnew.get(rowIndex).get(columnIndex).setInfopesanan("REJECTED");

        updatetodatabasepesanan(datagridnew, rowIndex, columnIndex,false);

        getupdatedistribusi();




    }
    @FXML
    private void buttonprove(ActionEvent event, DatePicker dp,Label warn, Label showwarndate) {
        Button clickedButton = (Button) event.getSource();
        int columnIndex = GridPane.getColumnIndex(clickedButton.getParent());
        int rowIndex = GridPane.getRowIndex(clickedButton.getParent());
        System.out.println("Button clicked on grid: " + columnIndex + "," + rowIndex);
        boolean accep = true;

        try {
            
            String selectedDate = dp.getValue().toString();
            System.out.println("Selected date: " + selectedDate);
        } catch (Exception e) {
            System.out.println("masuk sini");
            accep = false;
            showwarndate.setVisible(true);
            // TODO: handle exception
        }

        if (accep) {
            showwarndate.setVisible(false);

            
            dp.setDisable(true);
    
            // clickedButton.setStyle("-fx-background-color: #009900;");
    
            clickedButton.setStyle(" -fx-shape: 'M0 0 H150 V30 H0 Z';");
    
             double initialWidth = clickedButton.getWidth();
    
    
    
            Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(500),
            new KeyValue(clickedButton.prefWidthProperty(), 140),
            new KeyValue(clickedButton.translateXProperty(), clickedButton.getTranslateX() - (140- initialWidth))
            // new KeyValue(clickedButton.styleProperty(), "-fx-shape: 'M0 0 H150 V30 H0 Z';")
       
            )
            );
    
            timeline.play();
    
            // warn.setVisible(true);
    
            Timeline timeline2 = new Timeline(
            new KeyFrame(Duration.millis(500), new KeyValue(warn.visibleProperty(), true)
            
            )
            );
    
            timeline2.play();
            
            datagridnew.get(rowIndex).get(columnIndex).setTanggalDistribusi(dp.getValue().format(DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("id"))));
            datagridnew.get(rowIndex).get(columnIndex).setInfopesanan("APPROVED");
    
            updatetodatabasepesanan(datagridnew, rowIndex, columnIndex,true);
    
            getupdatedistribusi();
        }





    }
    AllBarang barangdatabase = new AllBarang();

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
        barangdatabase = datatmp;
        

        
        
    }


    private void updatebarangrejected(ArrayList<ArrayList<Pesanan>> datachange,int row,int column){

        for (int i = 0; i < barangdatabase.getRefoodBarang().size(); i++) {
            if (barangdatabase.getRefoodBarang().get(i).getNamaproduk().equals(datachange.get(row).get(column).getProduct().getNamaproduk())) {
                if (barangdatabase.getRefoodBarang().get(i).getDeskripsiproduk().equals(datachange.get(row).get(column).getProduct().getDeskripsiproduk())) {
                    if (barangdatabase.getRefoodBarang().get(i).getExpiredproduk().equals(datachange.get(row).get(column).getProduct().getExpiredproduk())) {

                        System.out.println("ada");

                        barangdatabase.getRefoodBarang().get(i).setStockproduk(String.valueOf(Integer.valueOf(barangdatabase.getRefoodBarang().get(i).getStockproduk())+ Integer.valueOf(datachange.get(row).get(column).getJumlahpesanan())));
                        // System.out.println(barangdatabase.getRefoodBarang().get(i).getStockproduk());
                        
                        break;


                    }
                    
                }
                
            }
            
        }

    }


    private void updatetodatabasepesanan(ArrayList<ArrayList<Pesanan>> datachange,int row,int column, boolean todo){
        for (int i = 0; i < UpdatePesananDataBase.getRefoodPesanan().size(); i++) {
            if (UpdatePesananDataBase.getRefoodPesanan().get(i).getProduct().getNamaproduk().equals(datachange.get(row).get(column).getProduct().getNamaproduk())) {
                if (UpdatePesananDataBase.getRefoodPesanan().get(i).getPembeli().getNamaBadan().equals(datachange.get(row).get(column).getPembeli().getNamaBadan())) {
                    if (UpdatePesananDataBase.getRefoodPesanan().get(i).getProduct().getStockproduk().equals(datachange.get(row).get(column).getProduct().getStockproduk())) {

                        // UpdatePesananDataBase.getRefoodPesanan().get(i).setPembeli(datachange.get(row).get(column).getPembeli());
                        // UpdatePesananDataBase.getRefoodPesanan().get(i).setProduct(datachange.get(row).get(column).getProduct());

                        if (todo) {
                            UpdatePesananDataBase.getRefoodPesanan().get(i).setTanggalDistribusi(datachange.get(row).get(column).getTanggalDistribusi());                  
                            
                            UpdatePesananDataBase.getRefoodPesanan().get(i).setInfopesanan(datachange.get(row).get(column).getInfopesanan());
                        }else{
                            System.out.println("masuksini");
                            updatebarangrejected(datachange, row, column);
                            xmlupdatebarangdatabase();
                            UpdatePesananDataBase.getRefoodPesanan().get(i).setInfopesanan(datachange.get(row).get(column).getInfopesanan());
                            // UpdatePesananDataBase.getRefoodPesanan().remove(i);


                        }
                        
                        // UpdatePesananDataBase.getRefoodPesanan().get(i).setPembeli(datachange.get(row).get(column).getPembeli());
                        // UpdatePesananDataBase.getRefoodPesanan().get(i).setPembeli(datachange.get(row).get(column).getPembeli());

                    }
                    
                }
                
            }
            
        }
    }



    @FXML
    private Label warnisempty;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        updateroleuser();
        updatepesananuser();
        updatebarang();

        try {

                int roindexnew = 0;
                int coindexnew = 0;
                datagridnew.add(new ArrayList<>());
                
        
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
                    statusorder.setText("Jumlah Pesanan : " + Pesananuser.getRefoodPesanan().get(i).getJumlahpesanan());
    
                    ImageView imageView = (ImageView) item.lookup("#imageprod");
                    Image image = new Image(getClass().getClassLoader().getResourceAsStream(Pesananuser.getRefoodPesanan().get(i).getProduct().getFotoproduk()));
                    imageView.setImage(image);


                    // Add event handler to button
                    
                    
                    DatePicker datepik = (DatePicker) item.lookup("#distribusidate");
                    // datepik.getValue();


                    Label prove = (Label) item.lookup("#approvetext");
                    Label datefalse = (Label) item.lookup("#showdatefalse");

                    
                    Button button = (Button) item.lookup("#confirmbut");
                    button.setOnAction(event -> buttonprove(event,datepik,prove,datefalse));
                    Button button2 = (Button) item.lookup("#rejectbut");
                    button2.setOnAction(event -> buttondelete(event,datepik,prove, button));
                    // showbarangnew.add(item, coindexnew, roindexnew);
                    datagridnew.get(roindexnew).add(Pesananuser.getRefoodPesanan().get(i));
                    listdistribusi.add(item,coindexnew,roindexnew);
                    
                    coindexnew++;

                    if (coindexnew > 1) {

                            datagridnew.add(new ArrayList<>());
                            roindexnew++;
                            coindexnew = 0;
                    }
                    
    
    
        
                    
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("salah disini dapa");
            }

            if (Pesananuser.getRefoodPesanan().isEmpty()) {
                System.out.println("ga ada yang beli");

                warnisempty.setVisible(true);
                
            }else{
                warnisempty.setVisible(false);
            }





    }




}
