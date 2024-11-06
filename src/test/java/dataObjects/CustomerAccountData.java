package dataObjects;

import java.util.List;

public class CustomerAccountData {

    private String fullName;
    private String customerId;
    private String accountId;
    private String balance;
    private String currency;
    private List<String> depositTransaction;
    private List<String> withdrawTransaction;




    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<String> getDepositTransaction() {
        return depositTransaction;
    }

    public void setDepositTransaction(List<String> depositTransaction) {
        this.depositTransaction = depositTransaction;
    }

    public List<String> getWithdrawTransaction() {
        return withdrawTransaction;
    }

    public void setWithdrawTransaction(List<String> withdrawTransaction) {
        this.withdrawTransaction = withdrawTransaction;
    }
}
