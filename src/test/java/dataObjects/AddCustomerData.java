package dataObjects;

public class AddCustomerData extends BaseData{

    private String firstName;
    private String lastName;
    private String postCode;


    public AddCustomerData(String filePath) {
        fromJsonToObject(filePath);
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

}
