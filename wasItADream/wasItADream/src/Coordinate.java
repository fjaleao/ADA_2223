public class Coordinate {
    private int x, y;
    private char type;

    public Coordinate(int x, int y, char type){
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public char getType(){
        return type;
    }
}
