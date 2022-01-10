
import Controllers.DeedController;


/*Test to see if the deeds actually are created by valued */
public class CreateDeedsTest {
    public static void main(String[] args) {
        DeedController deedController = new DeedController();
        deedController.createDeeds();
        int val = 0;
        for (int i = 0; i < 22; i++) {
            val +=deedController.getProperties()[i].getValue();

        }
        System.out.println(val);


    }
}
