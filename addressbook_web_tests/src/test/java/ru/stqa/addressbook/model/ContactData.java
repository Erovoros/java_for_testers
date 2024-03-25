package ru.stqa.addressbook.model;

public record ContactData (String id, String firstName, String lastName, String address, String email, String mobile,
                           String home, String work, String secondary, String email2, String email3) {
    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.lastName, this.address, this.email, this.mobile, this.home, this.work, this.secondary, this.email2, this.email3);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.lastName, this.address, this.email, this.mobile, this.home, this.work, this.secondary, this.email2, this.email3);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, lastName, this.address, this.email, this.mobile, this.home, this.work, this.secondary, this.email2, this.email3);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.lastName, address, this.email, this.mobile, this.home, this.work, this.secondary, this.email2, this.email3);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, email, this.mobile, this.home, this.work, this.secondary, this.email2, this.email3);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.email, mobile, this.home, this.work, this.secondary, this.email2, this.email3);
    }


    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.email, this.mobile, home, this.work, this.secondary, this.email2, this.email3);
    }

    public ContactData withWork(String work) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.email, this.mobile, this.home, work, this.secondary, this.email2, this.email3);
    }
    public ContactData withSecondary(String secondary) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.email, this.mobile, this.home, this.work, secondary, this.email2, this.email3);
    }
    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.email, this.mobile, this.home, this.work, this.secondary, email2, this.email3);
    }
    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.email, this.mobile, this.home, this.work, this.secondary, this.email2, email3);
    }











//    public ContactData withPhoto(String photo) {
//        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.email, this.mobile, photo);
//    }



}
