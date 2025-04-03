package dataObjects;

import helperMethods.StringUtils;
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
    private List<Accounts> accounts = new ArrayList<>();


    public void setFullName() {
        this.fullName = firstName + " " + lastName;
    }

    public void sanitizeData() {
        this.firstName = StringUtils.trimSpacesToOne(this.firstName);
        this.lastName = StringUtils.trimSpacesToOne(this.lastName);
        this.postCode = StringUtils.trimSpacesToOne(this.postCode);
        this.fullName = this.firstName + " " + this.lastName;
    }
}
