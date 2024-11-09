package dataObjects;


import lombok.Data;

import java.util.List;

@Data
public class AccountData {

    private String currency;
    private String accountId;
    private String balance;


    private List<TransactionsData> transactions;

}
