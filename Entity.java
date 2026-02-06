
import java.awt.Graphics;


public abstract class Entity {
    private static int lastID = 0;
    protected int id;
    protected int xCor;
    protected int yCor;
    protected int age;
    protected boolean isAlive;


    public Entity(String name, int x, int y, int age) {
        this.id = lastID;
        lastID = lastID + 1;
        xCor = x;
        yCor = y;
        this.age = age;
        isAlive = true;
    }

    // ABSTRACT METHODS
    // tick and draw are called by the Zoo class

    public abstract void tick(Zoo z);
    public abstract void draw(Graphics g);


    // NON-ABSTRACT METHODS
    // isAlive, getX, and getY are all called by the Zoo class

    public boolean isAlive() {
        return this.isAlive;
    }

    public int getX() {
        return this.xCor;
    }

    public int getY() {
        return this.yCor;
    }

}
