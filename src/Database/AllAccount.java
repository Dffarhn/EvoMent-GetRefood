package Database;

import java.util.ArrayList;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("AccountInfo")
public class AllAccount {
    
    @XStreamImplicit
    private ArrayList<Account> allRefoodAccounts = new ArrayList<>();

    public ArrayList<Account> getRefoodAccounts() {
        return allRefoodAccounts;
    }

    public void setRefoodAccounts(ArrayList<Account> allRefoodAccounts) {
        this.allRefoodAccounts = allRefoodAccounts;
    }
}
