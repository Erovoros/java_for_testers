package ru.stqa.addressbook.model;

public record ContactData (String id, String firstName, String lastName, String address, String email, String mobile, String photo) {
    public ContactData() {
        this("", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.lastName, this.address, this.email, this.mobile, this.photo);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.lastName, this.address, this.email, this.mobile, this.photo);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, lastName, this.address, this.email, this.mobile, this.photo);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.lastName, address, this.email, this.mobile, this.photo);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, email, this.mobile, this.photo);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.email, mobile, this.photo);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.email, this.mobile, photo);
    }




}
