package dataObjects;

import lombok.Data;

import java.util.List;

@Data
public class CustomerData {

    private String firstName;
    private String lastName;
    private String postCode;
    private String fullName;
    private String customerId;
    private List<AccountData> accounts;

    public void manipulateData() {

            this.fullName = firstName + " " + lastName;

    }
}
