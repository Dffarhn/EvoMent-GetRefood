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
import PageSeeProduct.seeproductController;
import Sceneopener.Openscene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
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
    private Button listdisbut;

    


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


     @FXML
    private TextField searchinput;

    AllBarang barangdatashow = new AllBarang();

    

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
        
        mainshow.getChildren().setAll(root);
        
    }
    @FXML
    private void ToMyReFood(ActionEvent event) throws Exception {

        Openscene os = new Openscene();

        Pane root = os.getPane("MyReFood/MyRefood");

        
        mainshow.getChildren().setAll(root);
        
    }
    @FXML
    private void ToMyaddProduct(ActionEvent event) throws Exception {

        Openscene os = new Openscene();

        Pane root = os.getPane("PageAddbarang/Addbarang");

        
        mainshow.getChildren().setAll(root);
        
    }
    @FXML
    private void ToMyProduct(ActionEvent event) throws Exception {

        Openscene os = new Openscene();

        Pane root = os.getPane("HomePage/myproduct");

        
        mainshow.getChildren().setAll(root);
        
    }
    @FXML
    private void ToDistribusi(ActionEvent event) throws Exception {

        Openscene os = new Openscene();

        Pane root = os.getPane("ListDistribusiPage/listdistribusi");

        
        mainshow.getChildren().setAll(root);
        
    }
    @FXML
    private void Logout(ActionEvent event) throws IOException {
   
        root = FXMLLoader.load(getClass().getClassLoader().getResource("Login/Scene1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        
    }
    @FXML
    private void ToBeranda(ActionEvent event) throws Exception {

        root = FXMLLoader.load(getClass().getClassLoader().getResource("HomePage/Homepage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
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
    private void ToSeeprod() throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("PageSeeProduct/seepro.fxml"));
        Parent root = loader.load();

        seeproductController controller = loader.getController();
        //dapetin button
        Button buyButton = controller.getButton();
        Button backbut = controller.getCancelbutform();

        // ngatur fungsi si button pada seepro.fxml melalui others controller
        buyButton.setOnAction(event ->{
            // buyButtonAction

            controller.topagepesan(event);
            buttonback.setVisible(false);
            // System.out.println("ehmasuk");
            
        });
        backbut.setOnAction(event ->{
            // buyButtonAction

            controller.cancelpagepesan(event);
            buttonback.setVisible(true);
            // System.out.println("ehmasuk");
            
        });


        //buat ngejadiin tampilan utama menjadi tampilan produk yang di pilih
        mainshowbot.getChildren().setAll(root);

        buttonback.setVisible(true);
        
    }

    //untuk nyimpen data setiap gridpane
    ArrayList<ArrayList<Barang>> datagridmost = new ArrayList<>();
    ArrayList<ArrayList<Barang>> datagridothers = new ArrayList<>();
    
    ArrayList<ArrayList<Barang>> datagridnew = new ArrayList<>();
    @FXML
    private void handleGridClick(MouseEvent event) throws IOException {
        int numCols = ((GridPane) event.getSource()).getColumnCount();
        int numRows = ((GridPane) event.getSource()).getRowCount();

        double cellWidth = ((GridPane) event.getSource()).getWidth() / numCols;
        double cellHeight = ((GridPane) event.getSource()).getHeight() / numRows;

        int clickedCol = (int) (event.getX() / cellWidth);
        int clickedRow = (int) (event.getY() / cellHeight);


        XStream xstream = new XStream(new StaxDriver());
        xstream.processAnnotations(Barang.class);
        xstream.processAnnotations(AllBarang.class);

        AllBarang datain = new AllBarang();
        datain.getRefoodBarang().add(datagridnew.get(clickedRow).get(clickedCol));
        




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
        // memunculkan seepro.fxml
      ToSeeprod();



    }


    @FXML
    private void handleGridClickmost(MouseEvent event) throws IOException {
        int numCols = ((GridPane) event.getSource()).getColumnCount();
        int numRows = ((GridPane) event.getSource()).getRowCount();

        double cellWidth = ((GridPane) event.getSource()).getWidth() / numCols;
        double cellHeight = ((GridPane) event.getSource()).getHeight() / numRows;

        int clickedCol = (int) (event.getX() / cellWidth);
        int clickedRow = (int) (event.getY() / cellHeight);


        XStream xstream = new XStream(new StaxDriver());
        xstream.processAnnotations(Barang.class);
        xstream.processAnnotations(AllBarang.class);


        AllBarang datain = new AllBarang();
        datain.getRefoodBarang().add(datagridmost.get(clickedRow).get(clickedCol));
        



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
    private void handleGridClickothers(MouseEvent event) throws IOException {
        int numCols = ((GridPane) event.getSource()).getColumnCount();
        int numRows = ((GridPane) event.getSource()).getRowCount();

        double cellWidth = ((GridPane) event.getSource()).getWidth() / numCols;
        double cellHeight = ((GridPane) event.getSource()).getHeight() / numRows;

        int clickedCol = (int) (event.getX() / cellWidth);
        int clickedRow = (int) (event.getY() / cellHeight);

        XStream xstream = new XStream(new StaxDriver());
        xstream.processAnnotations(Barang.class);
        xstream.processAnnotations(AllBarang.class);

        AllBarang datain = new AllBarang();
        datain.getRefoodBarang().add(datagridothers.get(clickedRow).get(clickedCol));
        

     


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

     

        
        

        
        
    }

    
    @FXML
    private Circle circlehomepage;
    
    @FXML
    private Label namehompage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateroleuser();
        
        updatebarang();




        if (roleuser.getRole().equals("Seller")) {


            butmyprod.setText("My-Product");
            // ActionEvent fakeEvent = new ActionEvent();
            butmyprod.setOnAction(event -> {
                try {
                    ToMyProduct(event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            Openscene os = new Openscene();

            Pane root = os.getPane("HomePage/berandaseller");

            
            mainshow.getChildren().setAll(root);

        
            
        }else{

            listdisbut.setVisible(false);

            addprodbut.setVisible(false);
            // untuk new grid
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
                    
                    
                    Label expire = (Label) item.lookup("#expiredatebarang");
                    expire.setText(expire.getText()+ barangdatashow.getRefoodBarang().get(i).getExpiredproduk());

                    ImageView imageView = (ImageView) item.lookup("#imageproduct");
                    Image image = new Image(getClass().getClassLoader().getResourceAsStream(barangdatashow.getRefoodBarang().get(i).getFotoproduk()));
                    imageView.setImage(image);
        
                    showbarangnew.add(item, coindexnew, roindexnew);


                    //add(item,0,0)
                    //add(item,1,0)
                    //add(item,2,0)
                    //add(item,4,0)
                
                    datagridnew.get(0).add(barangdatashow.getRefoodBarang().get(i));
                    
    
    
                    coindexnew++;
                    // coindex++;
        
                    
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("salah disini dapa");
            }
            //Ini untuk others grid
            try {
    
                HashSet<Integer> generatedNumbers = new LinkedHashSet<>();
                int min = 0;
                int max = barangdatashow.getRefoodBarang().size()-1;

                // harus di perbaiki ketika barang sudah mencapai angka lebih dari 30 barang yang ada pada getrefood
                // int jumlahonothers = 0;
    
                while (generatedNumbers.size() < barangdatashow.getRefoodBarang().size() && generatedNumbers.size() < 30) {
                    int randomNumber = (int) (Math.random() * (max - min + 1)) + min;
                    generatedNumbers.add(randomNumber);
                }
                int roindex = 0;
                int coindex = 0;
                datagridothers.add(new ArrayList<>());
    
                for (int i : generatedNumbers) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayShow\\DisplayBarang.fxml"));
                        AnchorPane item = loader.load();
    
                        // Set different text for each label
                        Label nameLabel = (Label) item.lookup("#nameLabel");
                        nameLabel.setText(barangdatashow.getRefoodBarang().get(i).getNamaproduk());
    
                        Label banyakPenjualanLabel = (Label) item.lookup("#banyakPenjualanLabel");
                        banyakPenjualanLabel.setText(banyakPenjualanLabel.getText()+ barangdatashow.getRefoodBarang().get(i).getStockproduk());
                        Label expire = (Label) item.lookup("#expiredatebarang");
                        expire.setText(expire.getText()+ barangdatashow.getRefoodBarang().get(i).getExpiredproduk());
                        
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
    
            
            // untuk the most stock available
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
                    banyakPenjualanLabel.setText(banyakPenjualanLabel.getText()+ barangdatashowurut.getRefoodBarang().get(i).getStockproduk());
                    
    
                    ImageView imageView = (ImageView) item.lookup("#imageproduct");
                    Image image = new Image(getClass().getClassLoader().getResourceAsStream(barangdatashowurut.getRefoodBarang().get(i).getFotoproduk()));
                    imageView.setImage(image);
                    Label expire = (Label) item.lookup("#expiredatebarang");
                    expire.setText(expire.getText()+ barangdatashowurut.getRefoodBarang().get(i).getExpiredproduk());
                    
                    showbarangpopular.add(item, coindexpopular, 0);
                    // datagridmost.get(0).add(barangdatashow.getRefoodBarang().get(i));
                    datagridmost.get(0).add(barangdatashowurut.getRefoodBarang().get(i));
    
                    coindexpopular++;
    
                    
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("salah disini dapa");
            }
        }

        namehompage.setText(roleuser.getNamaBadan());

        Image img = new Image("/MyProfile/userimage.png");
        System.out.println(barangdatashow.getRefoodBarang().size());

        // // src\MyProfile\backgroundup2.png
        // src\MyProfile\perusahaanpict.png


        circlehomepage.setFill(new ImagePattern(img));


        


        // defaultElements.addAll(mainshow.getChildren());

        // defaultElementsmainshowbot.addAll(mainshowbot.getChildren());

        
    }
}
