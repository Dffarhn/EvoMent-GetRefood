package Database.Barang;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class MakeBaseBarang {
    public static void main(String[] args) {
        XStream xstream = new XStream(new StaxDriver());
        
        
        xstream.processAnnotations(Barang.class);
        xstream.processAnnotations(AllBarang.class);

        AllBarang dataAll = new AllBarang();

        Barang test1 = new Barang();



        // test1.setEmail("d.raihan2004@gmail.com");
        // test1.setPassword("Raihannajwa123");
        // test1.setNamaBadan("Evo-Ment");
        // test1.setNomorBadan("082253503356");
        // test1.setRole("Admin");
        // test1.setAlamatBadan("Palagan");

        test1.setNamaproduk("BuahApel");
        test1.setDeskripsiproduk("Buah yang hampir segar dan lain-lain");
        test1.setCategoriproduk("Buah");
        test1.setExpiredproduk("27-09-2023");
        test1.setPengirimanproduk("JNE");
        test1.setFotoproduk("D:\\TUGASBESARFPA\\src\\Database\\Barang\\fotoProduct\\Apelll.png");
        test1.setStockproduk("20");
        // test1.setOwner();


        dataAll.getRefoodBarang().add(test1);

        


    
    
        String xml = xstream.toXML(dataAll);
        FileOutputStream myFile = null;
        try {
            myFile = new FileOutputStream("src\\Database\\Barang\\Barangbase.xml");
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

    
}
