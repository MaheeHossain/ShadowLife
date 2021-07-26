public class Thief extends Players {
    public boolean consuming = false;
    private static final String TYPE = "Thief";

    public Thief(int x, int y) {
        super("res/images/thief.png", TYPE, x, y);
        direction = Direction.moveUP();
    }

    public Thief(int x, int y, int direction) {
        super("res/images/thief.png", TYPE, x, y);
        this.direction = direction;
    }

    /** Duplicates the thief and makes them go across */
    @Override
    public void mitosis() {
        this.clockwiseDirection();
        Thief clone = new Thief(this.getX(), this.getY(), this.direction);
        clone.reverseDirection();
    }

    /** Sets the thief to consuming mode */
    @Override
    public void consuming() { consuming = true; }

    /** Makes the thief go in the direction of the sign */
    @Override
    public void signDirection(int signDirection) { this.direction = signDirection; }

    /** Makes the thief interact with the fruitPile
     *  @param fruitPiles The fruitPile the player is interacting with */
    @Override
    public void fruitInteractive(FruitPiles fruitPiles) {
        /* Check if the thief is on a hoard */
        if (fruitPiles.getType().matches("Hoard")) {

            /* If the thief is consuming, set to false and check if its carrying */
            if (consuming) {
                consuming = false;

                /* If its not carrying, and there's fruit, eat one, else go clockwise */
                if (!this.isCarrying()) {
                    if (fruitPiles.getFruitNum() > 0) {
                        this.setCarrying(true);
                        fruitPiles.remove();
                    }
                    else {
                        this.clockwiseDirection();
                    }
                }
            }
            /* If its not consuming, but is carrying, drop fruit here */
            else if (this.isCarrying()) {
                this.setCarrying(false);
                fruitPiles.add();
            }
        }

        /* Check if the thief is on a stockpile */
        if (fruitPiles.getType().matches("Stockpile")) {
            /* If it isn't carrying and stockpile not empty, steal fruit and go */
            if (!this.isCarrying() && fruitPiles.getFruitNum() > 0) {
                this.setCarrying(true);
                consuming = false;
                fruitPiles.remove();
            }
            this.clockwiseDirection();
        }
    }
}