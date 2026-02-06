
// TODO: extend Entity
public abstract class Animal {
    protected int hunger;
    protected boolean isSick;

    public Animal() {
        this.hunger = 0;
        this.isSick = false;
    }

    
    public abstract void eat(); 
    public abstract void move();
} //TODO: add non-abstact methods as necessary
