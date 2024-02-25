package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase{


    @Test
    public void canCreateContact() {

        app.contacts().createContact(new ContactData("some first name", "some last name",
                "some address", "some email", "some phone"));

    }

    @Test
    public void canCreateContactWithEmptyName() {

        app.contacts().createContact(new ContactData());

    }

    @Test
    public void canCreateContactWithLastNameOnly() {
        app.contacts().createContact(new ContactData().withFirstName("some first name"));
    }

    @Test
    public void canCreateContactWithTestNameOnly() {
        app.contacts().createContact(new ContactData().withLastName("some last name"));
    }

    @Test
    public void canCreateContactWithAddressOnly() {
        app.contacts().createContact(new ContactData().withAddress("some address"));
    }

    @Test
    public void canCreateContactWithEmailOnly() {
        app.contacts().createContact(new ContactData().withEmail("some email"));
    }

    @Test
    public void canCreateContactWithMobileOnly() {
        app.contacts().createContact(new ContactData().withMobile("some mobile"));
    }


}