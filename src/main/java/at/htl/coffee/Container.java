package at.htl.coffee;

public class Container  {

    private int maxValue;

    public Container(int maxValue) {
        this.maxValue = maxValue;
    }

    public boolean getPortion(Portion portion) throws NotEnoughException {
        throw new NotEnoughException();
        //return false;
    }

    public void clear() {
        System.out.println("Ein Container geleert");
    }
}
