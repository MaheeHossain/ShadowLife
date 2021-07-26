import bagel.Font;

/** Abstract Class FruitPiles, has a certain number of fruit inside it
 * @author Mahee Hossain
 * @author Student Number: 1080102
 * @version 1.0
 * @since 23/10/2020
 */
public abstract class FruitPiles extends Actor implements FruitChangeable {
    private int fruitNum;
    /** Font for trees */
    public Font appleFont = new Font("res/VeraMono.ttf", 24);

    public FruitPiles(String filename, String type, int x, int y) {super(filename, type, x, y); }

    /** Adds a fruit */
    @Override
    public void add() {fruitNum++; }

    /** Removes a fruit */
    @Override
    public void remove() {
        if (fruitNum > 0) { fruitNum--; }
    }

    /** Displays the number of fruits */
    public void printNumber() {}

    /** Returns the number of fruits */
    public int getFruitNum() { return fruitNum; }
}
