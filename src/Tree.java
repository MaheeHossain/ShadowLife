/** Tree, a type of fruitPile gatherers take fruit from
 * @author Mahee Hossain
 * @author Student Number: 1080102
 * @version 1.0
 * @since 23/10/2020
 */

public class Tree extends FruitPiles {
    /** The amount of fruit in the tree */
    public int fruitNum=3;
    private static final String TYPE = "Tree";

    public Tree(int x, int y) {
        super("res/images/tree.png", TYPE, x, y);
    }

    /** Add can't be used in Tree */
    @Override
    public void add() {}

    /** Removes a fruit */
    @Override
    public void remove() {
        if (fruitNum > 0) { fruitNum--; }
    }

    /** Prints the amount of fruit on the tree */
    @Override
    public void printNumber() {
        appleFont.drawString(String.valueOf(fruitNum), this.getX(), this.getY());
    }

    /** Returns the number of fruit */
    @Override
    public int getFruitNum() { return fruitNum; }

    /** Renders the image and the number of fruit */
    @Override
    public void render() {
        this.getImage().drawFromTopLeft(this.getX(), this.getY());
        printNumber();
    }

    @Override
    public void update() {}
}