
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Cheese extends Food{
    private boolean expired;
    private int lives;

    public Cheese(int x, int y, String name, int age){
        super(name, x, y, age);
        this.expired = false;
        this.nutrition = 10;
        this.lives = 3;
    }

    @Override
    public void beEaten(Animal eater){
        if (lives == 2){
            nutrition = 8;
        }else if (lives == 1){
            nutrition = 5;
        }
        int hunger = eater.getHunger();
        eater.setHunger(hunger -= this.nutrition);
        if (this.expired == true && !(eater instanceof Rat)){
            eater.setSickness(true);
        }

        this.lives--;
        if (lives == 0){
            this.isAlive = false;
        }
    }

    @Override
    public void draw(Graphics g){
        //TODO: PUT CORRECT EMOJI
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
        g.drawString("🐈", Zoo.wrap(this.xCor,Zoo.ZOO_COLS)*Zoo.SCALE, Zoo.wrap(this.yCor,Zoo.ZOO_ROWS)*Zoo.SCALE+25);
    }

    @Override
    public void tick(Zoo z){
        this.age += 1;
        if (age > 400){
            if ((int)(Math.random()*100) == 1){
                this.expired = true;
            }
        }
    }
    
}

