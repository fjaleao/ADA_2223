public class Coordinate {
    private int row, col, level;
    private char type;

    public Coordinate(int row, int col, char type, int level){
        this.row = row;
        this.col = col;
        this.type = type;
        this.level = level;
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return col;
    }

    public char getType(){
        return type;
    }
    
    public int getLevel() {
    	return level;
    }
}
