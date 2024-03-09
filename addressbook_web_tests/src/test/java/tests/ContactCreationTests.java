package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{


    @Test
    public void canCreateContact() {

        var contact = new ContactData()
                .withFirstName(randomString(10))
                .withLastName(randomString(10))
                .withPhoto("src/test/resources/images/avatar.png");
        app.contacts().createContact(contact);

    }




    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstName: List.of("", "first name")) {
            for (var lastName : List.of("", "last name")){
                for (var address: List.of("", "address")) {
                    for (var email: List.of("", "email")) {
                        for (var mobile : List.of("", "mobile")) {


                        result.add(new ContactData()
                                .withFirstName(firstName)
                                .withLastName(lastName)
                                .withAddress(address)
                                .withEmail(email)
                                .withMobile(mobile));
                        }
                    }
                }
            }

        }
        for (int i = 0; i < 5; i++){
            result.add(new ContactData()
                    .withFirstName(randomString(i * 10))
                    .withLastName(randomString(i * 10))
                    .withAddress(randomString(i * 10))
                    .withEmail(randomString(i * 10))
                    .withMobile(randomString(i * 10)));

        }
        return result;
    }
/*
    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {


        var oldContacts = app.contacts().getList();


        app.contacts().createContact(contact);

        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));

        };
        newContacts.sort(compareById);

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() -1).id()).withAddress("").withEmail("").withMobile(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);




    }



*/




}