package wp.proekt.ebank.data;


import lombok.Getter;
import org.springframework.stereotype.Component;
import wp.proekt.ebank.model.*;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {

    public static List<Customer> customers=new ArrayList<>();
    public static List<Account> accounts=new ArrayList<>();
    public static List<Deposit> deposits=new ArrayList<>();
    public static List<Withdraw> withdraws=new ArrayList<>();
    public static List<Transfer> transfers=new ArrayList<>();
    public static List<Transaction> transactions=new ArrayList<>();

}
