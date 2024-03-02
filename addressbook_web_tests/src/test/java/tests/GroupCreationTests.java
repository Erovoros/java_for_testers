package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase{


    @Test
    public void canCreateGroup() {
        int groupCount = app.groups().getCount();

        app.groups().createGroup(new GroupData("some name", "some header", "some footer"));
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount +1, newGroupCount);


    }

    @Test
    public void canCreateGroupWithEmptyName() {

        app.groups().createGroup(new GroupData());

    }

    @Test
    public void canCreateGroupWithNameOnly() {
        app.groups().createGroup(new GroupData().withName("some name"));
    }
    @Test
    public void canCreateGroupWithHeaderOnly() {
        app.groups().createGroup(new GroupData().withHeader("some header"));
    }


    @Test
    public void canCreateGroupWithFooterOnly() {
        app.groups().createGroup(new GroupData().withFooter("some footer"));
    }



}
