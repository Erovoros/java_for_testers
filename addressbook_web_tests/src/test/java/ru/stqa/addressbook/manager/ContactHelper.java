package ru.stqa.addressbook.manager;

import org.openqa.selenium.support.ui.Select;
import ru.stqa.addressbook.model.ContactData;
import org.openqa.selenium.By;
import ru.stqa.addressbook.model.GroupData;

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

    public void createContact(ContactData contact, GroupData group) {
        openAddNewPage();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToHomePage();
    }

    public void addContactToGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectContact(contact);
        selectGroupToAdd(group);
        returnToHomePage();



    }


    private void selectGroupToAdd(GroupData group) {
        click(By.name("to_group"));
        click(By.name("add"));

    }


    private void selectGroup(GroupData group) {

        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    public void removeContact(ContactData contact) {
        openHomePage();
        selectContact(contact);
        removeSelectedContact();
        returnToHomePage();
    }

    public void removeContactInGroup(ContactData contact) {
        openHomePage();
        selectGroupToRemove();
        removeSelectedContactFromGroup();
        returnToHomePage();


    }

    private void removeSelectedContactFromGroup() {
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.name("remove")).click();


    }

    private void selectGroupToRemove() {
        //      click(By.name("group"));
        manager.driver.findElement(By.cssSelector("select[name='group'] option:nth-child(3)")).click();

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
        //    attach(By.name("photo"), contact.photo());

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
