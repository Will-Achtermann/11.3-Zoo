
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
        this.xCor = z.wrap(this.xCor, z.getWidth());
        this.yCor = z.wrap(this.yCor, z.getHeight());

        //Dying
        if (age > 500){
            if (isSick){
                if (Math.random() * 10 == 1){
                    lives -= 1;
                }
            }else{
                if (Math.random() * 100 == 2){
                    lives -= 1;
                }
            }
        }else if (isSick){
            if (Math.random()*1000 == 1){
                lives -= 1;
            }
        }
        if (lives == 0){
            this.isAlive = false;
        }

        //EATING
        for (Entity e : z.at(this.xCor, this.yCor)){
            if (hunger > 25 && (int)(Math.random()*100) < 100){
                if (e instanceof Ham){
                    this.eat((Ham)e);
                }else if (e instanceof Cheese){
                    this.eat((Cheese)e);
                }else if (e instanceof Rat){
                    Rat r = (Rat)e;
                    r.beEaten(this);
                }
            }
        }

        //Birthing
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                for (Entity e : z.at(x, y)){
                    if (e instanceof Cat && (int)(Math.random() * 10) == 1){
                        Cat c = new Cat("james", this.xCor + 2, this.yCor - 1, 0);
                        z.add(c);
                    }
                }
            }
        }
        this.move(z);
        this.age++;
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

    @Override
    public void eat(Food f){
        f.beEaten(this);
    }

    @Override
    public void move(Zoo z){
        //MOVING TO FOOD
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                for (Entity e : z.at(x, y)){
                    if (e instanceof Cheese || e instanceof Ham || e instanceof Rat){
                        this.xCor = x; 
                        this.yCor = y;
                        return;
                    }
                }
            }
        }

        //MOVING RANDOMLY
        switch ((int)(Math.random()*8 + 1)) {
            case 1: //North
                this.yCor += 1;
                break;
            case 2: //East
                this.xCor += 1;
                break;
            case 3: //South
                this.yCor -= 1;
                break;
            case 4: //West
                this.xCor -= 1;
                break;
            case 5: //North East
                this.xCor += 1;
                this.yCor += 1;
                break;
            case 6: //South East
                this.xCor += 1;
                this.yCor -= 1;
                break;
            case 7: //South West
                this.xCor -= 1;
                this.yCor -= 1;
                break;
            case 8: //North West
                this.xCor -= 1;
                this.yCor += 1;
        }   
    }


}
