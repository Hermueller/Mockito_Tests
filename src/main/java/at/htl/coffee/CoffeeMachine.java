package at.htl.coffee;


public class CoffeeMachine {

    private Container coffeeContainer;
    private Container waterContainer;

    public CoffeeMachine(Container coffeeContainer, Container waterContainer) {
        this.coffeeContainer = coffeeContainer;
        this.waterContainer = waterContainer;
    }

    public boolean makeCoffee(Portion portion) throws NotEnoughException  {
        return coffeeContainer.getPortion(portion) && waterContainer.getPortion(portion);
    }

    public void cleanCoffeeMachine() {
        waterContainer.clear();
        coffeeContainer.clear();
    }

}
