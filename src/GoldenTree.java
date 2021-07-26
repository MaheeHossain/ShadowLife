/** GoldenTree, a tree with an infinite amount of fruit
 * @author Mahee Hossain
 * @author Student Number: 1080102
 * @version 1.0
 * @since 23/10/2020
 */

public class GoldenTree extends Actor {
    private static final String TYPE = "GoldenTree";

    public GoldenTree(int x, int y) {
        super("res/images/gold-tree.png", TYPE, x, y);
    }

    @Override
    public void update() {}
}
