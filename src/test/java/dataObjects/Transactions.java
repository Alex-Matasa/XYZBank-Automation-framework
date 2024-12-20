package dataObjects;

import lombok.Data;

import java.util.List;

@Data
public class Transactions {

    private String type;
    private String amount;
    private List<String> depositHistory;
    private List<String> withdrawHistory;

}
