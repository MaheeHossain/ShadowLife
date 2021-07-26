import java.util.Objects;

/** Abstract Class Players, moves around and interacts with other actors
 * @author Mahee Hossain
 * @author Student Number: 1080102
 * @version 1.0
 * @since 23/10/2020
 */
public abstract class Players extends Actor implements Movable {
    public int direction;
    private boolean carrying = false;
    private boolean active = true;
    private int tickCounter = -1;

    public Players(String filename, String type, int x, int y) {
        super(filename, type, x, y);
    }

    /** Sets the direction the player goes
     *  @param signDirection The direction found on the sign */
    public void setDirection(int signDirection) { this.direction = signDirection; }

    /** States whether or not the player is carrying fruit */
    public boolean isCarrying() { return carrying; }

    /** Sets whether or not the player is carrying fruit
     *  @param carrying The new carrying state */
    public void setCarrying(boolean carrying) { this.carrying = carrying; }

    /** States whether or not the player is active */
    public boolean isActive() { return active; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Players players = (Players) o;
        return direction == players.direction &&
                carrying == players.carrying &&
                active == players.active &&
                tickCounter == players.tickCounter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), direction, carrying, active, tickCounter);
    }

    /** Makes the player move one tile in the direction it has */
    public void move() {
        switch (direction) {
            case Direction.UP:
                move(0, -ShadowLife.TILE_SIZE);
                break;
            case Direction.DOWN:
                move(0, ShadowLife.TILE_SIZE);
                break;
            case Direction.LEFT:
                move(-ShadowLife.TILE_SIZE, 0);
                break;
            case Direction.RIGHT:
                move(ShadowLife.TILE_SIZE, 0);
                break;
        }
    }

    /** Update the point to be the current point */
    public void fillPoint() {
        Point currPoint = new Point(getX(), getY());
        setLocation(currPoint);
    }

    /** Called if the player is on a fruitPile
     *  @param fruitPiles The fruitPile the player is interacting with */
    public void fruitInteractive(FruitPiles fruitPiles) {}

    /** Called if the player is on a golden tree */
    public void goldenFruitInteractive() {}

    /** Called if the player hits a mitosis pool */
    public void mitosis() {}

    /** Called if the player is on a pad */
    public void consuming() {}

    /** Makes the player de-active, and moves it to the previous tile */
    public void deactivate() {
        this.active=false;
        reverseDirection();
        move();
    }

    /** Reverses the direction */
    @Override
    public void reverseDirection() {
        if (direction == Direction.UP) { direction = Direction.DOWN; }
        else if (direction == Direction.DOWN) { direction = Direction.UP; }
        else if (direction == Direction.LEFT) { direction = Direction.RIGHT; }
        else if (direction == Direction.RIGHT) { direction = Direction.LEFT; }
    }

    /** Turns the direction 90 degrees clockwise */
    @Override
    public void clockwiseDirection() {
        if (direction == Direction.UP) { direction = Direction.RIGHT; }
        else if (direction == Direction.DOWN) { direction = Direction.LEFT; }
        else if (direction == Direction.LEFT) { direction = Direction.DOWN; }
        else if (direction == Direction.RIGHT) { direction = Direction.UP; }
    }

    /** Turns the direction 270 degrees clockwise*/
    @Override
    public void anticlockwiseDirection() {
        if (direction == Direction.UP) { direction = Direction.LEFT; }
        else if (direction == Direction.DOWN) { direction = Direction.RIGHT; }
        else if (direction == Direction.LEFT) { direction = Direction.UP; }
        else if (direction == Direction.RIGHT) { direction = Direction.DOWN; }
    }

    /** Checks what actor it sharing a tile with, and does the appropriate action */
    public void checkPoint() {
        for (Actor actor : actorList) {
            if (actor.getLocation().equals(this.getLocation())) {
                /* If its on a FruitPile, fruits */
                if (actor.getType().matches("Tree|Hoard|Stockpile")) {
                    FruitPiles fruitPiles = (FruitPiles) actor;
                    fruitInteractive(fruitPiles);
                }
                /* If its on a GoldenTree, do goldenFruits*/
                else if (actor.getType().matches("GoldenTree")) {
                    goldenFruitInteractive();
                }
                /* If its on a mitosis pool, do mitosis */
                else if (actor.getType().matches("MitosisPool")) {
                    mitosis();
                }
                /* If its on a pad, do consumption */
                else if (actor.getType().matches("Pad")) {
                    consuming();
                }
                /* If its on a sign, follow the given direction */
                else if (actor.getType().matches("SignDown|SignRight|SignUp|SignLeft")) {
                    Sign sign = (Sign) actor;
                    setDirection(sign.getDirection());
                }
                /* If its on a fence, do deactivate */
                else if (actor.getType().matches("Fence")) {
                    deactivate();
                }
            }
        }
    }

    @Override
    public void update() {
        fillPoint();
        checkPoint();
        if (this.active) { move(); }
//        System.out.println(getLocation().toString()
//                + " | Actual Point {x = " + getX() + "; y = " + getY() + "}");
    }
}
