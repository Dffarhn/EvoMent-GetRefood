package Database.AntrianPesanan;

/**
 * MakebasePesanan
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import Database.Account;
import Database.Barang.Barang;

public class MakebasePesanan {
    public static void main(String[] args) {
        XStream xstream = new XStream(new StaxDriver());
        
        
        xstream.processAnnotations(Pesanan.class);
        xstream.processAnnotations(AllPesanan.class);

        AllPesanan dataAll = new AllPesanan();

        Account test1 = new Account();
        Account test2 = new Account();

        Barang test3 = new Barang();


        Pesanan datapesan = new Pesanan();
        


        test1.setEmail("d.raihan2004@gmail.com");
        test1.setPassword("Raihannajwa123");
        test1.setNamaBadan("Evo-Ment");
        test1.setNomorBadan("082253503356");
        test1.setRole("Buyer");
        test1.setAlamatBadan("Palagan");



        test2.setEmail("adiva@gmail.com");
        test2.setPassword("adiva123");
        test2.setNamaBadan("ASDOS");
        test2.setNomorBadan("082253507890");
        test1.setRole("Seller");
        test1.setAlamatBadan("uii");

        test3.setNamaproduk("Es Cendol Garut");
        test3.setOwner(test2);

        test3.setDeskripsiproduk("es cendol yang di olah dengan beragam komposisi yang sangat menyehatkan\r\n" + //
                "badan");

        test3.setCategoriproduk("Minuman");

        test3.setExpiredproduk("2023-08-22");

        test3.setFotoproduk("Database/Barang/fotoProduct/escendol.png");
        test3.setPengirimanproduk("jne,jnt,");
        test3.setStockproduk("45");


        // <namaproduk>Es Cendol Garut</namaproduk>
        // <deskripsiproduk>es cendol yang di olah dengan beragam komposisi yang sangat menyehatkan
        //     badan</deskripsiproduk>
        // <categoriproduk>Minuman</categoriproduk>
        // <expiredproduk>2023-08-22</expiredproduk>
        // <pengirimanproduk>jne,jnt,</pengirimanproduk>
        // <fotoproduk>Database/Barang/fotoProduct/escendol.png</fotoproduk>
        // <stockproduk>45</stockproduk>

        // <email>adiva@gmail.com</email>
        // <password>adiva123</password>
        // <NamaBadan>ASDOS</NamaBadan>
        // <NomorBadan>082253507890</NomorBadan>
        // <alamatBadan>uii</alamatBadan>
        // <role>Seller</role>
        // dataAll.getRefoodPesanan().get(0).setPembeli(test1);
        // // dataAll.getRefoodPesanan().get(0).setPenjual(test2);
        // dataAll.getRefoodPesanan().get(0).setProduct(test3);

        datapesan.setPembeli(test1);
        datapesan.setProduct(test3);

        dataAll.getRefoodPesanan().add(datapesan);
        


    
    
        String xml = xstream.toXML(dataAll);
        FileOutputStream myFile = null;
        try {
            myFile = new FileOutputStream("src\\Database\\AntrianPesanan/PesananBase.xml");
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