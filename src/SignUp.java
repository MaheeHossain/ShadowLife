/** Sign that points up
 * @author Mahee Hossain
 * @author Student Number: 1080102
 * @version 1.0
 * @since 23/10/2020
 */

public class SignUp extends Sign {
    private static final int UP = 0;
    private int direction=UP;
    private static final String TYPE = "SignUp";

    public SignUp(int x, int y) {
        super("res/images/up.png", TYPE, x, y);
    }

    /** Returns the direction the sign is pointing */
    @Override
    public int getDirection() { return direction; }

    @Override
    public void update() {}
}