package ru.stqa.addressbook.manager;

import ru.stqa.addressbook.model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{


    public ContactHelper(ApplicationManager manager) {

        super(manager);
    }
    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        openHomePage();
        selectAllContacts();
        removeSelectedContacts();

    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox: checkboxes) {
            checkbox.click();
        }
    }

    public void createContact(ContactData contact) {
        openAddNewPage();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void removeContact(ContactData contact) {
        openHomePage();
        selectContact(contact);
        removeSelectedContact();
        returnToHomePage();
    }




    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openHomePage();
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }

    private void initContactModification(ContactData contact) {

        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", contact.id())));
    }

    private void removeSelectedContacts() {
        manager.driver.findElement(By.cssSelector(".left:nth-child(8) > input")).click();
    }

    private void submitContactModification() {
        click(By.name("update"));

    }





    private void removeSelectedContact() {
        manager.driver.findElement(By.cssSelector(".left:nth-child(8) > input")).click();
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    private void returnToHomePage() {
        manager.driver.findElement(By.linkText("home")).click();
    }

    private void submitContactCreation() {
        manager.driver.findElement(By.cssSelector("input:nth-child(75)")).click();
    }





    public void openAddNewPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            manager.driver.findElement(By.linkText("add new")).click();
        }
    }

    public void openHomePage() {
        if (!manager.isElementPresent(By.name("searchstring"))) {
            returnToHomePage();
        }
    }

    public boolean isContactPresent() {
        openHomePage();
        return manager.isElementPresent(By.name("selected[]"));
    }







    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
        attach(By.name("photo"), contact.photo());

    }

    public List<ContactData> getList() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var entries = manager.driver.findElements(By.name("entry"));
        for (var entry : entries) {
            var checkbox = entry.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");

            var tds = entry.findElements(By.tagName("td"));

                String lastName = tds.get(1).getText();
                String firstName = tds.get(2).getText();
                contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));

        }
        return contacts;
    }


}
