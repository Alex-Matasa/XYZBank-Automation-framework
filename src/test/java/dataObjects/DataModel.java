package dataObjects;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DataModel extends BaseData {

    public List<CustomerData> customerData = new ArrayList<>();
    public InputAccountData inputAccountData;
    public List<InputCustomerData> inputCustomerData = new ArrayList<>();

    public DataModel(String filePath) {
        fromJsonToObject(filePath);
//        for (CustomerData customer : customerData) {
//            customer.manipulateData();
//        }
    }

}
