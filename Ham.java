
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Ham extends Food{
    private boolean expired;

    public Ham(int x, int y, String name, int age){
        super(name, x, y, age);
        this.expired = false;
        this.nutrition = 15;
        this.isAnimalProduct = true;
        this.isVegetableProduct = false;
    }

    @Override
    public void beEaten(Animal eater){
        if (age > 200){
            nutrition = -5;
            this.expired = true;
        }
        int hunger = eater.getHunger();
        eater.setHunger(hunger -= this.nutrition);
        if (this.expired == true){
            eater.setSickness(true);
        }

        this.isAlive = false;
    }

    @Override
    public void draw(Graphics g){
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
        g.drawString("🍖", Zoo.wrap(this.xCor,Zoo.ZOO_COLS)*Zoo.SCALE, Zoo.wrap(this.yCor,Zoo.ZOO_ROWS)*Zoo.SCALE+25);
    }

    @Override
    public void tick(Zoo z){
        this.age += 1;
    }
    
}
