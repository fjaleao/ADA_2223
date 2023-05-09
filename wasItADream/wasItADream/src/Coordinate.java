public class Coordinate {
    int axis, ordinate, count;

    public Coordinate(int axis, int ordinate, int count){
        this.axis = axis;
        this.ordinate = ordinate;
        this.count = count;
    }

    public int getAxis(){
        return axis;
    }

    public int getOrdinate(){
        return ordinate;
    }

    public int getCount(){
        return count;
    }
}
