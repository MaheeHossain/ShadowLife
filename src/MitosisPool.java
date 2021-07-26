/** MitosisPool, a pool that duplicates the players
 * @author Mahee Hossain
 * @author Student Number: 1080102
 * @version 1.0
 * @since 23/10/2020
 */

public class MitosisPool extends Actor {
    private static final String TYPE = "MitosisPool";

    public MitosisPool(int x, int y) {
        super("res/images/pool.png", TYPE, x, y);
    }

    @Override
    public void update() {}
}
