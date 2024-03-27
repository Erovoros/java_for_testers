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
    void testAll() {

        if (app.hbm().getContactList().isEmpty()) {
            var contact = new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withEmail(CommonFunctions.randomString(10))
                    .withEmail2(CommonFunctions.randomString(10))
                    .withEmail3(CommonFunctions.randomString(10))
                    .withAddress(CommonFunctions.randomString(10))
                    .withHome(CommonFunctions.randomString(10))
                    .withMobile(CommonFunctions.randomString(10))
                    .withWork(CommonFunctions.randomString(10));

            app.contacts().createContact(contact);
        }


        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary(), contact.email(), contact.email2(), contact.email3(), contact.address())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))


        ));

        var all = app.contacts().getAll();
        Assertions.assertEquals(expected, all);


    }
}




