public class MagicRescue {

    private static final char EMPTY = 'c';
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



    private String line;

    private int result;


    public MagicRescue(String line) {
        this.line = line;
        result = solve(line);
    }

    private int solve(String line) {
        int line_l = line.length();
        //Create a table with 4 rows (number of items with empty) and with x+ 1 empty colum. x is the length of
        // the line
        int[][] table = new int[TABLE_DEPTH][line_l + 1];
        int result = 0;
        for(int j = line_l - 1 ; j >= 0; j--){
            for(int i = 0; i < TABLE_DEPTH; i++){
                char pos = line.charAt(j);
                if(pos == EMPTY)
                    resolveEmpty(table, i, j, pos);
                else if (pos == T_DOG || pos == TROLL || pos == DRAGON ) { // List.of(T_DOG, TROLL, DRAGON).contains(p)
                    resolveCreature(table,i,j, pos);
                }
                else{
                    resolveItem(table, i, j, pos);
                }
            }
        }

        return result;
    }

    private void resolveItem(int[][] table, int row, int colum, char pos) {
        switch (row){
            case E: break;
            case H: break;
            case P: break;
            case C: break;
        }
    }

    private void resolveCreature(int[][] table, int row, int colum, char pos) {
        switch (row){
            case E: break;
            case H: break;
            case P: break;
            case C: break;
        }


    }

    private void resolveEmpty(int[][] table, int row, int colum, char pos) {
        switch (row){
            case E: break;
            case H: break;
            case P: break;
            case C: break;
        }
    }


    public int getResult(){
        return  result;
    }
}

