/** Gatherer, a type of player. It takes fruit from trees
 *  and places them into hoards and stockpiles
 * @author Mahee Hossain
 * @author Student Number: 1080102
 * @version 1.0
 * @since 23/10/2020
 */

public class Gatherer extends Players {
    private static final String TYPE = "Gatherer";

    public Gatherer(int x, int y) {
        super("res/images/gatherer.png", TYPE, x, y);
        direction = Direction.moveLeft();
    }

    public Gatherer(int x, int y, int direction) {
        super("res/images/gatherer.png", TYPE, x, y);
        this.direction = direction;
    }

    /** Duplicates the gatherer and makes them go across */
    @Override
    public void mitosis() {
        this.clockwiseDirection();
        Gatherer clone = new Gatherer(this.getX(), this.getY(), this.direction);
        clone.reverseDirection();
    }

    /** Makes the gatherer go in the direction of the sign */
    @Override
    public void signDirection(int signDirection) { this.direction = signDirection; }

    /** Makes the gatherer interact with the goldenTree  */
    @Override
    public void goldenFruitInteractive() {
        if (!this.isCarrying()) {
            this.setCarrying(true);
            this.reverseDirection();
        }
    }

    /** Makes the gatherer interact with the fruitPile
     *  @param fruitPiles The fruitPile the player is interacting with */
    @Override
    public void fruitInteractive(FruitPiles fruitPiles) {
        /* Check if the gatherer should pick from a tree */
        if (!this.isCarrying() && fruitPiles.getType().equals("Tree") && fruitPiles.getFruitNum()>0) {
            this.setCarrying(true);
            fruitPiles.remove();
            this.reverseDirection();
        }

        /* Check if the gatherer should place fruit in pile */
        if (fruitPiles.getType().matches("Hoard|Stockpile")) {
            if (this.isCarrying() && fruitPiles.getFruitNum()<3) {
                this.setCarrying(false);
                fruitPiles.add();
            }
            this.reverseDirection();
        }
    }
}
