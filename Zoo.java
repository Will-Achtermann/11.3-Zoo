
import javax.swing.*;
import java.awt.*;

import java.util.*;

// in general it would be better practice to separate the data and display functionalities of Zoo
// into separate classes - they're combined here for simplicity and ease of understanding
public class Zoo extends JPanel {

    // zoo grid size
    public static final int ZOO_ROWS = 30; // grid height
    public static final int ZOO_COLS = 40; // grid width
    // screen size is the zoo grid sizes * SCALE
    public static final int SCALE = 30;

    // use this single Random object to generate ANY and ALL random numbers you need
    // there's an important reason why that's too long to explain in comments but ask me if you're curious 
    public static Random rand = new Random();

    //

    private int width, height;
    private ArrayList<ArrayList<LinkedList<Entity>>> grid;

    public Zoo(int w, int h) {
        // initalize the grid using ArrayLists for the rows and colums and LinkedLists for the cell stack
        grid = new ArrayList<>(h);

        for(int y = 0; y < h; y++) {
            ArrayList<LinkedList<Entity>> row = new ArrayList<>(w);
            for(int x = 0; x < w; x++) {
                row.add(new LinkedList<Entity>());
            }
            grid.add(row);
        }
        width = w;
        height = h;
    }

	public void paintComponent(Graphics g){
		super.paintComponent(g); 
		setBackground(Color.GREEN);

        // draw cell grids
        g.setColor(new Color(0, 200, 0)); // dark green
        for(int y = 0; y < height; y++) {
            g.drawLine(0, y * SCALE, width * SCALE, y * SCALE);
        }
        for(int x = 0; x < width; x++) {
            g.drawLine(x * SCALE, 0, x * SCALE, height * SCALE);
        }

        // draw Entities
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                for(Entity e : grid.get(y).get(x)) {
                    e.draw(g);
                }
            }
        }
	}

    // iterates through each cell in the grid, calling tick(Zoo)
    public void tick() {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {

                // iterate backwards through the list because we might remove elements
                // removing an element in forward iteration would cause the remaining elements to shift back
                // causing us to skip the element immediately following the skipped element
                for(int i = grid.get(y).get(x).size() - 1; i >= 0; i--) {

                    // removing e from the grid means that e cannot interact with itself
                    // in its tick(Zoo) method
                    Entity e = grid.get(y).get(x).remove(i);

                    if(e.isAlive()) {
                        e.tick(this);
                        grid.get(wrap(e.getY(), height)).get(wrap(e.getX(), width)).add(e);
                    }
                }
            }
        }
    }

    // get a list of Entities at position x, y in the grid
    public ArrayList<Entity> at(int x, int y) {
        int atX = wrap(x, width);
        int atY = wrap(y, height);
        // ArrayList constructor copies references from the passed LinkedList
        return new ArrayList<Entity>(grid.get(atY).get(atX));
    }

    // add an Entity to the grid
    public void add(Entity e) {
        int atX = wrap(e.getX(), width);
        int atY = wrap(e.getY(), height);
        grid.get(atY).get(atX).add(e);
    }

    // wrap a val between 0 and thresh
    public static int wrap(int val, int thresh) {
        if(val >= 0) {
            return val % thresh;
        }
        else {
            return (thresh - val) % thresh;
        }
    }

    public static void main(String[] args) {
        // main Zoo object
        Zoo zoo = new Zoo(ZOO_COLS, ZOO_ROWS);

        JFrame frame = new JFrame("Zoo");
		frame.setSize(ZOO_COLS * SCALE + SCALE/2, ZOO_ROWS * SCALE + SCALE/2 + 23);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(zoo);
		frame.setVisible(true);

        // TODO: add food and animals to the zoo
        Cat c1 = new Cat("cat1", 10, 7, 20);
        zoo.add(c1);
        Cat c2 = new Cat("Cat2", 3, 3, 40);
        zoo.add(c2);
        Cat c3 = new Cat("Cat3", 1, 6, 0);
        zoo.add(c3);

        Dog d1 = new Dog("d1", 2, 1, 8);
        zoo.add(d1);
        Dog d2 = new Dog("d2", 10, 10, 50);
        zoo.add(d2);

        Ham h1 = new Ham(4, 5, "h1", 0);
        zoo.add(h1);
        Ham h2 = new Ham(9, 3, "h2", 0);
        zoo.add(h2);
        Ham h3 = new Ham(7, 5, "h3", 0);
        zoo.add(h3);

        Cheese ch1 = new Cheese(6, 4, "ch1", 0);
        zoo.add(ch1);
        Cheese ch2 = new Cheese(4, 6, "ch2", 0);
        zoo.add(ch2);
        Cheese ch3 = new Cheese(4, 8, "ch3", 0);
        zoo.add(ch3);

        Rat r1 = new Rat("r1", 2, 2, 0);
        zoo.add(r1);
        Rat r2 = new Rat("r2", 6, 7, 0);
        zoo.add(r2);

        int tickCount = 0;
        while(true) {
            try {
                Thread.sleep(100);
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }

            // TODO: add food and animals every 50, 100, 150, etc. ticks using tickCount and modulo (%)

            zoo.tick();

            // redraw the frame
            zoo.revalidate();
            zoo.repaint();

            tickCount++;
        }
    }

}
