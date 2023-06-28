package Database.Barang;

import java.util.ArrayList;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("BarangInfo")
public class AllBarang {
    
    @XStreamImplicit
    private ArrayList<Barang> allRefoodAccounts = new ArrayList<>();

    public ArrayList<Barang> getRefoodBarang() {
        return allRefoodAccounts;
    }

    public void setRefoodBarang(ArrayList<Barang> allRefoodAccounts) {
        this.allRefoodAccounts = allRefoodAccounts;
    }
}
