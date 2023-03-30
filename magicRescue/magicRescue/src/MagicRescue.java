public class MagicRescue {

    private static final char EMPTY = 'e';
    private static final char HARP = 'h';
    private static final char POTION = 'p';
    private static final char CLOAK = 'c';
    private static final char T_DOG = '3';
    private static final char TROLL = 't';
    private static final char DRAGON = 'd';

    private static final int TABLE_DEPTH = 4;

    private static final int E = 0; //empty row
    private static final int H = 1; // harp row
    private static final int P = 2; // potion row
    private static final int C = 3; // cloak row

    /*
    The usage of this constant is because there isn't any Infinity in class Integer.
    Casting a Double or Floating Infinity and adding one will return the minimum value representable by an 'int'
     */
    private static final int INTEGER_INFINITY = 2000000000;


    private String line;

    private int result;


    public MagicRescue(String line) {
        this.line = line;
        result = INTEGER_INFINITY;
        solve(line);
    }

    private void solve(String line) {
        int line_l = line.length();
        //Create a table with 4 rows (number of items with empty) and with x+ 1 empty colum. x is the length of
        // the line
        int[][] table = new int[TABLE_DEPTH][line_l + 1];
        for(int colum = line_l - 1 ; colum >= 0; colum--){
            char pos = line.charAt(colum);
            switch (pos){
                case EMPTY -> resolveEmpty(table,colum);
                case T_DOG, TROLL, DRAGON -> resolveCreature(table,colum, pos);
                case HARP,POTION,CLOAK ->   resolveItem(table, colum, pos);
            }
        }

        result = table[E][E];

    }


    private void resolveItem(int[][] table, int colum, char pos) {
        switch (pos){
            case HARP -> fillTableHarp(table, colum);
            case POTION -> fillTablePotion(table, colum);
            case CLOAK -> fillTableCloak(table, colum);
        }

    }

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



    private void resolveCreature(int[][] table, int colum, char pos) {
        switch (pos){
            case T_DOG -> fillTableDog(table, colum);
            case TROLL -> fillTableTroll(table, colum);
            case DRAGON -> fillTableDragon(table,colum);
        }



    }

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

    private void fillTableTroll(int[][] table, int colum) {
        for(int row = 0; row < TABLE_DEPTH; row++){
            switch (row){
                case E, H -> table[row][colum] = INTEGER_INFINITY;
                case P -> table[row][colum] = 5 + table[P][colum + 1];
                case C -> table[row][colum] = 6 + table[C][colum + 1];
            }
        }
    }


    private void fillTableDragon(int[][] table, int colum) {
        for (int row = 0; row < TABLE_DEPTH; row++) {
            switch (row) {
                case E, H, P -> table[row][colum] = INTEGER_INFINITY;
                case C -> table[row][colum] = 6 + table[C][colum + 1];
            }
        }
    }


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


    public int getResult(){
        return result;
    }
}

