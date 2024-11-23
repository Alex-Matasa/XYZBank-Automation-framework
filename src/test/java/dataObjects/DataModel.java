package dataObjects;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DataModel extends BaseData {

    public List<CustomerData> customerData;
    public List<CustomerAccountData> customerAccountData;

    public DataModel(String filePath) {
        fromJsonToObject(filePath);
        for (CustomerData customer : customerData) {
            customer.manipulateData();
        }
    }

}
