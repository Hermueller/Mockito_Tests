package at.htl.coffee;


import com.sun.tools.corba.se.idl.constExpr.Not;

public class Container  {

    private int maxValue;

    public Container(int maxValue) {
        this.maxValue = maxValue;
    }

    public boolean getPortion(Portion portion) throws NotEnoughException {
        switch (portion) {
            case LARGE:
                if (maxValue >= 3) {
                    maxValue -= 3;
                    return true;
                }
                break;
            case MEDIUM:
                if (maxValue >= 2) {
                    maxValue -= 2;
                    return true;
                }
                break;
            case SMALL:
                if (maxValue >= 1) {
                    maxValue -= 1;
                    return true;
                }
                break;
            default:
                throw new NotEnoughException();
                //return false;
        }
        throw new NotEnoughException();
        //return false;
    }

    public void clear() {
        maxValue = 0;
    }
}
