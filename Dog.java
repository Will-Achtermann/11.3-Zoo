
import java.util.*;
import java.awt.*;

// TODO: extend Animal
public class Dog extends Animal{

    int perferedDirection;

    public Dog(String name, int x, int y, int age){
        super(name, x, y, age);
        perferedDirection = (int)(Math.random() * 4);
    }

    @Override
    public void tick(Zoo z){
        //Dying
        if (age > 1000){
            if (isSick){
                if (Math.random() * 100 == 95){
                    this.isAlive = false;
                }
            }else{
                if (Math.random() * 1000 == 321){
                    this.isAlive = false;
                }
            }
        }
        //New dogs
        //TODO: TURN CORDS INTO WRAPED CORDS
        if (this.isAlive == false){
           if ((int)(Math.random() * 2) == 1){
                Dog d = new Dog("bruh", xCor + 1, yCor - 1, 0);
                z.add(d);
                if ((int)(Math.random() * 4) == 1){
                    Dog d2 = new Dog("bruh", xCor - 1, yCor - 1, 0);
                    z.add(d2);
                }
           }
        }
        
        //Eating
        for (Entity e : z.at(this.xCor, this.yCor)){
            if (e instanceof Ham || e instanceof Rat){
                if (hunger > 50){
                    this.eat(e);
                }else if (Math.random() * 100 == 83){
                    this.eat(e);
                }
            }
        }

        this.move(z);
    }

    @Override
    public void draw(Graphics g) {
        // two optional examples of a way to draw a cat follow!
        //TODO: CHANGE EMOJIS
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
        g.drawString("🐈", Zoo.wrap(this.xCor,Zoo.ZOO_COLS)*Zoo.SCALE, Zoo.wrap(this.yCor,Zoo.ZOO_ROWS)*Zoo.SCALE+25);

        //g.setColor(Color.DARK_GRAY);
        //g.setFont(new Font("Consolas", Font.PLAIN, 10));
        //g.drawString(" ^-^ ", Zoo.wrap(xPos,Zoo.ZOO_COLS)*Zoo.SCALE, Zoo.wrap(yPos,Zoo.ZOO_ROWS)*Zoo.SCALE+5);
        //g.drawString("/. .\\", Zoo.wrap(xPos,Zoo.ZOO_COLS)*Zoo.SCALE, Zoo.wrap(yPos,Zoo.ZOO_ROWS)*Zoo.SCALE+15);
        //g.drawString("\\_o_/", Zoo.wrap(xPos,Zoo.ZOO_COLS)*Zoo.SCALE, Zoo.wrap(yPos,Zoo.ZOO_ROWS)*Zoo.SCALE+25);
    }

    
    @Override
    public void eat(Food f){
        f.beEaten(this);
    }


    @Override
    public void move(Zoo z){
        int direction;
        if (Math.random()*100 < 75){
            direction = perferedDirection;
        }else{
            direction = (int)(Math.random()*4);
        }

        switch (direction) {
            case 1:
                this.yCor += 1;
                break;
            case 2:
                this.xCor += 1;
                break;
            case 3:
                this.yCor -= 1;
                break;
            case 4:
                this.xCor -= 1;
                break;
        }
    }


}

