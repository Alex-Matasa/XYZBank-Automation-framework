package dataObjects;


import lombok.Data;
import java.util.List;

@Data
public class Accounts {

    private String currency;
    private String accountId;
    private String balance = "0";
    private List<Transactions> transactions;

    public void addToBalance(String amount) {
        int currentBalance = parseStringToInt(this.balance);
        int addAmount = parseStringToInt(amount);
        this.balance = String.valueOf(currentBalance + addAmount);
    }

    public void subtractFromBalance(String amount) {
        int currentBalance = parseStringToInt(this.balance);
        int subtractAmount = parseStringToInt(amount);
        this.balance = String.valueOf(Math.max(0, currentBalance - subtractAmount)); // Prevent negative balance
    }

    private int parseStringToInt(String value) {
        return value != null ? Integer.parseInt(value) : 0;
    }

}
