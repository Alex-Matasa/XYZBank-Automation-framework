package dataObjects;

import lombok.Data;

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

    public void modifyData(Customers customers){
        customers.setFirstName(modifyString(customers.getFirstName()));
        customers.setLastName(modifyString(customers.getLastName()));
        customers.setFullName(customers.getFirstName() + " " + customers.getLastName());
        customers.setPostCode(modifyString(customers.getPostCode()));
    }

    private static String modifyString(String input) {
        if (input == null) {
            return "";
        }
        String trimmed = input.trim();
        return trimmed.replaceAll("\\s+", " ");
    }
}
