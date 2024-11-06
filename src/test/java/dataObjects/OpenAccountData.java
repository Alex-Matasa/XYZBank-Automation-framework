package dataObjects;

public class OpenAccountData extends BaseData{

    private String currency;


    public OpenAccountData(String filePath) {
        fromJsonToObject(filePath);
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }






}
