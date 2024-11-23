package dataObjects;

import lombok.Data;

import java.util.List;

@Data
public class DataModel extends BaseData {

    public List<CustomerData> customerData;
    public InputAccountData inputAccountData;
    public InputCustomerData inputCustomerData;

    public DataModel(String filePath) {
        fromJsonToObject(filePath);
//        for (CustomerData customer : customerData) {
//            customer.manipulateData();
//        }
    }

}
