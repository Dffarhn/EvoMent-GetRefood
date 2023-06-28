package Database.AntrianPesanan;

import java.util.ArrayList;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("PesananInfo")
public class AllPesanan {
    
    @XStreamImplicit
    private ArrayList<Pesanan> allRefoodPesanan = new ArrayList<>();

    public ArrayList<Pesanan> getRefoodPesanan() {
        return allRefoodPesanan;
    }

    public void setRefoodPesanan(ArrayList<Pesanan> allRefoodPesanan) {
        this.allRefoodPesanan = allRefoodPesanan;
    }
}
