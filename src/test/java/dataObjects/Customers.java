package dataObjects;

import helperMethods.UtilityMethods;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Customers {

    private String firstName;
    private String lastName;
    private String postCode;
    private String fullName;
    private String customerId;
    private List<Accounts> accounts;

    public Customers() {
        this.accounts = new ArrayList<>();
    }

    public void manipulateData() {
            this.fullName = firstName + " " + lastName;
    }

    public void modifyData(Customers customers){
        customers.setFirstName(UtilityMethods.modifyString(customers.getFirstName()));
        customers.setLastName(UtilityMethods.modifyString(customers.getLastName()));
        customers.setPostCode(UtilityMethods.modifyString(customers.getPostCode()));
    }


}
