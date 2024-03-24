package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Random;


public class ContactRemovalTests extends TestBase {


    @Test
    public void canRemoveContact() {

        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "first name", "last name", "address", "email", "phone", "", "", ""));

        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);

    }

//    @Test
//    void canRemoveAllContactsAtOnce() {
//        if (app.contacts().getCount() == 0) {
//            app.contacts().createContact(new ContactData("", "first name", "last name", "address", "email", "phone", "photo"));
//        }
//        app.contacts().removeAllContacts();
//        Assertions.assertEquals(0, app.contacts().getCount());
//
//    }

    @Test
    public void canRemoveContactFromGroup() {

        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));

        }

        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "first name", "last name", "address", "email", "phone", "", "", ""));

        }

        var contacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(contacts.size());


        var group = app.hbm().getGroupList().get(0);

        var oldContacts = app.hbm().getContactsInGroup(group);
        if (oldContacts.isEmpty()) {
            app.contacts().addContactToGroup(contacts.get(index), group);
            oldContacts = app.hbm().getContactsInGroup(group);

        }

        index = rnd.nextInt(oldContacts.size());

        app.contacts().removeContactInGroup(oldContacts.get(index));

        var newContacts = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldContacts.size(), newContacts.size() + 1);
    }
}