package at.htl.coffee;


public class Coffee implements Comparable {

    Type type;

    public Coffee(Type t) {
        this.type = t;
    }

    @Override
    public int compareTo(Object o) {
        if (this.type.toString().equals(((Type)o).toString())) {
            return 0;
        }
        if (this.type.toString().charAt(0) > ((Type)o).toString().charAt(0)) {
            return 1;
        }
        return -1;
    }
}
