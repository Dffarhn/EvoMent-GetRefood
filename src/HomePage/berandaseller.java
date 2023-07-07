package HomePage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import Database.Barang.AllBarang;
import Database.Barang.Barang;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
public class berandaseller implements Initializable {

    ObservableList<PieChart.Data> datain =  FXCollections.observableArrayList(

    );

    ObservableList<Barang> data = FXCollections.observableArrayList(
        
    ) ;


        @FXML
    private TableColumn<Barang, String> dateproducttabel;

    @FXML
    private TableColumn<Barang, String> nameproducttabel;

    @FXML
    private TableColumn<Barang, String> ownerproducttabel;


    @FXML
    private TableView<Barang> showtabelproduct;

    @FXML
    private GridPane postedproduct;

    
    @FXML
    private PieChart allbarangdata;

    AllBarang barangdatashow = new AllBarang();

    int sumamakanan = 0;
    int sumaminuman = 0;
    int sumsayur = 0;
    int sumbuah = 0;


    @FXML
    private void hitungjenis(ActionEvent event){

        for (int i = 0; i < barangdatashow.getRefoodBarang().size(); i++) {

            if (barangdatashow.getRefoodBarang().get(i).getCategoriproduk().equals("Makanan")) {
                sumamakanan++;
            }else if(barangdatashow.getRefoodBarang().get(i).getCategoriproduk().equals("Minuman")){
                sumaminuman++;

            }else if(barangdatashow.getRefoodBarang().get(i).getCategoriproduk().equals("Buah")){
                sumbuah++;

            }else{
                sumsayur++;

            }
            
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
    // double hasil = sumamakanan/barangdatashow.getRefoodBarang().size() * 100;

    private void Showintabel(String cari) {
        data.clear();

        // int jumlah = 0;

        for (int i = 0; i < barangdatashow.getRefoodBarang().size(); i++) {
            if (barangdatashow.getRefoodBarang().get(i).getCategoriproduk().equals(cari)) {
                data.add(barangdatashow.getRefoodBarang().get(i));
                // jumlah ++;
                
            }

            // if (jumlah == 10) {
            //     break;
                
            // }
            
        }


        nameproducttabel.setCellValueFactory(new PropertyValueFactory<Barang,String>("namaproduk"));
        // ownerproducttabel.setCellValueFactory(new PropertyValueFactory<Barang,String>("NamaBadan"));
        ownerproducttabel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOwner().getNamaBadan()));
        dateproducttabel.setCellValueFactory(new PropertyValueFactory<Barang,String>("expiredproduk"));

        showtabelproduct.setItems(data);
        
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updatebarang();
        hitungjenis(null);

        datain.add(new PieChart.Data("Makanan " + String.valueOf(sumamakanan),sumamakanan));
        datain.add(new PieChart.Data("Minuman " + String.valueOf(sumaminuman),sumaminuman));
        datain.add(new PieChart.Data("Sayur " + String.valueOf(sumsayur),sumsayur));
        datain.add(new PieChart.Data("Buah " + String.valueOf(sumbuah),sumbuah));

        allbarangdata.setData(datain);

         try {
                int roindexnew = 0;
                int coindexnew = 0;
                // datagridnew.add(new ArrayList<>());
        
                for (int i = barangdatashow.getRefoodBarang().size()-1 ; i > barangdatashow.getRefoodBarang().size()-11; i--) {
                    
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
        
                    postedproduct.add(item, coindexnew, roindexnew);
                    // datagridnew.get(0).add(barangdatashow.getRefoodBarang().get(i));
                    
    
    
                    coindexnew++;
                    if (coindexnew > 1) {
                        coindexnew = 0;
                        roindexnew++;
                        
                    }
                    // coindex++;
        
                    
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("salah disini dapa");
            }

            for(PieChart.Data data : allbarangdata.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    // data.setPieValue(data.getPieValue()+1);
                    // Showchoose.setText("Anda memilih " + data.getName());

                    // showtabelproduct.getItems().removeAll();

                
                    

                    String [] pilih = data.getName().split(" ");

                    // System.out.println(pilih[0]);

                    Showintabel(pilih[0]);
                }
                
            });
        }

        // System.out.println(sumamakanan);
        // System.out.println(sumsayur);
        // System.out.println(sumbuah);
        // System.out.println(sumaminuman);
        // System.out.println(barangdatashow.getRefoodBarang().size());
    }
}