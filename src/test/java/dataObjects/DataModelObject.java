package dataObjects;

import lombok.Data;
import pageObjects.BasePage;

import java.util.List;

@Data
public class DataModelObject extends BaseData {

    public List<CustomerData> customers;

    public DataModelObject(String filePath) {
        fromJsonToObject(filePath);
        for (CustomerData customer : customers) {
            customer.manipulateData();
        }
    }

}
