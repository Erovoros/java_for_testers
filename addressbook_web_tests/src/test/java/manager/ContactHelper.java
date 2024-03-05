package manager;

import model.ContactData;
import model.GroupData;
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

    private void removeSelectedContacts() {
        manager.driver.findElement(By.cssSelector(".left:nth-child(8) > input")).click();
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
        manager.driver.findElement(By.name("firstname")).click();
        manager.driver.findElement(By.name("firstname")).sendKeys(contact.firstName());
        manager.driver.findElement(By.name("lastname")).click();
        manager.driver.findElement(By.name("lastname")).sendKeys(contact.lastName());
        manager.driver.findElement(By.name("address")).click();
        manager.driver.findElement(By.name("address")).sendKeys(contact.address());
        manager.driver.findElement(By.name("mobile")).click();
        manager.driver.findElement(By.name("mobile")).sendKeys(contact.mobile());
        manager.driver.findElement(By.name("email")).click();
        manager.driver.findElement(By.name("email")).sendKeys(contact.email());
    }
//доделать
    public List<ContactData> getList() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var entries = manager.driver.findElements(By.name("entry"));
        for (var entry: entries) {

            var checkbox = entry.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");


            String lastName = null;
            String firstName = null;



            var tds = manager.driver.findElements(By.tagName("td"));
            for (int i = 0; i <= 2; i ++){

                if (i == 1) {
                    lastName = tds.get(i).getText();
                }
                if (i == 2) {
                    firstName = tds.get(i).getText();
                }

            }



            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
        }
        return contacts;
    }


}
