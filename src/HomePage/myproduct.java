package HomePage;

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
import Database.AntrianPesanan.Pesanan;
import Database.Barang.AllBarang;
import Database.Barang.Barang;
import PageAddbarang.Addbarang;
import Sceneopener.Openscene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.ChoiceBoxSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class myproduct implements Initializable {

      @FXML
    private Stage stage;
    @FXML
    private Parent root;
    private Scene scene;

    @FXML 
    private AnchorPane mainmyproduct;

    @FXML
    private GridPane showmyproduct;

    
    AllBarang updatetodatabase = new AllBarang();

    
    Account SellerAccount =new Account();
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

        SellerAccount = datatmp.getRefoodAccounts().get(0);

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
        updatetodatabase = datatmp;

        for (int i = 0; i <datatmp.getRefoodBarang().size(); i++) {
            if (datatmp.getRefoodBarang().get(i).getOwner().getNamaBadan().equals(SellerAccount.getNamaBadan())&&datatmp.getRefoodBarang().get(i).getOwner().getNomorBadan().equals(SellerAccount.getNomorBadan())) {
                barangdatashow.getRefoodBarang().add(datatmp.getRefoodBarang().get(i));
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

    @FXML
    private void xmlupdate() {

        XStream xstream = new XStream(new StaxDriver());
       xstream.processAnnotations(Barang.class);
        xstream.processAnnotations(AllBarang.class);

       
        AllBarang datain = new AllBarang();

        datain = updatetodatabase;




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


    ArrayList<ArrayList<Barang>> datagridmyproduct = new ArrayList<>();
    @FXML
    private void handleGridClick(MouseEvent event) throws IOException {
        int numCols = ((GridPane) event.getSource()).getColumnCount();
        int numRows = ((GridPane) event.getSource()).getRowCount();

        double cellWidth = ((GridPane) event.getSource()).getWidth() / numCols;
        double cellHeight = ((GridPane) event.getSource()).getHeight() / numRows;

        int clickedCol = (int) (event.getX() / cellWidth);
        int clickedRow = (int) (event.getY() / cellHeight);

        System.out.println(datagridmyproduct.get(clickedRow).get(clickedCol).getNamaproduk());

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("PageAddbarang/Addbarang.fxml"));
        AnchorPane item = loader.load();

        TextField editnameproduct = (TextField) item.lookup("#namaproduct");
        editnameproduct.setText(datagridmyproduct.get(clickedRow).get(clickedCol).getNamaproduk());
        editnameproduct.setDisable(true);
        TextField editstockproduct = (TextField) item.lookup("#kuantitasbarang");
        editstockproduct.setText(datagridmyproduct.get(clickedRow).get(clickedCol).getStockproduk());
        TextField editpathpictproduct = (TextField) item.lookup("#pathpict");
        editpathpictproduct.setText(datagridmyproduct.get(clickedRow).get(clickedCol).getFotoproduk());
        TextArea editdeskripsiproduct = (TextArea) item.lookup("#deskripsiproduk");
        editdeskripsiproduct.setText(datagridmyproduct.get(clickedRow).get(clickedCol).getDeskripsiproduk());
        ImageView editImageproduct = (ImageView) item.lookup("#previewpict");
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(datagridmyproduct.get(clickedRow).get(clickedCol).getFotoproduk()));
        editImageproduct.setImage(image);
        ChoiceBox<String> editcategoriproduct = (ChoiceBox<String>) item.lookup("#categorieschoice");
        editcategoriproduct.setValue(datagridmyproduct.get(clickedRow).get(clickedCol).getCategoriproduk());

        DatePicker date = (DatePicker) item.lookup("#dateproduct");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("id"));
        formatter = formatter.withLocale(new Locale("id", "ID"));
        LocalDate datechange = LocalDate.parse(datagridmyproduct.get(clickedRow).get(clickedCol).getExpiredproduk(), formatter);

        date.setValue(datechange);

        String[] listkirim = datagridmyproduct.get(clickedRow).get(clickedCol).getPengirimanproduk().split(",");

        CheckBox cekjne = (CheckBox) item.lookup("#cekjne");
        CheckBox cekjnt = (CheckBox) item.lookup("#cekjnt");
        CheckBox cekpickup = (CheckBox) item.lookup("#cekpickup");

        for (int i = 0; i < listkirim.length; i++) {
            if (listkirim[i].equals("jne")) {
                cekjne.setSelected(true);
                
            }
            if(listkirim[i].equals("jnt")){
                cekjnt.setSelected(true);

            }
            if(listkirim[i].equals("pickup")){
                cekpickup.setSelected(true);
            }
            
        }

    //       @FXML
    // private CheckBox cekjne;

    // @FXML
    // private CheckBox cekjnt;

    // @FXML
    // private CheckBox cekpickup;


        Button backbuton = (Button) item.lookup("#backbut");
        Button deletebutton = (Button) item.lookup("#deleteprodukbut");
        Button updatebutton = (Button) item.lookup("#updateprodukbut");


        backbuton.setVisible(true);
        backbuton.setOnAction(even -> backfunc());
        deletebutton.setVisible(true);
        deletebutton.setOnAction(even -> deletefunc(even, datagridmyproduct, clickedRow, clickedCol));
        updatebutton.setVisible(true);


        updatebutton.setOnAction(even -> {
            
            
            Barang productupdate = new Barang();
            productupdate.setNamaproduk(editnameproduct.getText());
            productupdate.setDeskripsiproduk(editdeskripsiproduct.getText());
            productupdate.setStockproduk(editstockproduct.getText());

            productupdate.setCategoriproduk(editcategoriproduct.getValue().toString());

            productupdate.setExpiredproduk(date.getValue().format(DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("id"))));
            productupdate.setOwner(SellerAccount);

            productupdate.setFotoproduk(editpathpictproduct.getText());
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

            productupdate.setPengirimanproduk(pengirimanlist);

            for (int i = 0; i < updatetodatabase.getRefoodBarang().size(); i++) {

                Barang databaseinfo = updatetodatabase.getRefoodBarang().get(i);

                System.out.println(databaseinfo.getNamaproduk());
                System.out.println(productupdate.getNamaproduk());

                if (databaseinfo.getNamaproduk().equals(productupdate.getNamaproduk()) && databaseinfo.getOwner().getNamaBadan().equals(productupdate.getOwner().getNamaBadan()) ) {
                    System.out.println("masuk ni");
                    
                    
                    
                    updatetodatabase.getRefoodBarang().set(i,productupdate);
                    break;
                    
                    
                    
                }
                
            }
            xmlupdate();
            backfunc();
        });
        Button addbuton = (Button) item.lookup("#addproductbut");
        addbuton.setVisible(false);
        
        Label label = (Label) item.lookup("#labelshow");
        label.setText("Make Ur Product Intresting");
        

        mainmyproduct.getChildren().setAll(item);
    }


    private void deletefunc(ActionEvent event, ArrayList<ArrayList<Barang>> datachange, int row, int column) {

        Barang newupdatebarang = datachange.get(row).get(column);

        for (int i = 0; i < updatetodatabase.getRefoodBarang().size(); i++) {

            Barang databaseinfo = updatetodatabase.getRefoodBarang().get(i);

            if (databaseinfo.getNamaproduk().equals(newupdatebarang.getNamaproduk()) && databaseinfo.getOwner().equals(newupdatebarang.getOwner()) ) {
                if (databaseinfo.getDeskripsiproduk().equals(newupdatebarang.getDeskripsiproduk())) {

                    updatetodatabase.getRefoodBarang().remove(i);
                    
                }
                
            }
            
        }

        xmlupdate();


        backfunc();


        
    }
    // private void Updatefunc(ActionEvent event, ArrayList<ArrayList<Barang>> datachange, int row, int column) throws IOException {

    //     Barang newupdatebarang = datachange.get(row).get(column);

    //     for (int i = 0; i < updatetodatabase.getRefoodBarang().size(); i++) {

    //         Barang databaseinfo = updatetodatabase.getRefoodBarang().get(i);

    //         if (databaseinfo.getNamaproduk().equals(newupdatebarang.getNamaproduk()) && databaseinfo.getOwner().equals(newupdatebarang.getOwner()) ) {
    //             if (databaseinfo.getDeskripsiproduk().equals(newupdatebarang.getDeskripsiproduk())) {

    //                 updatetodatabase.getRefoodBarang().remove(i);
                    
    //             }
                
    //         }
            
    //     }

    //     xmlupdate();

    //     root = FXMLLoader.load(getClass().getClassLoader().getResource("HomePage/Homepage.fxml"));
    //     stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    //     scene = new Scene(root);
    //     stage.setScene(scene);
    //     stage.show();

    //     // backfunc();


        
    // }


    


    private void backfunc() {

        Openscene os = new Openscene();

        Pane root = os.getPane("HomePage/myproduct");

        mainmyproduct.getChildren().setAll(root);

        // buttonback.setVisible(true);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateroleuser();
        updatebarang();

        try {
                int roindexnew = 0;
                int coindexnew = 0;
                datagridmyproduct.add(new ArrayList<>());
        
                for (int i = 0 ; i < barangdatashow.getRefoodBarang().size(); i++) {
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayShow\\DisplayBarang.fxml"));
                    AnchorPane item = loader.load();
        
                    // Set different text for each label
                    Label nameLabel = (Label) item.lookup("#nameLabel");
                    nameLabel.setText(barangdatashow.getRefoodBarang().get(i).getNamaproduk());
                    
                    Label banyakPenjualanLabel = (Label) item.lookup("#banyakPenjualanLabel");
                    banyakPenjualanLabel.setText(banyakPenjualanLabel.getText()+ barangdatashow.getRefoodBarang().get(i).getStockproduk());
                    Label Penjual = (Label) item.lookup("#Penjual");
                    Penjual.setText(Penjual.getText()+ barangdatashow.getRefoodBarang().get(i).getOwner().getNamaBadan());
    
                    ImageView imageView = (ImageView) item.lookup("#imageproduct");
                    Image image = new Image(getClass().getClassLoader().getResourceAsStream(barangdatashow.getRefoodBarang().get(i).getFotoproduk()));
                    imageView.setImage(image);
        
                    showmyproduct.add(item, coindexnew, roindexnew);
                    datagridmyproduct.get(roindexnew).add(barangdatashow.getRefoodBarang().get(i));
                    
    
    
                    coindexnew++;
                    if (coindexnew > 3) {
                        datagridmyproduct.add(new ArrayList<>());

                        coindexnew = 0;
                        roindexnew++;
                        
                    }
                    // coindex++;
        
                    
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("salah disini dapa");
            }
    }
}
