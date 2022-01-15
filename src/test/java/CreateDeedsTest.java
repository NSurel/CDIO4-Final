
import Controllers.DeedController;
import Controllers.FieldController;

import java.io.IOException;


/*Test to see if the deeds actually are created by valued */
public class CreateDeedsTest {
    public static void main(String[] args) throws IOException {
        DeedController deedController = new DeedController();
        FieldController fieldController = new FieldController();
        deedController.createDeeds(fieldController);
        int val = 0;
        for (int i = 0; i < 22; i++) {
            val +=deedController.getProperties()[i].getValue();

        }
        System.out.println(val);


    }
}
