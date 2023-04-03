public class MagicRescue {

    private static final char EMPTY = 'e';
    private static final char HARP = 'h';
    private static final char POTION = 'p';
    private static final char CLOAK = 'c';
    private static final char T_DOG = '3';
    private static final char TROLL = 't';
    private static final char DRAGON = 'd';

    private static final int T_DOG_SOM = 4;
    private static final int TROLL_SOM = 5;

    private static final int DRAGON_SOM = 6;

    private static final int TABLE_DEPTH = 4;

    private static final int E = 0; //empty row
    private static final int H = 1; // harp row
    private static final int P = 2; // potion row
    private static final int C = 3; // cloak row
    private static final int INTEGER_INFINITY = 2000000000;
    //Entry and exit with no item time unit
    private static final int NO_ITEM_NO_ITEM = 1;
    //Entry with item and exit with no item time unit
    private static final int ITEM_NO_ITEM = 2;
    //Entry with no item and exit with item time unit
    private static final int NO_ITEM_ITEM = 2;
    //Entry with item and exit with item time unit
    private static final int ITEM_ITEM = 3;





    private char[] line;

    private int result;


    public MagicRescue(char[] line) {
        this.line = line;
        result = INTEGER_INFINITY;
        solve(line);
    }

    /**
     *
     * Creates an array with all the possible items we can have, including nothing.
     * The array will be a sum of fillings depending on the plot we are in
     * @param line the route that Harry and Ron need to do to save Hermione
     */
    private void solve(char[] line) {
        int line_l = line.length;
        int[] array = new int[TABLE_DEPTH];
        for(int column = line_l - 1 ; column >= 0; column--){
            char pos = line[column];
            switch (pos){
                case EMPTY -> resolveEmpty(array);
                case T_DOG, TROLL, DRAGON -> resolveCreature(array, pos);
                case HARP,POTION,CLOAK ->   resolveItem(array, column, pos);
            }
        }

        result = array[E];

    }


    /**
     * Endpoint for dealing with item-filled plots
     *
     * @param array     table with results
     * @param pos       plot content
     */
    private void resolveItem(int[] array, int column, char pos) {
        switch (pos){
            case HARP -> fillTableHarp(array);
            case POTION -> fillTablePotion(array);
            case CLOAK -> fillTableCloak(array);
        }

    }

    /**
     * Fills the array with the cases when going through a harp-plot
     *
     * @param array     table with results
     */
    private void fillTableHarp(int[] array) {
        int[] temp = array.clone();
        for(int row = 0; row < TABLE_DEPTH; row++){
            switch (row){
                case E -> array[row] = Math.min(NO_ITEM_NO_ITEM + temp[E], NO_ITEM_ITEM + temp[H]);
                case H -> array[row] = Math.min(ITEM_NO_ITEM + temp[E], ITEM_ITEM + temp[H]);
                case P -> array[row] = Math.min(ITEM_NO_ITEM + temp[E], Math.min(ITEM_ITEM + temp[P], ITEM_ITEM + temp[H]));
                case C -> array[row] = Math.min(ITEM_NO_ITEM + temp[E], Math.min(ITEM_ITEM + temp[C], ITEM_ITEM + temp[H]));
            }
        }
    }

    /**
     * Fills the array with the cases when going through a potion-plot
     *
     * @param array     table with results
     */
    private void fillTablePotion(int[] array) {
        int[] temp = array.clone();
        for(int row = 0; row < TABLE_DEPTH; row++){
            switch (row){
                case E -> array[row] = Math.min(NO_ITEM_NO_ITEM + temp[E], NO_ITEM_ITEM + temp[P]);
                case H -> array[row] = Math.min(ITEM_NO_ITEM + temp[E], Math.min(ITEM_ITEM + temp[H], ITEM_ITEM + temp[P]));
                case P -> array[row] = Math.min(ITEM_NO_ITEM + temp[E], ITEM_ITEM + temp[P]);
                case C -> array[row] = Math.min(ITEM_NO_ITEM + temp[E], Math.min(ITEM_ITEM + temp[C], ITEM_ITEM + temp[P]));
            }
        }
    }

    /**
     * Fills the array with the cases when going through a cloak-plot
     *
     * @param array     table with results
     */
    private void fillTableCloak(int[] array) {
        int[] temp = array.clone();
        for(int row = 0; row < TABLE_DEPTH; row++){
            switch (row){
                case E -> array[row] = Math.min(NO_ITEM_NO_ITEM + temp[E], NO_ITEM_ITEM + temp[C]);
                case H -> array[row] = Math.min(ITEM_NO_ITEM + temp[E], Math.min(ITEM_ITEM + temp[H], ITEM_ITEM + temp[C]));
                case P -> array[row] = Math.min(ITEM_NO_ITEM + temp[E], Math.min(ITEM_ITEM + temp[P], ITEM_ITEM + temp[C]));
                case C -> array[row] = Math.min(ITEM_NO_ITEM + temp[E], ITEM_ITEM + temp[C]);
            }
        }
    }


    /**
     * Endpoint for when dealing with beast-filled plots
     *
     * @param array     table with results
     * @param pos       plot content
     */
    private void resolveCreature(int[] array, char pos) {
        switch (pos){
            case T_DOG -> fillTableDog(array);
            case TROLL -> fillTableTroll(array);
            case DRAGON -> fillTableDragon(array);
        }
    }

    /**
     * Fills the array with the cases when dealing with a three-headed dog plot
     *
     * @param array     table with results
     */
    private void fillTableDog(int[] array) {
        int[] temp = array.clone();
        for(int row = 0; row < TABLE_DEPTH; row++){
            switch (row){
                case E -> array[row] = INTEGER_INFINITY;
                case H -> array[row] = T_DOG_SOM + temp[H];
                case P -> array[row] = TROLL_SOM + temp[P];
                case C -> array[row] = DRAGON_SOM + temp[C];
            }
        }
    }

    /**
     * Fills the array with the cases when dealing with a troll plot
     *
     * @param array     table with results
     */
    private void fillTableTroll(int[] array) {
        int[] temp = array.clone();
        for(int row = 0; row < TABLE_DEPTH; row++){
            switch (row){
                case E, H   -> array[row] = INTEGER_INFINITY;
                case P      -> array[row] = TROLL_SOM + temp[P];
                case C      -> array[row] = DRAGON_SOM + temp[C];
            }
        }
    }


    /**
     * Fills the array with the cases when dealing with a dragon plot
     *
     * @param array     table with results
     */

    private void fillTableDragon(int[] array) {
        int[] temp = array.clone();
        for (int row = 0; row < TABLE_DEPTH; row++) {
            switch (row) {
                case E, H, P -> array[row] = INTEGER_INFINITY;
                case C       -> array[row] = DRAGON_SOM + temp[C];
            }
        }
    }

    /**
     * Fills the array with the cases when dealing with a empty plot
     *
     * @param array     table with results
     */
    private void resolveEmpty( int[] array){
        int[] temp = array.clone();
        for (int row = 0; row < TABLE_DEPTH; row++) {
            switch (row) {
                case E -> array[row] = NO_ITEM_NO_ITEM + temp[row];
                case H -> array[row] = Math.min(NO_ITEM_ITEM + temp[E], ITEM_ITEM + temp[H]);
                case P -> array[row] = Math.min(NO_ITEM_ITEM + temp[E], ITEM_ITEM + temp[P]);
                case C -> array[row] = Math.min(NO_ITEM_ITEM + temp[E], ITEM_ITEM + temp[C]);
            }
        }
    }


    public int getResult(){
        return result;
    }
}

