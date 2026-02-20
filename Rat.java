
import java.awt.*;

// TODO: extend Animal
public class Rat extends Animal{

    int perferedDirection;
    boolean isAnimalProduct;
    boolean isVegetableProduct;
    int nutrition;

    public Rat(String name, int x, int y, int age){
        super(name, x, y, age);
        perferedDirection = (int)(Math.random() * 8 + 1);
        isAnimalProduct = true;
        isVegetableProduct = false;
        nutrition = 10;
    }

    public void beEaten(Animal eater){
        int hung = eater.getHunger();
        eater.setHunger(hung -= this.nutrition);
        this.isAlive = false;
    }

    @Override
    public void tick(Zoo z){
        //Wrap
        this.xCor = z.wrap(this.xCor, z.getWidth());
        this.yCor = z.wrap(this.yCor, z.getHeight());


        //Dying
        if (this.isSick){
            if (this.age > 500){
                if ((int)(Math.random()*100) < 20){
                    this.isAlive = false;
                }
            }else{
                if ((int)(Math.random()*100) == 1){
                    this.isAlive = false;
                }
            }
        }else if (this.age > 500){
                if ((int)(Math.random()*75) == 1){
                    this.isAlive = false;
                } 
        }

        //Moving
        //TODO: TURN CORDS INTO WRAPED CORDS
        if (age % 5 == 0){
            this.move(z);
        }

        //NEW RATS
        if (age % 50 == 0){
            if ((int)(Math.random()*10) == 1){
                Rat r = new Rat("boy", z.wrap(this.xCor + 1, z.getWidth()), z.wrap(this.yCor - 1, z.getHeight()), 0);
                z.add(r);
            }
        }

        //Making sure hunger doesnt go below 20
        if (this.hunger < 20){
            this.hunger = 20;
        }
        
        //Eating
        for (Entity e : z.at(this.xCor, this.yCor)){
            if (e instanceof Cheese){
                this.eat((Cheese)e);
            }else if(e instanceof Ham && this.hunger > 50){
                this.eat((Ham)e);
            }else if (e instanceof Rat && this.hunger > 50){
                Rat r = (Rat)e;
                r.beEaten(this);
            }
        }
        

        this.age++;
    }

    @Override
    public void draw(Graphics g) {
        // two optional examples of a way to draw a cat follow!
        //TODO: CHANGE EMOJIS
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
        g.drawString("🐀", Zoo.wrap(this.xCor,Zoo.ZOO_COLS)*Zoo.SCALE, Zoo.wrap(this.yCor,Zoo.ZOO_ROWS)*Zoo.SCALE+25);

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
        //resetting direction
        if (age % 50 == 0){
            perferedDirection = (int)(Math.random()*8 + 1);
        }

        //Moving to food
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                for (Entity e : z.at(x, y)){
                    if (e instanceof Cheese){
                        this.xCor = x; 
                        this.yCor = y;
                        return;
                    }
                }
            }
        }

        //moving normally
        switch (perferedDirection) {
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

