public abstract class Food extends Entity{
    protected int nutrition;
    protected boolean isAnimalProduct;
    protected boolean isVegetableProduct;

    public Food(String name, int x, int y, int age){
        super(name, x, y, age);

    }

    public abstract void beEaten(Animal eater);

    // TODO: add non-abstract methods as needed


    public String getProduct(){
        if (isAnimalProduct){
            return "Animal";
        }else{
            return "Vegetable";
        }
    }
}
