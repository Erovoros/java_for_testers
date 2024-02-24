package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GroupRemovalTests extends TestBase{

    private Map<String, Object> vars;



    @Test
    public void canRemoveGroup() {
        app.openGroupsPage();


        if (!app.isGroupPresent()) {
            app.createGroup(new GroupData("group name", "group header", "group footer"));

        }
        app.removeGroup();

    }


}
