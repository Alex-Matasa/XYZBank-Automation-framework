package dataObjects;

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

    public void manipulateData() {

            this.fullName = firstName + " " + lastName;
    }
}
