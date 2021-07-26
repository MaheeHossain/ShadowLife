/** Abstract Class Sign, makes the player go a certain direction
 * @author Mahee Hossain
 * @author Student Number: 1080102
 * @version 1.0
 * @since 23/10/2020
 */

public abstract class Sign extends Actor {
    /** The direction the player will travel */
    public int direction;

    public Sign(String filename, String type, int x, int y) {super(filename, type, x, y); }

    /** Returns the direction the sign is pointing */
    public int getDirection() { return direction; }

    @Override
    public void update() {}
}