package at.htl.coffee;


public class CoffeeMachine {

    private Container coffeeContainer;
    private Container waterContainer;

    public CoffeeMachine(Container coffeeContainer, Container waterContainer) {
        this.coffeeContainer = coffeeContainer;
        this.waterContainer = waterContainer;
    }

    public boolean makeCoffee(Portion portion) throws NotEnoughException  {
        if (coffeeContainer.getPortion(portion) && waterContainer.getPortion(portion)) {
            return true;
        }
        throw new NotEnoughException();
    }

    public void cleanCoffeeMachine() {
        coffeeContainer.clear();
        waterContainer.clear();
    }

}
