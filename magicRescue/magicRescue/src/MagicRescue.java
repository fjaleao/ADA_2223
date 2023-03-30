public class MagicRescue {

    private static final char EMPTY = 'e';
    private static final char HARP = 'h';
    private static final char POTION = 'p';
    private static final char CLOAK = 'c';
    private static final char T_DOG = '3';
    private static final char TROLL = 't';
    private static final char DRAGON = 'd';

    private static final int TABLE_DEPTH = 4;

    private static final int E = 0;     //empty row
    private static final int H = 1;     //harp row
    private static final int P = 2;     //potion row
    private static final int C = 3;     //cloak row


    private static final int INTEGER_INFINITY = 200000;


    private char[] line;

    private int result;


    public MagicRescue(char[] line) {
        this.line = line;
        result = INTEGER_INFINITY;
        solve(line);
    }

    public int getResult(){
        return result;
    }

    /**
     * 
     * @param line
     */
    private void solve(char[] line) {
        int line_l = line.length;
        //Create a table with 4 rows (number of items with empty) and with x+ 1 empty colum. x is the length of
        // the line
        int[][] table = new int[TABLE_DEPTH][line_l + 1];
        for(int column = line_l - 1 ; column >= 0; column--){
            char pos = line[column];
            switch (pos){
                case EMPTY -> resolveEmpty(table,column);
                case T_DOG, TROLL, DRAGON -> resolveCreature(table,column, pos);
                case HARP,POTION,CLOAK ->   resolveItem(table, column, pos);
            }
        }

        result = table[E][E];

    }


    /**
     * 
     * @param table
     * @param colum
     * @param pos
     */
    private void resolveItem(int[][] table, int colum, char pos) {
        switch (pos){
            case HARP -> fillTableHarp(table, colum);
            case POTION -> fillTablePotion(table, colum);
            case CLOAK -> fillTableCloak(table, colum);
        }

    }

    /**
     * 
     * @param table
     * @param colum
     */
    private void fillTableHarp(int[][] table, int colum) {
        for(int row = 0; row < TABLE_DEPTH; row++){
            switch (row){
                case E -> table[row][colum] = Math.min(1 + table[E][colum + 1], 2 + table[H][colum + 1]);
                case H -> table[row][colum] = Math.min(2 + table[E][colum + 1], 3 + table[H][colum + 1]);
                case P -> table[row][colum] = Math.min(2 + table[E][colum + 1], Math.min(3 + table[P][colum + 1],
                        3 + table[H][colum + 1]));
                case C -> table[row][colum] = Math.min(2 + table[E][colum + 1], Math.min(3 + table[C][colum + 1],
                        3 + table[H][colum + 1]));
            }
        }
    }

    /***
     * 
     * @param table
     * @param colum
     */
    private void fillTablePotion(int[][] table, int colum) {
        for(int row = 0; row < TABLE_DEPTH; row++){
            switch (row){
                case E -> table[row][colum] = Math.min(1 + table[E][colum + 1], 2 + table[P][colum + 1]);
                case H -> table[row][colum] =  Math.min(2 + table[E][colum + 1], Math.min(3 + table[H][colum + 1],
                        3 + table[P][colum + 1]));
                case P -> table[row][colum] = Math.min(2 + table[E][colum + 1], 3 + table[P][colum + 1]);
                case C -> table[row][colum] = Math.min(2 + table[E][colum + 1], Math.min(3 + table[C][colum + 1],
                        3 + table[P][colum + 1]));
            }
        }
    }

    /**
     * 
     * @param table
     * @param colum
     */
    private void fillTableCloak(int[][] table, int colum) {
        for(int row = 0; row < TABLE_DEPTH; row++){
            switch (row){
                case E -> table[row][colum] = Math.min(1 + table[E][colum + 1], 2 + table[C][colum + 1]);
                case H -> table[row][colum] =  Math.min(2 + table[E][colum + 1], Math.min(3 + table[H][colum + 1],
                        3 + table[C][colum + 1]));
                case P -> table[row][colum] = Math.min(2 + table[E][colum + 1], Math.min(3 + table[P][colum + 1],
                        3 + table[C][colum + 1]));
                case C -> table[row][colum] = Math.min(2 + table[E][colum + 1], 3 + table[C][colum + 1]);
            }
        }
    }



    /**
     * 
     * @param table
     * @param colum
     * @param pos
     */
    private void resolveCreature(int[][] table, int colum, char pos) {
        switch (pos){
            case T_DOG -> fillTableDog(table, colum);
            case TROLL -> fillTableTroll(table, colum);
            case DRAGON -> fillTableDragon(table,colum);
        }



    }

    /**
     * 
     * @param table
     * @param colum
     */
    private void fillTableDog(int[][] table, int colum) {
        for(int row = 0; row < TABLE_DEPTH; row++){
            switch (row){
                case E -> table[row][colum] = INTEGER_INFINITY;
                case H -> table[row][colum] = 4 + table[H][colum + 1];
                case P -> table[row][colum] = 5 + table[P][colum + 1];
                case C -> table[row][colum] = 6 + table[C][colum + 1];
            }
        }
    }

    /**
     * 
     * @param table
     * @param colum
     */
    private void fillTableTroll(int[][] table, int colum) {
        for(int row = 0; row < TABLE_DEPTH; row++){
            switch (row){
                case E, H -> table[row][colum] = INTEGER_INFINITY;
                case P -> table[row][colum] = 5 + table[P][colum + 1];
                case C -> table[row][colum] = 6 + table[C][colum + 1];
            }
        }
    }


    /**
     * 
     * @param table
     * @param colum
     */
    private void fillTableDragon(int[][] table, int colum) {
        for (int row = 0; row < TABLE_DEPTH; row++) {
            switch (row) {
                case E, H, P -> table[row][colum] = INTEGER_INFINITY;
                case C -> table[row][colum] = 6 + table[C][colum + 1];
            }
        }
    }


    /**
     * 
     * @param table
     * @param colum
     */
    private void resolveEmpty( int[][] table, int colum){
        for (int row = 0; row < TABLE_DEPTH; row++) {
            switch (row) {
                case E -> table[row][colum] = 1 + table[row][colum + 1];
                case H -> table[row][colum] = Math.min(2 + table[E][colum + 1], 3 + table[H][colum + 1]);
                case P -> table[row][colum] = Math.min(2 + table[E][colum + 1], 3 + table[P][colum + 1]);
                case C -> table[row][colum] = Math.min(2 + table[E][colum + 1], 3 + table[C][colum + 1]);
            }
        }
    }
}

