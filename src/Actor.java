import bagel.Image;

import java.util.ArrayList;
import java.util.Objects;

/** Abstract Class Actor, fills the ShadowLife world
 * @author Mahee Hossain
 * @author Student Number: 1080102
 * @version 1.0
 * @since 23/10/2020
 */

public abstract class Actor {
    private int x;
    private int y;
    private Point location;

    /** Each actor has an arraylist of all the actors */
    public ArrayList<Actor> actorList;

    private final Image image;
    public final String type;

    public Actor(String filename, String type, int x, int y) {
        image = new Image(filename);
        this.type = type;
        this.x = x;
        this.y = y;
        location = new Point(x, y);
    }

    /** Updates the actor every tick */
    public final void tick() {
        update();
    }

    /** Displays the image */
    public void render() {
        image.drawFromTopLeft(x, y);
    }

    /** Moves the actor by the given amounts
     *  @param deltaX Amount moved in the x direction
     *  @param deltaY Amount moved in the y direction */
    public void move(int deltaX, int deltaY) {
        x += deltaX;
        y += deltaY;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public Point getLocation() { return location; }

    public String getType() { return type; }

    public Image getImage() { return image; }

    public void setActorList(ArrayList<Actor> actors) { this.actorList = actors; }

    public void setLocation(Point location) { this.location = location; }

    @Override
    public String toString() {
        return "Actor {" + "x = " + x + "; y = " + y + "; location = " + location.toString() +
                "; tileActors = " + actorList.toString() + "; image = " + image.toString() +
                "; type = '" + type + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return x == actor.x &&
                y == actor.y &&
                Objects.equals(location, actor.location) &&
                Objects.equals(actorList, actor.actorList) &&
                Objects.equals(image, actor.image) &&
                Objects.equals(type, actor.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, location, actorList, image, type);
    }

    public abstract void update();
}
