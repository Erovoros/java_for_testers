package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


public class ContactCreationTests extends TestBase {


    @Test
    public void canCreateContact() {

        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10));
        //.withPhoto(randomFile("src/test/resources/images/"));
        app.contacts().createContact(contact);

    }

    @Test
    public void canCreateContactInGroup() {

        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10));
        //.withPhoto(randomFile("src/test/resources/images/"));

        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));

        }
        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());

    }

    @Test
    public void canAddContactToGroup() {


        ContactData contactToAdd = null;
        GroupData groupToAdd = null;

        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "new group", "", ""));
            groupToAdd = app.hbm().getGroupList().get((int) (app.hbm().getGroupCount() - 1));
        } else {
            groupToAdd = app.hbm().getGroupList().get(0);
        }
        for (ContactData contact : app.hbm().getContactList()) {

            if (!app.hbm().isContactInGroup(contact, groupToAdd)) {
                contactToAdd = contact;

                break;
            }
        }


        var oldContacts = app.hbm().getContactsInGroup(groupToAdd);
        if (contactToAdd == null) {
            var contact = new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10));
            app.contacts().createContact(contact);
            contactToAdd = app.hbm().getContactList().get((int)(app.hbm().getContactCount() - 1));
        }
            app.contacts().addContactToGroup(contactToAdd, groupToAdd);


        var newContacts = app.hbm().getContactsInGroup(groupToAdd);
        Assertions.assertEquals(oldContacts.size() + 1, newContacts.size());
    }


    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
//        for (var firstName: List.of("", "first name")) {
//            for (var lastName : List.of("", "last name")){
//                for (var address: List.of("", "address")) {
//                    for (var email: List.of("", "email")) {
//                        for (var mobile : List.of("", "mobile")) {
//
//
//                        result.add(new ContactData()
//                                .withFirstName(firstName)
//                                .withLastName(lastName)
//                                .withAddress(address)
//                                .withEmail(email)
//                                .withMobile(mobile));
//                        }
//                    }
//                }
//            }
//
//        }

        var mapper = new XmlMapper();
        var value = mapper.readValue(new File("contacts.xml"), new TypeReference<List<ContactData>>() {
        });
        result.addAll(value);

        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {


        var oldContacts = app.hbm().getContactList();


        app.contacts().createContact(contact);

        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));

        };
        newContacts.sort(compareById);

        var expectedList = new ArrayList<>(oldContacts);
        var maxId = newContacts.get(newContacts.size() - 1).id();
        expectedList.add(contact.withId(maxId).withAddress("").withEmail("").withMobile(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);


    }


}