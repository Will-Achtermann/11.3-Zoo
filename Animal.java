
public abstract class Animal extends Entity{
    protected int hunger;
    protected boolean isSick;

    public Animal(String name, int x, int y, int age) {
        super(name, x, y, age);
        this.hunger = 0;
        this.isSick = false;
    }

    
    public abstract void eat(Food f); 
    public abstract void move(Zoo z);

    public void setHunger(int num){
        this.hunger = num;
    }

    public int getHunger(){
        return hunger;
    }

    public void setSickness(boolean tf){
        this.isSick = tf;
    }
} //TODO: add non-abstact methods as necessary
