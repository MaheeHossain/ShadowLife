/** Stockpiles, a type of fruitPile gatherers place fruit in, and
 *  thieves also place fruits in
 * @author Mahee Hossain
 * @author Student Number: 1080102
 * @version 1.0
 * @since 23/10/2020
 */

public class Stockpile extends FruitPiles {
    /** The amount of fruit in the hoard */
    public int fruitNum=0;
    private static final String TYPE = "Stockpile";

    public Stockpile(int x, int y) { super("res/images/cherries.png", TYPE, x, y); }

    /** Adds a fruit */
    @Override
    public void add() { fruitNum++; }

    /** Removes a fruit */
    @Override
    public void remove() {
        if (fruitNum > 0) { fruitNum--; }
    }

    /** Pritns the number of fruit */
    @Override
    public void printNumber() {
        appleFont.drawString(String.valueOf(fruitNum), this.getX(), this.getY());
    }

    /** Renders the image and the number of fruit */
    @Override
    public void render() {
        this.getImage().drawFromTopLeft(this.getX(), this.getY());
        printNumber();
    }

    @Override
    public void update() {}
}
