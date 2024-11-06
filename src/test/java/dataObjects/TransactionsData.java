package dataObjects;

import java.util.List;

public class TransactionsData extends BaseData{

    private String depositAmount;
    private String withdrawAmount;
    private List<String> depositHistory;
    private List<String> withdrawHistory;

    public TransactionsData(String filePath) {
        fromJsonToObject(filePath);
    }

    public String getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(String depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(String withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public List<String> getDepositHistory() {
        return depositHistory;
    }

    public void setDepositHistory(List<String> depositHistory) {
        this.depositHistory = depositHistory;
    }

    public List<String> getWithdrawHistory() {
        return withdrawHistory;
    }

    public void setWithdrawHistory(List<String> withdrawHistory) {
        this.withdrawHistory = withdrawHistory;
    }
}
