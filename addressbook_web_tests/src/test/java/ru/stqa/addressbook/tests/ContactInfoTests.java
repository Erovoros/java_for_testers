package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {


    @Test
    void testPhones() {
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))


        ));

        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);

    }

    @Test
    void testEmails() {

        if (app.hbm().getContactList().isEmpty()) {
            var contact = new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withEmail(CommonFunctions.randomString(10))
                    .withEmail2(CommonFunctions.randomString(10))
                    .withEmail3(CommonFunctions.randomString(10));

            app.contacts().createContact(contact);


        }
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var emails = app.contacts().getEmails(contact);
        var expected = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, emails);


    }

    @Test
    void testAddress() {

        if (app.hbm().getContactList().isEmpty()) {
            var contact = new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withAddress(CommonFunctions.randomString(10));


            app.contacts().createContact(contact);


        }
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var address = app.contacts().getAddress(contact);
        var expected = Stream.of(contact.address())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, address);


    }
}
