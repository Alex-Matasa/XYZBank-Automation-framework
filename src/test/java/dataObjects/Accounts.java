package dataObjects;


import lombok.Data;

import java.util.List;

@Data
public class Accounts {

    private String currency;
    private String accountId;
    private String balance;
    private List<Transactions> transactions;

}
