package dataObjects;

import lombok.Data;

import java.util.List;

@Data
public class DataModel extends BaseData {

    public List<Customers> customers;
    public List<Accounts> accounts;
    public List<Transactions> transactions;

    public DataModel(String filePath) {
        fromJsonToObject(filePath);
        for (Customers customer : customers) {
            customer.manipulateData();
        }
    }

    public void normalizeInputData(Customers customers) {
        customers.modifyData(customers);
    }

}
