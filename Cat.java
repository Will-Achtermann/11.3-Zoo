
import java.util.*;
import java.awt.*;

// TODO: extend Animal
public class Cat extends Animal{

    private int lives;

    public Cat(String name, int x, int y, int age){
        super(name, x, y, age);
        lives = 9;
    }

    @Override
    public void tick(Zoo z){
        //Dying
        if (age > 500){
            if (isSick){
                if (Math.random() * 100 == 95){
                    lives -= 1;
                }
            }else{
                if (Math.random() * 1000 == 321){
                    lives -= 1;
                }
            }
        }
        if (lives == 0){
            this.isAlive = false;
        }

        

        this.move(z);
    }

    @Override
    public void draw(Graphics g) {
        // two optional examples of a way to draw a cat follow!

        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
        g.drawString("🐈", Zoo.wrap(this.xCor,Zoo.ZOO_COLS)*Zoo.SCALE, Zoo.wrap(this.yCor,Zoo.ZOO_ROWS)*Zoo.SCALE+25);

        //g.setColor(Color.DARK_GRAY);
        //g.setFont(new Font("Consolas", Font.PLAIN, 10));
        //g.drawString(" ^-^ ", Zoo.wrap(xPos,Zoo.ZOO_COLS)*Zoo.SCALE, Zoo.wrap(yPos,Zoo.ZOO_ROWS)*Zoo.SCALE+5);
        //g.drawString("/. .\\", Zoo.wrap(xPos,Zoo.ZOO_COLS)*Zoo.SCALE, Zoo.wrap(yPos,Zoo.ZOO_ROWS)*Zoo.SCALE+15);
        //g.drawString("\\_o_/", Zoo.wrap(xPos,Zoo.ZOO_COLS)*Zoo.SCALE, Zoo.wrap(yPos,Zoo.ZOO_ROWS)*Zoo.SCALE+25);
    }

    // TODO: override the eat method
    // TODO: override the move method
    @Override
    public void move(Zoo z){

    }


}
