package model;

public record ContactData (String firstName, String lastName, String address, String email, String mobile) {
    public ContactData() {
        this("", "", "", "", "");
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(firstName, this.lastName, this.address, this.email, this.mobile);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.firstName, lastName, this.address, this.email, this.mobile);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.firstName, this.lastName, address, this.email, this.mobile);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.firstName, this.lastName, this.address, email, this.mobile);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.firstName, this.lastName, this.address, this.email, mobile);
    }




}
