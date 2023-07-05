package Database.Barang;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import Database.Account;

@XStreamAlias("Barang")
public class Barang {


    private Account owner;

    private String namaproduk;
    private String deskripsiproduk;
    private String categoriproduk;
    private String expiredproduk;
    private String pengirimanproduk;
    private String fotoproduk;
    public Account getOwner() {
        return owner;
    }
    public void setOwner(Account owner) {
        this.owner = owner;
    }

    private String stockproduk;
    

    
    public String getStockproduk() {
        return stockproduk;
    }
    public void setStockproduk(String stockproduk) {
        this.stockproduk = stockproduk;
    }
    public String getNamaproduk() {
        return namaproduk;
    }
    public void setNamaproduk(String namaproduk) {
        this.namaproduk = namaproduk;
    }
    public String getExpiredproduk() {
        return expiredproduk;
    }
    public void setExpiredproduk(String expiredproduk) {
        this.expiredproduk = expiredproduk;
    }
    public String getPengirimanproduk() {
        return pengirimanproduk;
    }
    public void setPengirimanproduk(String pengirimanproduk) {
        this.pengirimanproduk = pengirimanproduk;
    }

    public String getCategoriproduk() {
        return categoriproduk;
    }
    public void setCategoriproduk(String categoriproduk) {
        this.categoriproduk = categoriproduk;
    }
    public String getFotoproduk() {
        return fotoproduk;
    }
    public void setFotoproduk(String fotoproduk) {
        String pathremove = removePrefix(fotoproduk);
        String path = replaceBackslashes(pathremove);
        this.fotoproduk = path;
    }
    public String getDeskripsiproduk() {
        return deskripsiproduk;
    }
    public void setDeskripsiproduk(String deskripsiproduk) {
        this.deskripsiproduk = deskripsiproduk;
    }

    public String replaceBackslashes(String input) {
        return input.replace("\\", "/");
    }

    public String removePrefix(String input) {
        String databasePrefix = "Database\\";
        int prefixIndex = input.indexOf(databasePrefix);

        if (prefixIndex != -1) {
            return input.substring(prefixIndex);
        }

        return input;
    }



    
}
