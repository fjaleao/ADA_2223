import java.util.ArrayList;
import java.util.List;

public class LegoMosaics {

    private static int[] BRICKS = {1,2,3,4,6,8,10,12,16};
    private int[] nOccurrences;
    private int columns;

    private int[] waysSolv;

    private int result;


    public LegoMosaics(int[]nOccurrences, int columns) {
        result = 1;
        this.nOccurrences = nOccurrences;
        this.columns = columns;
        waysSolv = new int[columns+1];
        fillWaysSolve();
        resolve();

    }

    private void resolve() {
        for(int i = 1; i < waysSolv.length; i++)
            if(nOccurrences[i-1] > 0)
                result *= Math.pow(waysSolv[i], nOccurrences[i-1]);
    }

    private void fillWaysSolve() {
        List<Integer> bricksList = new ArrayList<>();
        for(int i: BRICKS)
            bricksList.add(i);
        waysSolv[0] = 1;
        waysSolv[1] = 1;
        int largeBrick = BRICKS[BRICKS.length-1];
        for(int i = 2; i< waysSolv.length; i++) {
            int aux = Math.min(i, largeBrick);
            for(int j = aux -1; j >= 0; j--)
                if(bricksList.contains(i - j))
                    waysSolv[i] += waysSolv[j];
        }
    }

    public int getResult() {
        return result;
    }


    }