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
     * Creates a table and fills it with the respective possibility of each case
     * After the table is filled, the result is in the position table[0][0]
     * 
     * @param line      the route that Harry and Ron need to do to save Hermione
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
     * Fills the table with the cases when going through empty easy-plots
     * 
     * @param table     table with results
     * @param column    column to fill in the table 
     */
    private void resolveEmpty( int[][] table, int column){
        for (int row = 0; row < TABLE_DEPTH; row++) {
            switch (row) {
                case E -> table[row][column] = 1 + table[row][column + 1];
                case H -> table[row][column] = Math.min(2 + table[E][column + 1], 3 + table[H][column + 1]);
                case P -> table[row][column] = Math.min(2 + table[E][column + 1], 3 + table[P][column + 1]);
                case C -> table[row][column] = Math.min(2 + table[E][column + 1], 3 + table[C][column + 1]);
            }
        }
    }


    /**
     * Endpoint for when dealing with non-empty easy plots
     * 
     * @param table     table with results
     * @param column    column to fill in the table 
     * @param pos       plot content
     */
    private void resolveItem(int[][] table, int column, char pos) {
        switch (pos){
            case HARP -> fillTableHarp(table, column);
            case POTION -> fillTablePotion(table, column);
            case CLOAK -> fillTableCloak(table, column);
        }

    }

    /**
     * Fills the table with the cases when going through harp easy-plots
     * 
     * @param table     table with results
     * @param column    column to fill in the table
     */
    private void fillTableHarp(int[][] table, int column) {
        for(int row = 0; row < TABLE_DEPTH; row++){
            switch (row){
                case E -> table[row][column] = Math.min(1 + table[E][column + 1], 2 + table[H][column + 1]);
                case H -> table[row][column] = Math.min(2 + table[E][column + 1], 3 + table[H][column + 1]);
                case P -> table[row][column] = Math.min(2 + table[E][column + 1], Math.min(3 + table[P][column + 1],
                        3 + table[H][column + 1]));
                case C -> table[row][column] = Math.min(2 + table[E][column + 1], Math.min(3 + table[C][column + 1],
                        3 + table[H][column + 1]));
            }
        }
    }

    /**
     * Fills the table with the cases when going through potion easy-plots
     * 
     * @param table     table with results
     * @param column    column to fill in the table 
     */
    private void fillTablePotion(int[][] table, int column) {
        for(int row = 0; row < TABLE_DEPTH; row++){
            switch (row){
                case E -> table[row][column] = Math.min(1 + table[E][column + 1], 2 + table[P][column + 1]);
                case H -> table[row][column] =  Math.min(2 + table[E][column + 1], Math.min(3 + table[H][column + 1],
                        3 + table[P][column + 1]));
                case P -> table[row][column] = Math.min(2 + table[E][column + 1], 3 + table[P][column + 1]);
                case C -> table[row][column] = Math.min(2 + table[E][column + 1], Math.min(3 + table[C][column + 1],
                        3 + table[P][column + 1]));
            }
        }
    }

    /**
     * Fills the table with the cases when going through cloak easy-plots
     * 
     * @param table     table with results
     * @param column    column to fill in the table 
     */
    private void fillTableCloak(int[][] table, int column) {
        for(int row = 0; row < TABLE_DEPTH; row++){
            switch (row){
                case E -> table[row][column] = Math.min(1 + table[E][column + 1], 2 + table[C][column + 1]);
                case H -> table[row][column] =  Math.min(2 + table[E][column + 1], Math.min(3 + table[H][column + 1],
                        3 + table[C][column + 1]));
                case P -> table[row][column] = Math.min(2 + table[E][column + 1], Math.min(3 + table[P][column + 1],
                        3 + table[C][column + 1]));
                case C -> table[row][column] = Math.min(2 + table[E][column + 1], 3 + table[C][column + 1]);
            }
        }
    }



    /**
     * Endpoint for when dealing with beast-filled plots
     * 
     * @param table     table with results
     * @param column    column to fill in the table 
     * @param pos       plot content
     */
    private void resolveCreature(int[][] table, int column, char pos) {
        switch (pos){
            case T_DOG -> fillTableDog(table, column);
            case TROLL -> fillTableTroll(table, column);
            case DRAGON -> fillTableDragon(table,column);
        }



    }

    /**
     * Fills the table with the cases when dealing with three-headed dog plots
     * 
     * @param table     table with results
     * @param column    column to fill in the table 
     */
    private void fillTableDog(int[][] table, int column) {
        for(int row = 0; row < TABLE_DEPTH; row++){
            switch (row){
                case E -> table[row][column] = INTEGER_INFINITY;
                case H -> table[row][column] = 4 + table[H][column + 1];
                case P -> table[row][column] = 5 + table[P][column + 1];
                case C -> table[row][column] = 6 + table[C][column + 1];
            }
        }
    }

    /**
     * Fills the table with the cases when dealing with troll plots
     * 
     * @param table     table with results
     * @param column    column to fill in the table 
     */
    private void fillTableTroll(int[][] table, int column) {
        for(int row = 0; row < TABLE_DEPTH; row++){
            switch (row){
                case E, H -> table[row][column] = INTEGER_INFINITY;
                case P -> table[row][column] = 5 + table[P][column + 1];
                case C -> table[row][column] = 6 + table[C][column + 1];
            }
        }
    }


    /**
     * Fills the table with the cases when dealing with dragon plots
     * 
     * @param table     table with results
     * @param column    column to fill in the table 
     */
    private void fillTableDragon(int[][] table, int column) {
        for (int row = 0; row < TABLE_DEPTH; row++) {
            switch (row) {
                case E, H, P -> table[row][column] = INTEGER_INFINITY;
                case C -> table[row][column] = 6 + table[C][column + 1];
            }
        }
    }
}

