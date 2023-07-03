package HomePage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import Database.Barang.AllBarang;
import Database.Barang.Barang;
import Sceneopener.Openscene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
public class search implements Initializable {

    @FXML
    private GridPane gridsearch;
    @FXML
    private AnchorPane mainshowsearch;
    @FXML
    private Label showwarnzero;

    AllBarang barangdatashow = new AllBarang();

    String search = "";

    
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

        System.out.println(search);

        for (int i = 0; i < datatmp.getRefoodBarang().size(); i++) {

            if (datatmp.getRefoodBarang().get(i).getNamaproduk().toLowerCase().contains(search.toLowerCase())) {

                barangdatashow.getRefoodBarang().add(datatmp.getRefoodBarang().get(i));
                
            }
            
        }
        

        
        
    }

    @FXML
    private void updatesearch() {

        XStream xstream = new XStream(new StaxDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        xstream.ignoreUnknownElements();
        FileInputStream getFile = null;
        String readXML = "";
        try {
            getFile = new FileInputStream("src/HomePage/usersearch.xml");
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
        String datatmp = (String) xstream.fromXML(readXML);
        search = datatmp;


        

        
        
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


     
    ArrayList<ArrayList<Barang>> datagridnew = new ArrayList<>();
    @FXML
    private void handleGridClick(MouseEvent event) {
        int numCols = ((GridPane) event.getSource()).getColumnCount();
        int numRows = ((GridPane) event.getSource()).getRowCount();

        double cellWidth = ((GridPane) event.getSource()).getWidth() / numCols;
        double cellHeight = ((GridPane) event.getSource()).getHeight() / numRows;

        int clickedCol = (int) (event.getX() / cellWidth);
        int clickedRow = (int) (event.getY() / cellHeight);

        // System.out.println("Clicked on grid: Row=" + clickedRow + ", Column=" + clickedCol);
        System.out.println(datagridnew.get(clickedRow).get(clickedCol).getOwner().getNamaBadan());

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

        System.out.println(datain.getRefoodBarang().get(0).getNamaproduk());


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
        // mainshowsearch.setVisible(false);


    }

    @FXML
    private void ToSeeprod() {

        Openscene os = new Openscene();

        Pane root = os.getPane("PageSeeProduct/seepro");

        // nowshow = root;
        mainshowsearch.getChildren().setAll(root);
        
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updatesearch();
        update();
        System.out.println(barangdatashow.getRefoodBarang().size());

        try {
            int roindexnew = 0;
            int coindexnew = 0;
            datagridnew.add(new ArrayList<>());
            datagridnew.add(new ArrayList<>());
            datagridnew.add(new ArrayList<>());
            datagridnew.add(new ArrayList<>());
            datagridnew.add(new ArrayList<>());
            datagridnew.add(new ArrayList<>());
    
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
                System.out.println(barangdatashow.getRefoodBarang().get(i).getFotoproduk());
                imageView.setImage(image);

                System.out.println("in berhasil");
    
                gridsearch.add(item, coindexnew, roindexnew);

                datagridnew.get(roindexnew).add(barangdatashow.getRefoodBarang().get(i));
                
                
                
                coindexnew++;
                if (coindexnew > 3) {
                       // datagridothers.add(new ArrayList<>());
                       roindexnew++;
                       coindexnew = 0;
                   }
                // coindex++;
    
                
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("salah disini dapa");
        }

        if (barangdatashow.getRefoodBarang().size()== 0) {
            showwarnzero.setVisible(true);

            
        }else{
            showwarnzero.setVisible(false);

        }


    }
}