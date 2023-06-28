package Database;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class MakeBaseAccount {
    public static void main(String[] args) {
        XStream xstream = new XStream(new StaxDriver());
        
        
        xstream.processAnnotations(Account.class);
        xstream.processAnnotations(AllAccount.class);

        AllAccount dataAll = new AllAccount();

        Account test1 = new Account();


        test1.setEmail("d.raihan2004@gmail.com");
        test1.setPassword("Raihannajwa123");
        test1.setNamaBadan("Evo-Ment");
        test1.setNomorBadan("082253503356");
        test1.setRole("Admin");
        test1.setAlamatBadan("Palagan");

        dataAll.getRefoodAccounts().add(test1);
        


    
    
        String xml = xstream.toXML(dataAll);
        FileOutputStream myFile = null;
        try {
            myFile = new FileOutputStream("src/Database/Accountbase.xml");
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
