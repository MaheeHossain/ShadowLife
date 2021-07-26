/** Sign that points down
 * @author Mahee Hossain
 * @author Student Number: 1080102
 * @version 1.0
 * @since 23/10/2020
 */

public class SignDown extends Sign {
    private static final int DOWN = 1;
    private int direction=DOWN;
    private static final String TYPE = "SignDown";

    public SignDown(int x, int y) {
        super("res/images/down.png", TYPE, x, y);
    }

    /** Returns the direction the sign is pointing */
    @Override
    public int getDirection() { return direction; }

    @Override
    public void update() {}
}