/** Sign that points left
 * @author Mahee Hossain
 * @author Student Number: 1080102
 * @version 1.0
 * @since 23/10/2020
 */

public class SignLeft extends Sign {
    private static final int LEFT = 2;
    private int direction=LEFT;
    private static final String TYPE = "SignLeft";

    public SignLeft(int x, int y) {
        super("res/images/left.png", TYPE, x, y);
    }

    /** Returns the direction the sign is pointing */
    @Override
    public int getDirection() { return direction; }

    @Override
    public void update() {}
}
