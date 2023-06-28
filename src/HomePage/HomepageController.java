package HomePage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import Database.Account;
import Database.AllAccount;
import Database.Barang.AllBarang;
import Database.Barang.Barang;
import Sceneopener.Openscene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class HomepageController implements Initializable {
     @FXML
    private Stage stage;
    @FXML
    private Parent root;
    private Scene scene;

     @FXML
    private Button buttonback;
     @FXML
    private Button butmyprod;
     @FXML
    private Button addprodbut;

    


    @FXML
    private AnchorPane mainshow;
    @FXML
    private AnchorPane mainshowbot;

    @FXML
    private GridPane showbarang;

    @FXML
    private GridPane showbarangnew;

    @FXML
    private GridPane showbarangpopular;

    private List<Node> defaultElements = new ArrayList<>();
    private List<Node> defaultElementsmainshowbot = new ArrayList<>();

     @FXML
    private TextField searchinput;

    AllBarang barangdatashow = new AllBarang();

    Pane nowshow = new Pane();

    String Search = "";


    @FXML
    private void Toback(ActionEvent event) throws IOException {

        buttonback.setVisible(false);

        

        root = FXMLLoader.load(getClass().getClassLoader().getResource("HomePage/Homepage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    
    @FXML
    private void ToSearch(ActionEvent event) throws Exception {

        Openscene os = new Openscene();

        buttonback.setVisible(true);

        
        
        XStream xstream = new XStream(new StaxDriver());
        String datain = searchinput.getText();

       




        String xml = xstream.toXML(datain);
        FileOutputStream myFile = null;
        try {
            String folderPath = "src\\HomePage";
                String fileName = "usersearch.xml";
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
        // // nowshow = root;

        Pane root = os.getPane("HomePage/searchview");
        try {
            mainshowbot.getChildren().setAll(root);
            
        } catch (Exception e) {
            // TODO: handle exception

            System.out.println("Salah disini dapa");
        }
        
    }

    @FXML
    private void ToMyProfile(ActionEvent event) throws Exception {

        Openscene os = new Openscene();

        Pane root = os.getPane("MyProfile/Myprofile");
        nowshow = root;
        mainshow.getChildren().setAll(root);
        
    }
    @FXML
    private void ToMyReFood(ActionEvent event) throws Exception {

        Openscene os = new Openscene();

        Pane root = os.getPane("MyReFood/MyRefood");

        nowshow = root;
        mainshow.getChildren().setAll(root);
        
    }
    @FXML
    private void ToMyaddProduct(ActionEvent event) throws Exception {

        Openscene os = new Openscene();

        Pane root = os.getPane("PageAddbarang/Addbarang");

        nowshow = root;
        mainshow.getChildren().setAll(root);
        
    }
    @FXML
    private void ToBeranda(ActionEvent event) throws Exception {
        try {

                    mainshow.getChildren().remove(nowshow);
                    
                    
            
                    mainshow.getChildren().addAll(defaultElements);


        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }


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

    @FXML
    private void ToSeeprod() {

        Openscene os = new Openscene();

        Pane root = os.getPane("PageSeeProduct/seepro");

        nowshow = root;
        mainshowbot.getChildren().setAll(root);

        buttonback.setVisible(true);
        
    }

    // int coindex = 0;
    // int roindex = 0;
    ArrayList<ArrayList<Barang>> datagridmost = new ArrayList<>();
    ArrayList<ArrayList<Barang>> datagridothers = new ArrayList<>();
    
    ArrayList<ArrayList<Barang>> datagridnew = new ArrayList<>();
    @FXML
    private void handleGridClick(MouseEvent event) {
        int numCols = ((GridPane) event.getSource()).getColumnCount();
        int numRows = ((GridPane) event.getSource()).getRowCount();

        double cellWidth = ((GridPane) event.getSource()).getWidth() / numCols;
        double cellHeight = ((GridPane) event.getSource()).getHeight() / numRows;

        int clickedCol = (int) (event.getX() / cellWidth);
        int clickedRow = (int) (event.getY() / cellHeight);


        XStream xstream = new XStream(new StaxDriver());
        xstream.processAnnotations(Barang.class);
        xstream.processAnnotations(AllBarang.class);

        // Datadiri data1 = new Datadiri("Daffa","Laki-Laki");
        // Datadiri data2 = new Datadiri("Najwa","Perempuan");
        // Datadiri data3 = new Datadiri("Widya","Perempuan");
        AllBarang datain = new AllBarang();
        datain.getRefoodBarang().add(datagridnew.get(clickedRow).get(clickedCol));
        

        // Datasum.getAlldata().add(data1);
        // Datasum.getAlldata().add(data2);
        // Datasum.getAlldata().add(data3);


        String xml = xstream.toXML(datain);
        FileOutputStream myFile = null;
        try {
            String folderPath = "src\\PageSeeProduct";
                String fileName = "seeproduct.xml";
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

      ToSeeprod();



    }


    @FXML
    private void handleGridClickmost(MouseEvent event) {
        int numCols = ((GridPane) event.getSource()).getColumnCount();
        int numRows = ((GridPane) event.getSource()).getRowCount();

        double cellWidth = ((GridPane) event.getSource()).getWidth() / numCols;
        double cellHeight = ((GridPane) event.getSource()).getHeight() / numRows;

        int clickedCol = (int) (event.getX() / cellWidth);
        int clickedRow = (int) (event.getY() / cellHeight);


        XStream xstream = new XStream(new StaxDriver());
        xstream.processAnnotations(Barang.class);
        xstream.processAnnotations(AllBarang.class);

        // Datadiri data1 = new Datadiri("Daffa","Laki-Laki");
        // Datadiri data2 = new Datadiri("Najwa","Perempuan");
        // Datadiri data3 = new Datadiri("Widya","Perempuan");
        AllBarang datain = new AllBarang();
        datain.getRefoodBarang().add(datagridmost.get(clickedRow).get(clickedCol));
        

        // Datasum.getAlldata().add(data1);
        // Datasum.getAlldata().add(data2);
        // Datasum.getAlldata().add(data3);


        String xml = xstream.toXML(datain);
        FileOutputStream myFile = null;
        try {
            String folderPath = "src\\PageSeeProduct";
                String fileName = "seeproduct.xml";
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

      ToSeeprod();



    }
    @FXML
    private void handleGridClickothers(MouseEvent event) {
        int numCols = ((GridPane) event.getSource()).getColumnCount();
        int numRows = ((GridPane) event.getSource()).getRowCount();

        double cellWidth = ((GridPane) event.getSource()).getWidth() / numCols;
        double cellHeight = ((GridPane) event.getSource()).getHeight() / numRows;

        int clickedCol = (int) (event.getX() / cellWidth);
        int clickedRow = (int) (event.getY() / cellHeight);

        XStream xstream = new XStream(new StaxDriver());
        xstream.processAnnotations(Barang.class);
        xstream.processAnnotations(AllBarang.class);

        // Datadiri data1 = new Datadiri("Daffa","Laki-Laki");
        // Datadiri data2 = new Datadiri("Najwa","Perempuan");
        // Datadiri data3 = new Datadiri("Widya","Perempuan");
        AllBarang datain = new AllBarang();
        datain.getRefoodBarang().add(datagridothers.get(clickedRow).get(clickedCol));
        

        // Datasum.getAlldata().add(data1);
        // Datasum.getAlldata().add(data2);
        // Datasum.getAlldata().add(data3);


        String xml = xstream.toXML(datain);
        FileOutputStream myFile = null;
        try {
            String folderPath = "src\\PageSeeProduct";
                String fileName = "seeproduct.xml";
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

      ToSeeprod();



    }

    
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateroleuser();
        
        
        
        updatebarang();

        // System.out.println(roleuser.getRole());


        if (roleuser.getRole().equals("Seller")) {
            // mainshow.setVisible(false);

            butmyprod.setText("My-Product");
            // ActionEvent fakeEvent = new ActionEvent();
            butmyprod.setOnAction(event -> {
                try {
                    ToMyaddProduct(event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            Openscene os = new Openscene();

            Pane root = os.getPane("HomePage/berandaseller");

            nowshow = root;
            mainshow.getChildren().setAll(root);

        
            
        }else{
            // System.out.println("ini harus buyer");

            addprodbut.setVisible(false);
            
            try {
                int roindexnew = 0;
                int coindexnew = 0;
                datagridnew.add(new ArrayList<>());
        
                for (int i = barangdatashow.getRefoodBarang().size()-1 ; i > barangdatashow.getRefoodBarang().size()-6; i--) {
                    
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
        
                    showbarangnew.add(item, coindexnew, roindexnew);
                    datagridnew.get(0).add(barangdatashow.getRefoodBarang().get(i));
                    
    
    
                    coindexnew++;
                    // coindex++;
        
                    
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("salah disini dapa");
            }
    
            try {
    
                HashSet<Integer> generatedNumbers = new LinkedHashSet<>();
                int min = 0;
                int max = barangdatashow.getRefoodBarang().size()-1;
    
                while (generatedNumbers.size() < barangdatashow.getRefoodBarang().size()) {
                    int randomNumber = (int) (Math.random() * (max - min + 1)) + min;
                    generatedNumbers.add(randomNumber);
                }
                int roindex = 0;
                int coindex = 0;
                datagridothers.add(new ArrayList<>());
                // datagridothers.add(new ArrayList<>());
                // datagridothers.add(new ArrayList<>());
                // datagridothers.add(new ArrayList<>());
                // datagridothers.add(new ArrayList<>());
    
                for (int i : generatedNumbers) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayShow\\DisplayBarang.fxml"));
                        AnchorPane item = loader.load();
    
                        // Set different text for each label
                        Label nameLabel = (Label) item.lookup("#nameLabel");
                        nameLabel.setText(barangdatashow.getRefoodBarang().get(i).getNamaproduk());
    
                        Label banyakPenjualanLabel = (Label) item.lookup("#banyakPenjualanLabel");
                        banyakPenjualanLabel.setText(banyakPenjualanLabel.getText()+ barangdatashow.getRefoodBarang().get(i).getStockproduk());
                        Label Penjual = (Label) item.lookup("#Penjual");
                        Penjual.setText(Penjual.getText()+ barangdatashow.getRefoodBarang().get(i).getOwner().getNamaBadan());
                        // Set the image
                        ImageView imageView = (ImageView) item.lookup("#imageproduct");
                        Image image = new Image(getClass().getClassLoader().getResourceAsStream(barangdatashow.getRefoodBarang().get(i).getFotoproduk()));
                        imageView.setImage(image);
    
                        showbarang.add(item, coindex, roindex);
    
                        datagridothers.get(roindex).add(barangdatashow.getRefoodBarang().get(i));
                        
                        coindex++;
    
                        if (coindex > 4) {
                            datagridothers.add(new ArrayList<>());
                            roindex++;
                            coindex = 0;
                        }
    
                }
    
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("salah disini dapa");
            }
    
            
            
            try {
                
                AllBarang barangdatashowurut = new AllBarang();
                barangdatashowurut = barangdatashow;
                Collections.sort(barangdatashowurut.getRefoodBarang(), (barang1, barang2) ->
                                    Integer.parseInt(barang2.getStockproduk()) - Integer.parseInt(barang1.getStockproduk())
                                );
                
                int coindexpopular = 0;
                datagridmost.add(new ArrayList<>());
    
                for (int i = 0; i < 5; i++) {
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayShow\\DisplayBarang.fxml"));
                    AnchorPane item = loader.load();
    
                    // Set different text for each label
                    Label nameLabel = (Label) item.lookup("#nameLabel");
                    nameLabel.setText(barangdatashowurut.getRefoodBarang().get(i).getNamaproduk());
    
                    Label banyakPenjualanLabel = (Label) item.lookup("#banyakPenjualanLabel");
                    banyakPenjualanLabel.setText(barangdatashowurut.getRefoodBarang().get(i).getStockproduk());
                    Label Penjual = (Label) item.lookup("#Penjual");
                    Penjual.setText(Penjual.getText()+ barangdatashow.getRefoodBarang().get(i).getOwner().getNamaBadan());
    
                    ImageView imageView = (ImageView) item.lookup("#imageproduct");
                    Image image = new Image(getClass().getClassLoader().getResourceAsStream(barangdatashow.getRefoodBarang().get(i).getFotoproduk()));
                    imageView.setImage(image);
    
                    showbarangpopular.add(item, coindexpopular, 0);
                    // datagridmost.get(0).add(barangdatashow.getRefoodBarang().get(i));
                    datagridmost.get(0).add(barangdatashow.getRefoodBarang().get(i));
    
                    coindexpopular++;
    
                    
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("salah disini dapa");
            }
        }
        


        defaultElements.addAll(mainshow.getChildren());

        defaultElementsmainshowbot.addAll(mainshowbot.getChildren());

        
    }
}
