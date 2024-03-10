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

public class ContactCreationTests extends TestBase{


    @Test
    public void canCreateContact() {

        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10));
                //.withPhoto(randomFile("src/test/resources/images/"));
        app.contacts().createContact(contact);

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
        var value = mapper.readValue(new File("contacts.xml"), new TypeReference<List<ContactData>>() {});
        result.addAll(value);

        return result;
    }

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








}