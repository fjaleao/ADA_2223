import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Queue;

public class Tester {
    private final char[][] board;
    private int result;

    public Tester(char[][] board) {
        this.board = board;

    }


    public int resolve(int x, int y) {
        boolean[][] found = new boolean[board.length][board[0].length];
        Queue<Coordinate> waiting = new LinkedList<>();
        Coordinate current = new Coordinate(x, y, 0);
        waiting.add(current);
        found[x][y] = true;
       /* do{
            Coordinate c = waiting.poll();

        }*/
        

       /* Coordinate current = new Coordinate(x, y);
        waiting.add(current);
        found[x][y] = true;
        while(!waiting.isEmpty())
        return count;

        */
        return 0;
    }

    private int auxresolve(Queue<Coordinate> waiting, int count){
        Coordinate c = waiting.poll();


        return 0;
    }
}
