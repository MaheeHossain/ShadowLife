/** Sign that points right
 * @author Mahee Hossain
 * @author Student Number: 1080102
 * @version 1.0
 * @since 23/10/2020
 */

public class SignRight extends Sign {
    private static final int RIGHT = 3;
    private int direction=RIGHT;
    private static final String TYPE = "SignRight";

    public SignRight(int x, int y) {
        super("res/images/right.png", TYPE, x, y);
    }

    /** Returns the direction the sign is pointing */
    @Override
    public int getDirection() { return direction; }

    @Override
    public void update() {}
}
