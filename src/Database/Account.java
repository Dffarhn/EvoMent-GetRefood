package Database;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Account")
public class Account {
    
    private String email;
    private String password;
    
    private String NamaBadan;
    private String NomorBadan;
    private String alamatBadan;
    
        private String role ;
    

    public void setEmail(String email) {
        this.email = email;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public void setNamaBadan(String namaBadan) {
        NamaBadan = namaBadan;
    }


    public void setNomorBadan(String nomorBadan) {
        NomorBadan = nomorBadan;
    }


    public void setAlamatBadan(String alamatBadan) {
        this.alamatBadan = alamatBadan;
    }


    public void setRole(String role) {
        this.role = role;
    }



    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }


    public String getNamaBadan() {
        return NamaBadan;
    }


    public String getNomorBadan() {
        return NomorBadan;
    }


    public String getAlamatBadan() {
        return alamatBadan;
    }


    public String getRole() {
        return role;
    }


}
