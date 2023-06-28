package Database.AntrianPesanan;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import Database.Account;
import Database.Barang.Barang;

@XStreamAlias("Pesanan")

public class Pesanan {

    // private Account penjual;
    private Account pembeli;

    private Barang product;

    private String jumlahpesanan;
    private String pengirimanpesanan;
    private String infopesanan;

    // public Account getPenjual() {
    //     return penjual;
    // }

    // public void setPenjual(Account penjual) {
    //     this.penjual = penjual;
    // }

    public Account getPembeli() {
        return pembeli;
    }

    public String getJumlahpesanan() {
        return jumlahpesanan;
    }

    public void setJumlahpesanan(String jumlahpesanan) {
        this.jumlahpesanan = jumlahpesanan;
    }

    public String getPengirimanpesanan() {
        return pengirimanpesanan;
    }

    public void setPengirimanpesanan(String pengirimanpesanan) {
        this.pengirimanpesanan = pengirimanpesanan;
    }

    public String getInfopesanan() {
        return infopesanan;
    }

    public void setInfopesanan(String infopesanan) {
        this.infopesanan = infopesanan;
    }

    public void setPembeli(Account pembeli) {
        this.pembeli = pembeli;
    }

    public Barang getProduct() {
        return product;
    }

    public void setProduct(Barang product) {
        this.product = product;
    }
    
}
