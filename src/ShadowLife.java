import bagel.AbstractGame;
import bagel.Image;
import bagel.Input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/** Runs the simulation Shadow Life. Based off sample code from Assignment 1
 * @author Mahee Hossain
 * @author Student Number: 1080102
 * @version 1.0
 * @since 23/10/2020
 */
public class ShadowLife extends AbstractGame {
    private static String currentWorldFile;

    /** Represents the size of each virtual tile */
    public static final int TILE_SIZE = 64;

    /* Helps count the ticks */
    private long lastTick = 0;
    private static int numTicks = 0;
    private static int tickTime = 500;
    private static int maxTicks = 500;

    /** An arraylist of all the actors in the world file */
    public ArrayList<Actor> actors = new ArrayList();

    /* Background image */
    private final Image background = new Image("res/images/background.png");

    /* Checks if the simulation is active or not */
    private boolean simulationActive = false;

    /* Fills the actors arraylist */
    private void loadActors() {
        /* Insert the name of the world file you want to test */
        String worldFile = currentWorldFile;

        /* Reads the world file, and fills the actors arraylist with actors */
        try (BufferedReader reader = new
                BufferedReader(new FileReader(worldFile))) {

            String text;
            while ((text = reader.readLine()) != null) {
                /* Reads a line from the world file, splits it into an array */
                // Line format is: type,x,y
                String[] parts = text.split(",");
                String type = parts[0];
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);

                /* Switch statement which puts all the actors in the arraylist */
                switch (type) {
                    case Tree.TYPE:
                        Tree tree = new Tree(x, y);
                        actors.add(tree);
                        break;
                    case Stockpile.TYPE:
                        Stockpile stockpile = new Stockpile(x, y);
                        actors.add(stockpile);
                        break;
                    case Hoards.TYPE:
                        Hoards hoards = new Hoards(x, y);
                        actors.add(hoards);
                        break;
                    case Fence.TYPE:
                        Fence fence = new Fence(x, y);
                        actors.add(fence);
                        break;
                    case Pad.TYPE:
                        Pad pad = new Pad(x, y);
                        actors.add(pad);
                        break;
                    case GoldenTree.TYPE:
                        GoldenTree goldenTree = new GoldenTree(x, y);
                        actors.add(goldenTree);
                        break;
                    case MitosisPool.TYPE:
                        MitosisPool mitosisPool = new MitosisPool(x, y);
                        actors.add(mitosisPool);
                        break;
                    case SignLeft.TYPE:
                        SignLeft left = new SignLeft(x, y);
                        actors.add(left);
                        break;
                    case SignRight.TYPE:
                        SignRight right = new SignRight(x, y);
                        actors.add(right);
                        break;
                    case SignDown.TYPE:
                        SignDown down = new SignDown(x, y);
                        actors.add(down);
                        break;
                    case SignUp.TYPE:
                        SignUp up = new SignUp(x, y);
                        actors.add(up);
                        break;
                    case Gatherer.TYPE:
                        Gatherer gatherer = new Gatherer(x, y);
                        actors.add(gatherer);
                        break;
                    case Thief.TYPE:
                        Thief thief = new Thief(x, y);
                        actors.add(thief);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /** Runs the simulation */
    public ShadowLife() {
        loadActors();
    }

    @Override
    protected void update(Input input) {
        /* If enough time has passed, run the next tick */
        if (System.currentTimeMillis() - lastTick > tickTime) {
            lastTick = System.currentTimeMillis();
            numTicks++;
            simulationActive = false;

            /* In the actors arraylist, go through every actor and run its tick */
            for (Actor actor : actors) {
                if (actor != null) {
                    actor.setActorList(actors);
                    actor.tick();

                    /* Check if all the players have stopped being active yet,
                     *  if so simulation won't be active */
                    if (actor.getType().matches("Gatherer|Thief")) {
                        Players player = (Players) actor;
                        if (player.isActive()) { simulationActive = true; }
                    }
                }
            }
        }

        /* Checks if the simulation is active or not, if not prints info */
        if (!simulationActive) {
            System.out.println((numTicks-1) + " ticks");
            for (Actor actor: actors) {
                if (actor.getType().matches("Hoard|Stockpile|Tree")) {
                    FruitPiles fruitPiles = (FruitPiles) actor;
                    System.out.println(fruitPiles.getFruitNum());
                }
            }
            System.exit(0);
        }

        /* Checks if the maximum amount of ticks has passed */
        if (numTicks > maxTicks) {
            System.out.println("Timed out");
            System.exit(-1);
        }

        /* Draw all elements */
        background.drawFromTopLeft(0, 0);
        for (Actor actor : actors) {
            if (actor != null) {
                actor.render();
            }
        }
    }

    /** The main method where the game is run
     * @param args An array with the (int) tick rate, (int) maximum number
     *             of ticks, and (string) location of the world file
     */
    public static void main(String[] args) {
        try {
            tickTime = Integer.parseInt(args[0]);
            maxTicks = Integer.parseInt(args[1]);
            currentWorldFile = args[2];
        }
        catch (Exception e) {
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
        }
        new ShadowLife().run();
    }
}
