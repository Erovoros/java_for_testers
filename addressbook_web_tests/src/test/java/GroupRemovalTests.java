import model.GroupData;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GroupRemovalTests extends TestBase{

    private Map<String, Object> vars;



    @Test
    public void canRemoveGroup() {
        openGroupsPage();


        if (!isGroupPresent()) {
            createGroup(new GroupData("group name", "group header", "group footer"));

        }
        removeGroup();

    }


}
